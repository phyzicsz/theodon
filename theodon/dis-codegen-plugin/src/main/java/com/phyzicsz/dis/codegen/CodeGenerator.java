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
import com.phyzicsz.dis.datamodel.api.DisClass;
import com.squareup.javapoet.JavaFile;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
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
    final File testOutputPath;
   
    public CodeGenerator(final File inputPath, final File outputPath,final File testOutputPath){
        this.inputPath = inputPath;
        this.outputPath = outputPath;
        this.testOutputPath = testOutputPath;
               
    }
    
    public CodeGenerator generate(){
//        try {
//            //clear the generated sources
//            //clearGeneratedSources(outputPath);
//            //clearGeneratedSources(testOutputPath);
//        } catch (IOException ex) {
//            LOGGER.error("Error cleaning up previous classes",ex);
//        }
        
        //create all of the mappings
        DisMapper mapper = new DisMapper();
        List<DisClass> classes;
        try {
            classes = mapper.mapper(inputPath);
             generateClasses(classes);
             generateTestClasses(classes);
        } catch (CodeGenerationConfigurationException | IOException | ClassNotFoundException ex) {
            LOGGER.error("Error generating classes",ex);
        }
          
        return this; 
    }
    
    private void generateClasses(List<DisClass> classes) throws CodeGenerationConfigurationException, IOException, ClassNotFoundException{
        if(null == outputPath){
            throw new CodeGenerationConfigurationException("Output Directory cannot be undefined");
        }
        
        for (DisClass disClass : classes) {
            try {
                JavaFile javaFile = new DisClassGenerator()
                    .generate(disClass);
                writeClassFile(outputPath, javaFile);
            } catch (IOException ex) {
                LOGGER.error("Error Writing File: ", ex);
            }
        }
        
    }
    
    private void generateTestClasses(List<DisClass> classes) throws CodeGenerationConfigurationException, IOException{
        if(null == outputPath){
            throw new CodeGenerationConfigurationException("Output Directory cannot be undefined");
        }
        
        for (DisClass disClass : classes) {
            try {
                
                JavaFile javaFile = new DisTestClassGenerator()
                    .generate(disClass);
                writeClassFile(testOutputPath, javaFile);
            } catch (IOException ex) {
                LOGGER.error("Error Writing File: ", ex);
            }
        }
        
    }

    
    private void writeClassFile(File outputPath, JavaFile javaFile) throws IOException{
        LOGGER.info("Writing file {}", javaFile.toJavaFileObject().getName());
        Path file = outputPath.toPath();
        file.toFile().mkdirs();
        javaFile.writeTo(file); 
    }
    
//    private void clearGeneratedSources(File directory) throws IOException{
//        Path path = directory.toPath();
//        Files.walk(path)
//                .sorted(Comparator.reverseOrder())
//                .map(Path::toFile)
//                .forEach(File::delete);
//
//        path.toFile().mkdirs();
//    }
    
//    private void clearGeneratedSources(String packageName, File directory) throws IOException{
//        Path path = directory.toPath();
//        Files.walk(path)
//                .sorted(Comparator.reverseOrder())
//                .map(Path::toFile)
//                .forEach(File::delete);
//
//        path.toFile().mkdirs();
//    }
}
