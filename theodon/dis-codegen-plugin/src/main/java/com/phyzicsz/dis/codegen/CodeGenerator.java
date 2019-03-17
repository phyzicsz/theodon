/*
 * Copyright 2019 phyzicsz.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.phyzicsz.dis.codegen;

import com.phyzicsz.dis.codegen.exceptions.CodeGenerationConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import static java.nio.charset.StandardCharsets.UTF_8;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author phyzicsz
 */
public class CodeGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);
    final File inputPath;
    final File outputPath;
    
    public CodeGenerator(final File inputPath, final File outputPath){
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }
    
    public CodeGenerator generate() throws CodeGenerationConfigurationException, IOException, ParserConfigurationException, SAXException{
        
        load();
        return this;
        
    }
    
    private void load() throws CodeGenerationConfigurationException, IOException, ParserConfigurationException, SAXException{
        if(null == inputPath || null == outputPath){
            throw new CodeGenerationConfigurationException("Directories cannot be undefined");
        }
        
        if(inputPath.isDirectory()){
            FileCollector collector = new FileCollector();
            Files.walkFileTree(inputPath.toPath(), collector);
            List<Path> files = collector.getFiles();
            for(Path file: files){
                prepare(file);
        

            }
        }else{
            
        }
    }
    
    private void prepare(Path file) throws IOException, ParserConfigurationException, SAXException{
        BufferedReader reader = Files.newBufferedReader(file, UTF_8);
        
        SAXParserFactory factory = SAXParserFactory.newInstance();
	SAXParser saxParser = factory.newSAXParser();
        
        //saxParser.p

    }
    
   
}
