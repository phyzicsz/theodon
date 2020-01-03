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
import com.phyzicsz.dis.datamodel.api.DisClasses;
import com.squareup.javapoet.JavaFile;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author phyzicsz
 */
public class CodeGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(CodeGenerator.class);
    private final String javaPackage;
    private final File outputPath;
    private final File testOutputPath;
    private static final String DIS_INPUT_FILE = "dis/dis7.xml";
   
    public CodeGenerator(final String javaPackage, final File outputPath,final File testOutputPath){
        this.javaPackage = javaPackage;
        this.outputPath = outputPath;
        this.testOutputPath = testOutputPath;
               
    }
    
    public CodeGenerator generate() throws CodeGenerationConfigurationException, IOException, ClassNotFoundException{

        Class<?>[] allowedClasses = new Class[] { DisClasses.class};
        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(DisClasses.class);
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypes(allowedClasses);

        URL url = getClass().getClassLoader().getResource(DIS_INPUT_FILE);
        
        LOGGER.info("parsing DIS XML File: {}", DIS_INPUT_FILE);
        DisClasses classes = (DisClasses)xstream.fromXML(url);
        LOGGER.info("parsed {} classes: ", classes.getClasses().size());
        
        
        for (DisClass disClass : classes.getClasses()) {
            try {
                LOGGER.info("generating class {}: ", disClass.getName());
                DisClassGenerator generator = new DisClassGenerator();
                JavaFile javaFile = generator.generate(javaPackage,disClass);
                
                generator.writeClassFile(outputPath, javaFile);
            } catch (IOException ex) {
                LOGGER.error("Error Writing File: ", ex);
            }
        }
        
          
        return this; 
    }
    
//    private void generateClasses(DisClasses classes) throws CodeGenerationConfigurationException, IOException, ClassNotFoundException{
//        if(null == outputPath){
//            throw new CodeGenerationConfigurationException("Output Directory cannot be undefined");
//        }
//        
//        for (DisClass disClass : classes.getClasses()) {
//            try {
//                JavaFile javaFile = new DisClassGenerator()
//                        .generate(javaPackage,disClass);
//                writeClassFile(outputPath, javaFile);
//            } catch (IOException ex) {
//                LOGGER.error("Error Writing File: ", ex);
//            }
//        }
//        
//    }
    
//    private void generateTestClasses(DisClasses classes) throws CodeGenerationConfigurationException, IOException{
//        if(null == outputPath){
//            throw new CodeGenerationConfigurationException("Output Directory cannot be undefined");
//        }
//        
//        for (DisClass disClass : classes.getClasses()) {
//            try {
//                
//                JavaFile javaFile = new DisTestClassGenerator()
//                    .generate(javaPackage, disClass);
//                writeClassFile(testOutputPath, javaFile);
//            } catch (IOException ex) {
//                LOGGER.error("Error Writing File: ", ex);
//            }
//        }
//        
//    }

    
   
    
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
