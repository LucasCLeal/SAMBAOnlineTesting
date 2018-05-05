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
    static Path configRootPath = Paths.get("/Users/LucasCLeal/NetBeansProjects/modelupdater/ModelGen/config.properties");
    

    //variaveis a ser iniciadas.
    private static boolean isLoaded = false;

    public static String pathToData;
    public static String pathToArchive;
    public static String pathToProduction;
    
    //variáveis para uso da interface WEB conversão de arquivos unicos ou multiplos
    public static String pathToOrigin;
    public static String pathToTarget;

    static FileHandler flHand = new FileHandler();


    public static boolean loadConfigurations() {
        //verificando se o arquivo já foi carregado
        if (!isLoaded) {

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
                        System.out.println("[ModelUpdater]: config.properties file not readable");
                        return isLoaded;
                    }

                    try {
                        f.close();
                    } catch (IOException io) {
                        System.out.println("[ModelUpdater]: impossible to close file");
                    }

                    //carregando variáveis
                    pathToData= configFile.getProperty("PATHTODATA");
                    pathToArchive= configFile.getProperty("PATHTOARCHIVE");
                    pathToProduction= configFile.getProperty("PATHTOPRODUCTION");
                    pathToOrigin= configFile.getProperty("PATHTOORIGIN");
                    pathToTarget= configFile.getProperty("PATHTOTARGET");
                    
                    System.out.println("[ModelUpdater]: configurations loaded");

                }

            } catch (IOException ex) {
                System.out.println("Error while reading config file at path: " + configRootPath.toString());
                return isLoaded;
            }    
            isLoaded = true;       
        }
        return isLoaded;
    }

}
