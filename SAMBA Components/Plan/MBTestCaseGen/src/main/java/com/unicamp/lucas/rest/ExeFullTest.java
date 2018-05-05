package com.unicamp.lucas.rest;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.config.ServiceConfig;
import com.unicamp.lucas.testCaseGen.TestGenerator;
import java.awt.PageAttributes;
import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author LucasCLeal
 */
@Path("/exefulltest")
public class ExeFullTest {

    //iniciando objetos usados apra executar operações
    static TestGenerator tstGen = new TestGenerator();
    static FileHandler flHand = new FileHandler();
    //static ServiceConfig srvConf = new ServiceConfig();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String ExeFullApplicationTest() {

        ServiceConfig.loadConfigurations();
        //execute testes using all models in the root folder.
        //buscando arquivos na pasta raiz   
        ArrayList<File> modelFiles = flHand.filesAtPathWithExtension(Paths.get(ServiceConfig.pathToModels), ServiceConfig.modelExt);
        String outPut = "";

        //exibindo resultado da busca
        System.out.println("models found: ");
        for (File modelFile : modelFiles) {
            System.out.println(modelFile.getName());
        }

        //executando testes para cada arquivo de modelo encontrado
        if (tstGen.requiredServicesOnline()) {
            for (File modelFile : modelFiles) {
                try {
                    outPut = outPut.concat(tstGen.exeTestReturnResult(modelFile,30));
                } catch (Exception e) {
                    System.out.println("[MBTestCaseGen] Rest request problem:" + e);
                }
            }

        } else {
            System.out.println("please, check if the communication port to Rest Server is ok.");
        }

        return outPut;

    }

}
