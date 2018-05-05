/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.rest;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.config.ServiceConfig;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author LucasCLeal
 */
@Path("/getOriginPath")
public class WSConfigOriginData {
    
   
    //métodos para criação de um modelo a partir de um filepath de entrada
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getWSOriginDirPath(){
        //ler arquivo de configuração e retornar informação sobre diretorio origem
        ServiceConfig.loadConfigurations();
        return ServiceConfig.pathToOrigin;
    }
    
}
