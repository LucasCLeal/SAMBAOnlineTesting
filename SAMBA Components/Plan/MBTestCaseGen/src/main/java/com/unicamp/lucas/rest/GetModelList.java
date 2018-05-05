/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.rest;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.testCaseGen.TestGenerator;
import com.unicamp.lucas.common.pojo.ModelsInfo;
import com.unicamp.lucas.config.ServiceConfig;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.FilenameUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author LucasCLeal
 */
@Path("/getmodellist")
public class GetModelList {

    //iniciando objetos usados apra executar operações
    static TestGenerator tstGen = new TestGenerator();
    static FileHandler flHand = new FileHandler();
    
    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public ModelsInfo FetchList() {
        //carregando configuracoes
        ServiceConfig.loadConfigurations();
  
        //buscando arquivos na pasta raiz   
        ArrayList<File> modelFiles = flHand.filesAtPathWithExtension(Paths.get(ServiceConfig.pathToModels), ServiceConfig.modelExt);
        ArrayList<String> modelNames = new ArrayList<>();
        for (File moldelFile : modelFiles) {
            modelNames.add(FilenameUtils.removeExtension(moldelFile.getName()));
        }
        ModelsInfo modelinfo = new ModelsInfo(modelNames);
        System.out.print(modelinfo.toString());

        return modelinfo;
    }

}
