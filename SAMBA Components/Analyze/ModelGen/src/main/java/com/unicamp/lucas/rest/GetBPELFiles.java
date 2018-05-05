/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.rest;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.common.pojo.FileInfo;
import com.unicamp.lucas.config.ServiceConfig;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author LucasCLeal
 */
@Path("/getBPELList")
public class GetBPELFiles {
 
    static FileHandler flHand = new FileHandler();
    
    /**
     *
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public FileInfo FetchList() {
        //carregando configuracoes
        ServiceConfig.loadConfigurations();
        //buscando arquivos na pasta raiz   
        ArrayList<File> bpelFiles = flHand.filesAtPathWithExtension(Paths.get(ServiceConfig.pathToOrigin), "bpel");
        ArrayList<String> fileNames = new ArrayList<>();
        for (File bpelFile : bpelFiles) {
            fileNames.add(FilenameUtils.removeExtension(bpelFile.getName()));
        }
        FileInfo fileInfo = new FileInfo(fileNames);
        return fileInfo;
    }

}
