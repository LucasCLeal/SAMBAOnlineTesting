/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.rest;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.config.ServiceConfig;
import static com.unicamp.lucas.rest.GetModelList.flHand;
import static com.unicamp.lucas.rest.SingleModelTestGen.flHand;
import static com.unicamp.lucas.rest.SingleModelTestGen.tstGen;
import com.unicamp.lucas.testCaseGen.TestGenerator;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.apache.commons.io.FilenameUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author LucasCLeal
 */
@Path("/multipleMT")
public class MultipleMutantTest {

    //iniciando objetos usados apra executar operações
    static TestGenerator tstGen = new TestGenerator();
    static FileHandler flHand = new FileHandler();

    @POST
    @Produces(MediaType.TEXT_PLAIN)
    public String ExecuteMultipleMutantTests(String data) {

        //Loading configurations
        ServiceConfig.loadConfigurations();

        //SEPARANDO AS CONFIGURAÇOES DE MANEIRA DUVIDOSA
        String[] config;
        config = data.split("/");
        String dirName = config[0];
        int testRep = Integer.parseInt(config[1]);

        //obtendo lista de arquivos mutantes
        ArrayList<File> mutantFiles = flHand.filesAtPathWithExtension(Paths.get(ServiceConfig.pathToMutants + "/" + dirName), ServiceConfig.modelExt);

        //contador de testes
        int counter = 0;
        
        for (File moldelFile : mutantFiles) {
            
            //incrementando o contador
            counter++;
            
            System.out.println("[Mutant NAME]: " + moldelFile.getName());
            System.out.println("[Mutant NUMBER]: " + counter);
            
            //iniciando loop de teste
            for (int i = 0; i < testRep; i++) {
                
                try {
                    //executando teste
                    tstGen.exeTestReturnResult(moldelFile,30);
                } catch (IOException ex) {
                    Logger.getLogger(SingleModelTestGen.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        }

        return "Finished Test Execution, check report files";

    }

}
