/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.testCaseGen;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.config.ServiceConfig;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import javax.jws.Oneway;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import org.codehaus.jackson.map.DeserializerFactory;

/**
 *
 * @author LucasCLeal Serviço SOAP para model based testing
 */
@WebService(serviceName = "MBTestCaseGen")
public class MBTestCaseGen {

    //iniciando objetos usados apra executar operações
    static TestGenerator tstGen = new TestGenerator();
    static FileHandler flHand = new FileHandler();

    /**
     * Operação de Web service
     *
     * @param fileName
     * @param fileData
     * @return
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "ExecuteTest")
    public void ExecuteTest(@WebParam(name = "orchestrationFileName") String orchestrationFileName) throws IOException {

        //searching for modelfile 
        //changing the extension of the file name
        String jsonModelname = flHand.removeFileExt(orchestrationFileName) + ".json";
        //criating path to PRD folder @ knoledge base
        Path prdBPELPath = flHand.generatePathDirForFileName(Paths.get(ServiceConfig.pathToProduction), orchestrationFileName);
        //using path to load curent orchestration model
        File md = new File(flHand.generateFilePath(prdBPELPath, jsonModelname).toString());
        if (flHand.fileOrfolderExist(md.toPath())) {
            if (tstGen.requiredServicesOnline()) {
                try {
                    if (tstGen.loadModeltoGW(md)) {
                        tstGen.exeTest(md);
                    }
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
            }
        }
    }

    /**
     * Operação de Web service
     *
     * @param fileName
     * @return
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "startTestCaseGenForBpelModelNamed")
    public boolean startTestCaseGenForBpelModelNamed(@WebParam(name = "fileName") String fileName) throws IOException {
        System.out.println("[MBTestCaseGen]: starting Test Case Gen.");
        ServiceConfig.loadConfigurations();
        try {
            //removendo extensão do nome do arquivo e colocando ext do modelo
            String jsonModelname = flHand.removeFileExt(fileName) + ".json";
            //criando path para pasta PRD no knoledge base
            Path prdBPELPath = flHand.generatePathDirForFileName(Paths.get(ServiceConfig.pathToProduction), fileName);

            //criando possivel nome arquivo de modelo para arquivo do mesmo nome
            File md = new File(flHand.generateFilePath(prdBPELPath, jsonModelname).toString());
            if (flHand.fileOrfolderExist(md.toPath())) {
                System.out.println("[MBTestCaseGen] model for " + fileName + " found.");
                System.out.println("[MBTestCaseGen] Setting up test case generator.");
                //verificando se GW-rest está operacional e carregando arquivo no serviço
                if (tstGen.requiredServicesOnline()) {
                    return tstGen.loadModeltoGW(md);
                } else {
                    return false;
                }
            } else {
                throw new FileNotFoundException("[MBTestCaseGen] no model file found for bpel named: " + fileName);
            }

        } catch (IOException e) {
            System.out.println("[EXCEPTION]" + e);
            return false;
        }
    }

    /**
     * Operação de Web service
     *
     * @return
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "getNextStepForBpelModelNamed")
    public String getNextStepForBpelModelNamed() throws IOException {
        String result = tstGen.getNextStepOfTestCaseForModelFile();
        if (result == null) {
            System.out.println("[MBTestCaseGen] stop condition reached");
        }
        return result;
    }

    /**
     * Operação de Web service
     *
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "addExeResulttoTestReport")
    public void addExeResulttoTestReport(@WebParam(name = "test") String Test, @WebParam(name = "result") boolean result) throws IOException {
        tstGen.AddToTestReport(Test, result);
    }

    /**
     * Operação de Web service
     *
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "finishtestCaseGeneration")
    public void finishtestCaseGeneration(@WebParam(name = "testeExeData") String executionData) throws IOException {
        tstGen.finishTestReportAndStopGWService(executionData);
        System.out.println("[MBTestCaseGen]: Test Case Gen finished");
    }

    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "abortTestCaseGen")
    public void abortTestCaseGen(@WebParam(name = "testeExeData") String exeData) {
        tstGen.abortTestReportAndStopGWService(exeData);
        System.out.println("[MBTestCaseGen]: Test Case Aborted");
    }

    //METODOS PARA TESTE DO WS USANDO PAGINA
    @WebMethod(operationName = "executeAllTests")
    public void executeAllTests() {

        //loading config
        ServiceConfig.loadConfigurations();

        //execute testes using all models in the root folder.
        //buscando arquivos na pasta raiz   
        ArrayList<File> modelFiles;
        modelFiles = flHand.filesAtPathWithExtension(Paths.get(ServiceConfig.pathToModels), ServiceConfig.modelExt);

        //exibindo resultado da busca
        System.out.println("models found: ");
        for (File modelFile : modelFiles) {
            System.out.println(modelFile.getName());
        }

        //gerando testes para cada arquivo de modelo encontrado
        if (tstGen.requiredServicesOnline()) {
            for (File modelFile : modelFiles) {
                try {
                    if (tstGen.loadModeltoGW(modelFile)) {
                        tstGen.exeTest(modelFile);
                    }
                } catch (Exception e) {
                    System.out.println("Exception: " + e);
                }
            }

        } else {
            System.out.println("please, check if the communication port to Rest Server is right.");
        }
    }

}
