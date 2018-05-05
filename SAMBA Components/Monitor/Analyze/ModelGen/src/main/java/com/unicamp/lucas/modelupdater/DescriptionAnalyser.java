/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.modelupdater;

import java.io.File;
import com.unicamp.lucas.modelupdater.XmlParser;
import jdk.nashorn.internal.parser.TokenType;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;

/**
 *
 * @author LucasCLeal Classe responsible for reading BPEL and WSDL files to DOM
 * Parsers, This info is used later to determine the name and path to the
 * dependencies file of a BPEL file name of services and their operations
 *
 */
public class DescriptionAnalyser {

    public static Document getFileParsedInfo(File file) {

        Document parsedDoc = null;
        //checking file extension
        //if file is a BPEL file
        if (FilenameUtils.getExtension(file.getName()).equals("bpel")) {
            parsedDoc = XmlParser.parseFile(file);
        }
        //IF file is a WSDL file
        if (FilenameUtils.getExtension(file.getName()).equals("wsdl")) {
            parsedDoc = XmlParser.parseFile(file);
        } else {
            //o arquivo recebido não é um wsdl ou bpel
            System.err.println("failed to parse file, it is not bpel nor wsdl");
        }

        return parsedDoc;

    }
    
    public static boolean checkModelUpdateNecessity(String orchestrationName){
    
        //used to verify if the changes in the description files are suficient to trigger model generation
        //this current version assumes that all updates in the description files trigger an update.
        
        return true;
    
    } 

}
