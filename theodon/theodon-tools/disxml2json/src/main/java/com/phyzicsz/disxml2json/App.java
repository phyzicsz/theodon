/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.phyzicsz.disxml2json;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;



/**
 *
 * @author phyzicsz
 */
public class App {
    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    
    public static void main(String[] args){
        try {
            new DisXml()
                    .init()
                    .convert();
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            LOGGER.error("Error converting file", ex);
        }
    }
}
