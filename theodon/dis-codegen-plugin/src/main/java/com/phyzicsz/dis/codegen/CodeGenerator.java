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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
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
                    roaster.roast(idl);
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
}
