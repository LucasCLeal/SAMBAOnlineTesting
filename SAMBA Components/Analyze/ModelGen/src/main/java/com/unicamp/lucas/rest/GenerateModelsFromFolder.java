/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.rest;

import com.unicamp.lucas.config.ServiceConfig;
import com.unicamp.lucas.modelupdater.ServerFunctions;
import static com.unicamp.lucas.rest.GenerateModelFromFile.svFunc;
import java.nio.file.Paths;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author LucasCLeal
 */
@Path("/genMultipleModels")
public class GenerateModelsFromFolder {
    
    static ServerFunctions svFunc = new ServerFunctions();
    
    
    //métodos para criação de um modelo a partir de um filepath de entrada
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String GenMultipleModels(){
        //converte todos os modelos da pasta de origem em modelos
        //executa a conversão de todos os arquivos, vai adicionando em uma nova linha o resultado da conversão
        //no final manda o resultado como uma string
        
        ServiceConfig.loadConfigurations();
        return svFunc.generateMoldesFromFolder(Paths.get(ServiceConfig.pathToOrigin), Paths.get(ServiceConfig.pathToTarget));
        
    }
    
}
