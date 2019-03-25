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
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
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

    private final ObjectMapper mapper = new ObjectMapper();

    public void convert() throws ParserConfigurationException, SAXException, IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File xmlfile = new File(classLoader.getResource("dis7.xml").getFile());
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(xmlfile);
        doc.getDocumentElement().normalize();

        System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
        NodeList nList = doc.getElementsByTagName("class");
        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            System.out.println("\nCurrent Element :" + nNode.getNodeName());
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;

                DisClass dis = new DisClass();
                className(dis, element);
                classParent(dis, element);
                classComment(dis, element);

                NodeList attrs = element.getElementsByTagName("attribute");
                System.out.println("----------------------------");
                System.out.println("num attrs: " + attrs.getLength());

                for (int count = 0; count < attrs.getLength(); count++) {
                    Node node1 = attrs.item(count);

                    if (node1.getNodeType() == Node.ELEMENT_NODE) {
                        Element attr = (Element) node1;
                        DisAttribute disAttr = new DisAttribute();
                        attributeName(disAttr, attr);
                        attributeComment(disAttr, attr);
                        attributeType(disAttr, attr);
                        attributeInitialValue(disAttr, attr);
                        //System.out.println("\tname: " + attr.getAttribute("name"));
                        //System.out.println("\tcomment: " + attr.getAttribute("comment"));

                        //System.out.println("\ttype: " + getAttributePrimative(attr));
                        //System.out.println("\tref type: " + getAttributeClassRef(attr));
                        dis.setAttribute(disAttr);
                    }
                }
                dis.setPackageName("com.phyzicsz.dis7");
                String content = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dis);
                writeFile(new File("/tmp/dis"), "com.phyzicsz.dis7",  content, dis.getName());
            }
        }
    }

    private void className(DisClass dis, Element element) {
        String className = element.getAttribute("name");
        System.out.println("name : " + className);
        dis.setName(className);
    }

    private void attributeName(DisAttribute dis, Element element) {
        String className = element.getAttribute("name");
        System.out.println("\tname : " + className);
        dis.setName(className);
    }

    private void attributeComment(DisAttribute dis, Element element) {
        String className = element.getAttribute("comment");
        System.out.println("\tcomment : " + className);
        dis.setComment(className);
    }

    private void attributeType(DisAttribute dis, Element element) {
        String type = getAttributePrimative(element);
        if (type.isEmpty()) {
            System.out.println("\ttype : " + type);
            type = getAttributeClassRef(element);
            dis.setType(type);
        }
        
    }
    
    private void attributeInitialValue(DisAttribute dis, Element element) {
        String initialValue = getAttributePrimativeInitialValue(element);
        if (!initialValue.isEmpty()) {
            System.out.println("\tinitialValue : " + initialValue);
            dis.setInitialValue(initialValue);
        }
    }

    private void classParent(DisClass dis, Element element) {
        String parent = element.getAttribute("inheritsFrom");
        System.out.println("parent : " + parent);
        dis.setParent(parent);
    }

    private void classComment(DisClass dis, Element element) {
        String comment = element.getAttribute("comment");
        System.out.println("comment : " + comment);
        dis.setComment(comment);
    }

    private String getAttributePrimative(Element attr) {
        NodeList prims = attr.getElementsByTagName("primitive");
        for (int x = 0; x < prims.getLength(); x++) {
            Node node2 = prims.item(x);

            if (node2.getNodeType() == Node.ELEMENT_NODE) {
                Element prim = (Element) node2;
//                 System.out.println("\ttype: " +prim.getAttribute("type"));
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
//                System.out.println("\tref type: " + prim.getAttribute("name"));
                return prim.getAttribute("name");
            }
        }

        return "";
    }
    
    private void writeFile(File outputPath, String packageName, String content, String fileName) throws UnsupportedEncodingException, IOException{
        Path file = outputPath.toPath();
        String[] splits = packageName.split("\\.");
        for(String split: splits){
            file = Paths.get(file.toString(), split);
        }
        file.toFile().mkdirs();
        file = Paths.get(file.toString(), fileName + ".dis");
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file.toString()), "utf-8"))) {
            writer.write(content);
        }
        
        
    }

}
