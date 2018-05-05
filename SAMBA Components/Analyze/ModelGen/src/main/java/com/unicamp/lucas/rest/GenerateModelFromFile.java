/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.rest;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.config.ServiceConfig;
import com.unicamp.lucas.modelupdater.DataGatherer;
import com.unicamp.lucas.modelupdater.ServerFunctions;
import java.nio.file.Paths;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author LucasCLeal
 */
@Path("/genSingleModel")
public class GenerateModelFromFile {
    
    static ServerFunctions svFunc = new ServerFunctions();
    

    //métodos para criação de um modelo a partir de um filepath de entrada
    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String GenModelFileFromBPELFileName(String fileName) {
        
        System.out.println(fileName);
        ServiceConfig.loadConfigurations();
        String result = svFunc.generateModelFromBpelFile(fileName,Paths.get(ServiceConfig.pathToOrigin),Paths.get(ServiceConfig.pathToTarget));
        
        if(!result.isEmpty()){
            return "Model created for bpelfile: "+fileName;
        }else{
            return "Problems creating the model";
        }
    }
    
}
