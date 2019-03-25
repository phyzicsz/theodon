/*
 * Copyright 2019 pborawski.
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
package com.phyzicsz.disxml2json;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.SAXException;

/**
 *
 * @author pborawski
 */
public class DisXml {
    private static final Logger LOGGER = LoggerFactory.getLogger(DisXml.class);
    private XMLConverter converter = new XMLConverter(); 
    public DisXml init(){
        Config conf = ConfigFactory.load();
        
        String disFile = conf.getString("disxml.disFile");
        String outputDir = conf.getString("disxml.outputDir");
        String packageName = conf.getString("disxml.package");
        
        converter.disFile(disFile)
                .outputDir(outputDir)
                .packageName(packageName);

                return this;
    }
    
    public DisXml convert() throws ParserConfigurationException, SAXException, IOException{
        converter.convert();
        return this;
    }
}
