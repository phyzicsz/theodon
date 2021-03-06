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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phyzicsz.dis.codegen.exceptions.CodeGenerationConfigurationException;
import com.phyzicsz.dis.codegen.model.DisClass;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class CodeGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);
    final File inputPath;
    final File outputPath;
    private final ObjectMapper mapper = new ObjectMapper();
    private final DisRoaster roaster = new DisRoaster();
    
    public CodeGenerator(final File inputPath, final File outputPath){
        this.inputPath = inputPath;
        this.outputPath = outputPath;
    }
    
    public CodeGenerator generate() throws CodeGenerationConfigurationException, IOException{
        
        load();
        return this;
        
    }
    
    private void load() throws CodeGenerationConfigurationException, IOException{
        if(null == inputPath || null == outputPath){
            throw new CodeGenerationConfigurationException("Directories cannot be undefined");
        }
        
        if(inputPath.isDirectory()){
            FileCollector collector = new FileCollector();
            Files.walkFileTree(inputPath.toPath(), collector);
            List<Path> files = collector.getFiles();
            for(Path file: files){
                Optional<DisClass> opt = map(file);
                opt.ifPresent(idl -> {
                    String classString = roaster.roast(idl);
                    try {
                        writeFile(outputPath,idl.getPackageName(),classString, idl.getName());
                    } catch (IOException ex) {
                        LOGGER.error("Error Writing File: ", ex);
                    }
                });
            }
        }else{
            
        }
    }
    
    private Optional<DisClass> map(Path file){
        DisClass idl = null;
        try {
            idl = mapper.readValue(file.toFile(), DisClass.class);
        } catch (IOException ex) {
            LOGGER.error("Error mapping IDL", ex);
        }
        return Optional.ofNullable(idl);
    }
    
    private void writeFile(File outputPath, String packageName, String content, String fileName) throws UnsupportedEncodingException, IOException{
        Path file = outputPath.toPath();
        String[] splits = packageName.split("\\.");
        for(String split: splits){
            file = Paths.get(file.toString(), split);
        }
        file.toFile().mkdirs();
        file = Paths.get(file.toString(), fileName + ".java");
        try (Writer writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(file.toString()), "utf-8"))) {
            writer.write(content);
        }
        
        
    }
}
