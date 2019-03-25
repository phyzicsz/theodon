/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phyzicsz.disxml2json;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author phyzicsz
 */
public class XMLConverter {

    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(App.class);

    private final ObjectMapper mapper = new ObjectMapper();
    private String disFile ;
    private String outputDir;
    private String packageName;
    
    public XMLConverter disFile(String disFile){
        this.disFile = disFile;
        return this;
    }
    
    public XMLConverter outputDir(String outputDir){
        this.outputDir = outputDir;
        return this;
    }
    
    public XMLConverter packageName(String packageName){
        this.packageName = packageName;
        return this;
    }

    public void convert() throws ParserConfigurationException, SAXException, IOException {
        Path path = new File(outputDir).toPath();
        Files.walk(path)
                .sorted(Comparator.reverseOrder())
                .map(Path::toFile)
                .forEach(File::delete);

        path.toFile().mkdirs();
        
        ClassLoader classLoader = getClass().getClassLoader();
        File xmlfile = new File(classLoader.getResource(disFile).getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize();

        LOGGER.info("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("class");
        LOGGER.info("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            LOGGER.info("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;

                DisClass dis = new DisClass();
                className(dis, element);
                classParent(dis, element);
                classComment(dis, element);

                NodeList attrs = element.getElementsByTagName("attribute");
                LOGGER.info("----------------------------");
                LOGGER.info("num attrs: " + attrs.getLength());

                for (int count = 0; count < attrs.getLength(); count++) {
                    Node node1 = attrs.item(count);

                    if (node1.getNodeType() == Node.ELEMENT_NODE) {
                        Element attr = (Element) node1;
                        DisAttribute disAttr = new DisAttribute();
                        attributeName(disAttr, attr);
                        attributeComment(disAttr, attr);
                        attributeType(disAttr, attr);
                        attributeInitialValue(disAttr, attr, disAttr.getType());

                        dis.setAttribute(disAttr);
                    }
                }
                dis.setPackageName(packageName);
                
                writeFile(new File(outputDir), dis);
            }
        }
    }

    private void className(DisClass dis, Element element) {
        String className = element.getAttribute("name");
        LOGGER.info("name : " + className);
        dis.setName(className);
    }

    private void attributeName(DisAttribute dis, Element element) {
        String className = element.getAttribute("name");
        LOGGER.info("\tname : " + className);
        dis.setName(className);
    }

    private void attributeComment(DisAttribute dis, Element element) {
        String className = element.getAttribute("comment");
        LOGGER.info("\tcomment : " + className);
        dis.setComment(className);
    }

    private void attributeType(DisAttribute dis, Element element) {
        String type = getAttributePrimative(element);
        if (type.isEmpty()) {
            LOGGER.info("\ttype : " + type);
            type = getAttributeClassRef(element);
            
        }
        dis.setType(type);
    }

    private void attributeInitialValue(DisAttribute dis, Element element, String type) {
        String initialValue = getAttributePrimativeInitialValue(element);
        if (!initialValue.isEmpty()) {
            LOGGER.info("\tinitialValue : " + initialValue);
            switch (type) {
                case "double":
                    initialValue = initialValue.concat("d");
                    break;
                case "float":
                    initialValue = initialValue.concat("f");
                    break;
                case "long":
                    initialValue = initialValue.concat("l");
                    break;
                case "unsigned int":
                    initialValue = initialValue.concat("l");
                    break;
                default:
                    break;
            }
            dis.setInitialValue(initialValue);
        }
    }

    private void classParent(DisClass dis, Element element) {
        String parent = element.getAttribute("inheritsFrom");
        LOGGER.info("parent : " + parent);
        dis.setParent(parent);
    }

    private void classComment(DisClass dis, Element element) {
        String comment = element.getAttribute("comment");
        LOGGER.info("comment : " + comment);
        dis.setComment(comment);
    }

    private String getAttributePrimative(Element attr) {
        NodeList prims = attr.getElementsByTagName("primitive");
        for (int x = 0; x < prims.getLength(); x++) {
            Node node2 = prims.item(x);

            if (node2.getNodeType() == Node.ELEMENT_NODE) {
                Element prim = (Element) node2;
                return prim.getAttribute("type");
            }
        }

        return "";
    }

    private String getAttributePrimativeInitialValue(Element attr) {
        NodeList prims = attr.getElementsByTagName("primitive");
        for (int x = 0; x < prims.getLength(); x++) {
            Node node2 = prims.item(x);

            if (node2.getNodeType() == Node.ELEMENT_NODE) {
                Element prim = (Element) node2;
                return prim.getAttribute("defaultValue");
            }
        }

        return "";
    }

    private String getAttributeClassRef(Element attr) {
        NodeList refs = attr.getElementsByTagName("classRef");
        for (int x = 0; x < refs.getLength(); x++) {
            Node node3 = refs.item(x);

            if (node3.getNodeType() == Node.ELEMENT_NODE) {
                Element prim = (Element) node3;
                return prim.getAttribute("name");
            }
        }

        return "";
    }

    private void writeFile(File outputPath, DisClass dis) throws UnsupportedEncodingException, IOException {
        Path file = outputPath.toPath();
        
        file = Paths.get(file.toString(), dis.getName() + ".dis");
        
        String content = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dis);
        
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file.toString()), "utf-8"))) {
            writer.write(content);
        }

    }

}
