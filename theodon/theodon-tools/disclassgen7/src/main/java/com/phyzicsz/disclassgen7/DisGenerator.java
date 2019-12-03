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
package com.phyzicsz.disclassgen7;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author phyzicsz
 */
public class DisGenerator {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisGenerator.class);
    private XMLGenerator generator = new XMLGenerator(); 
    
    public DisGenerator init(){
        Config conf = ConfigFactory.load();
        
        String disFile = conf.getString("disxml.disFile");
        String outputDir = conf.getString("disxml.outputDir");
        String packageName = conf.getString("disxml.package");
        
        generator.disFile(disFile)
                .outputDir(outputDir)
                .packageName(packageName);

                return this;
    }
    
    public DisGenerator convert() throws ParserConfigurationException, SAXException, IOException{
        generator.convert();
        return this;
    }
}
