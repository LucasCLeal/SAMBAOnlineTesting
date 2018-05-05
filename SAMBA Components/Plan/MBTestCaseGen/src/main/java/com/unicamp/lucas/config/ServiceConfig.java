/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.config;

import java.util.Properties;
import java.io.*;
import java.nio.file.Path;
import com.unicamp.lucas.common.FileHandler;
import java.nio.file.Paths;

/**
 *
 * @author LucasCLeal
 */
public class ServiceConfig {

    //rootconfigFile MAC
    static Path configRootPath = Paths.get("/Users/LucasCLeal/Documents/workspace/SAMBTestingService/MBTestCaseGen/config.properties");
    //rootconfigFile Ubuntu
    //static Path configRootPath = Paths.get("/home/sats/workspace/SAMBATestingSystem/sambatestingsystem/MBTestingService/config.properties");

    //variaveis a ser iniciadas.
    private static boolean isLoaded = false;

    public static String pathToModels;
    public static String pathToReports;
    public static String pathToArchive;
    public static String pathToProduction;
    
    //parametros para testes com mutantes
    public static String pathToMutants;
    
    
    public static String modelExt;

    static FileHandler flHand = new FileHandler();


    public static void loadConfigurations() {

        //verificando se o arquivo já foi carregado
        if (isLoaded == false) {

            //tentando ler arquivo de configuração
            try {
                if (flHand.fileOrfolderExist(configRootPath)) {
                    //arquivo encontrado

                    Properties configFile = new Properties();
                    FileInputStream f;
                    try {
                        f = new FileInputStream(configRootPath.toFile());
                        configFile.load(f);
                    } catch (Exception e) {
                        System.out.println("[MBTestCaseGen] config.properties file not readable");
                        return;
                    }

                    try {
                        f.close();
                    } catch (IOException io) {
                        System.out.println("[MBTestCaseGen] impossible to close file");
                    }

                    //carregando variáveis
                    pathToModels = configFile.getProperty("PATHTOMODELS");
                    pathToArchive = configFile.getProperty("PATHTOARCHIVE");
                    pathToReports = configFile.getProperty("PATHTOREPORTS");
                    pathToProduction = configFile.getProperty("PATHTOPRODUCTION");
                    pathToMutants = configFile.getProperty("PATHTOMUTANTS");
                    
                    modelExt = configFile.getProperty("MODELEXT");
                    
                    System.out.println("[MBTestCaseGen] config loaded");

                }

            } catch (IOException ex) {
                System.out.println("Error while reading config file at path: " + configRootPath.toString());
                return;
            }
            
            isLoaded = true;
            
        } else {
            System.out.println("[MBTestCaseGen] config already loaded");
        }

    }

}
