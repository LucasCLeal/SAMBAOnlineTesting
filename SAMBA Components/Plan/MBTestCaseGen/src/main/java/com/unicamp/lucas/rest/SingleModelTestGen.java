/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.rest;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.common.pojo.ModelsInfo;
import com.unicamp.lucas.config.ServiceConfig;
import com.unicamp.lucas.testCaseGen.TestGenerator;
import static com.unicamp.lucas.rest.GetModelList.flHand;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author LucasCLeal
 */
@Path("/singleMTG")
public class SingleModelTestGen {
    
    //iniciando objetos usados apra executar operações
    static TestGenerator tstGen = new TestGenerator();
    static FileHandler flHand = new FileHandler();

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String ExecuteSingleTest(String model) {
        
        //Loading configurations
        ServiceConfig.loadConfigurations();

        System.out.println("[Single Model Test Gen]: " + model);
        //verificando existencia do arquivo
        File modelfile = new File(flHand.fileAtPathWithName( Paths.get(ServiceConfig.pathToModels), model, ServiceConfig.modelExt).toString());
        try {
            //executando teste
            return tstGen.exeTestReturnResult(modelfile,30);
        } catch (IOException ex) {
            Logger.getLogger(SingleModelTestGen.class.getName()).log(Level.SEVERE, null, ex);
            return ex.toString();
        }

        
    }

}
