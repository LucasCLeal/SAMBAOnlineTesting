/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.testCaseGen;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.common.pojo.TestStatistics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.ws.WebServiceRef;
import jdk.nashorn.internal.codegen.CompilerConstants;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.node.TreeTraversingParser;
import org.json.JSONObject;

/**
 *
 * @author LucasCLeal Classe criada para lidar com a criação de casos de teste,
 * conecxão do com clintes SAOP para TS, e geração de relatórios com testes.
 */
public class TestGenerator {

    static GraphWalkerRestHandler gwRestHandler = new GraphWalkerRestHandler();
    static FileHandler flHandler = new FileHandler();
    static TestReport tstRep = new TestReport();
    static TestingServiceSOAPHandler tsServHandler = new TestingServiceSOAPHandler();

    //variaveis para geração do relatório
    static String repHead = null;
    static String repBody = null;
    static File actualModelFile = null;

    public boolean loadModeltoGW(File modelfile) throws IOException {

        //resetando serviço
        resetVarsAndGWService();
        //tentando ler arquivo e carregando para uso de geraçao de testes
        String jsModel = flHandler.File2String(modelfile);
        actualModelFile = modelfile;
        return gwRestHandler.loadModel(jsModel);

    }

    public void resetVarsAndGWService() {
        gwRestHandler.resetService();
        repHead = "";
        repBody = "";
        actualModelFile = null;
    }

    public boolean requiredServicesOnline() {

        try {
            //verificando se serviços estao online
            return gwRestHandler.isServiceOnline() && tsServHandler.checkTestServiceAvailability();
        } catch (IOException ex) {
            System.out.println("[MBTestCaseGen] requiredServicesOnline" + ex);
            return false;
        }

    }

    public void shutGWRestServiceDown() {
        //TODO verificar ser WS esta funcionando e desligar se estiver.

    }

    public void setTestGeneratorAndStopCondition() {
        //Setando configuração do modelo para execução do teste!
    }

    public void getStatistic() throws IOException {

        //criando JSobject para receber estatisticas
        JSONObject jsObject = new JSONObject(gwRestHandler.getTestStatistics());
        System.out.println("[MBTestCaseGen] jsObject: " + jsObject.toString());

        //TestStatistics tstStats = new TestStatistics();
        //tstStats.setEdgeCoverage(jsObject.getString("edgeCoverage"));
        //tstStats.setVertexCoverage(jsObject.getString("vertexCoverage"));
        //tstStats.setResult(jsObject.getString("result"));
        //tstStats.setTotalNumberOfEdges(jsObject.getString("totalNumberOfEdges"));
        //tstStats.setTotalNumberOfVertices("totalNumberOfVertices");
        //tstStats.setTotalNumberOfVisitedEdges("totalNumberOfVisitedEdges");
        //tstStats.setTotalNumberOfVisitedVertices("totalNumberOfVisitedVertices");
    }

    //getNextStepInTestCase
    public String getNextStepOfTestCaseForModelFile() {
        //modelo ja estará carregado no GW
        //verificando se cabeçalho do relatório esta vazio
        startTestReportForModelFile();
        if (gwRestHandler.hasNextStep()) {
            //filtrar entre vertices e arestas talvez seja necessário
            return gwRestHandler.getNextStep();
        } else {
            return null;
        }
    }

    //startTestReport
    public void startTestReportForModelFile() {
        if (repHead.equals("")) {
            //criando novo cabeçalho
            repHead = tstRep.generateReportHeader(actualModelFile);
        }
    }

    //AddToTestReport
    public void AddToTestReport(String testedElement, boolean result) {
        //adicionando linha no relatório com 
        repBody = tstRep.fillReportBody(repBody, testedElement, result);
    }

    //finishTestReport
    public void finishTestReportAndStopGWService(String exeData) {

        //finalizar execução do teste e escrever dados no relatório
        //exibindo dados da execução
        //escrevendo statisticas no arquivo
        try {
            tstRep.updateTestStatFile(actualModelFile.getName(), exeData);
            //relatorio
            tstRep.updateTestReportFile(actualModelFile.getName(), repHead + repBody);
            //parando ws
            gwRestHandler.stopService();
        } catch (Exception e) {
            System.err.println("Error while writing report: " + e);
            //repetint operation SIM É UMA GAMBIARRA
            finishTestReportAndStopGWService(exeData);
        }

    }

    //ABORTTestReport
    public void abortTestReportAndStopGWService(String exeData) {

        //finalizar execução do teste e escrever dados no relatório
        //exibindo dados da execução
        //escrevendo statisticas no arquivo
        try {
            tstRep.updateTestStatFile(actualModelFile.getName(), exeData);
            //relatorio
            tstRep.updateTestReportFile(actualModelFile.getName(), "TEST CASE ABORTED");
            //parando ws
            gwRestHandler.stopService();
        } catch (Exception e) {
            System.err.println("Exection while wrting report: " + e);
            //repetint operation SIM É UMA GAMBIARRA
            finishTestReportAndStopGWService(exeData);
        }

    }

    public void exeTest(File modelFile) throws IOException {
        //metodo responsável por fazer o link entre o TS e o graphWalker RestClient
        //testes e resultados são salvos num arquivo de texto.
        //numero de casos de testes executados e tempo de execução são contados aqui e transferidos para arquivo st_modelName.txt

        //NEW
        if (loadModeltoGW(modelFile)) {
            System.out.println("[MBTestCaseGen]: model loaded");
            //iniciando report
            startTestReportForModelFile();
            //iniciando contadores e tags para gerador de statistica
            Integer counter = 1;
            long startTime = System.currentTimeMillis();
            //result: 1=sucsess, 0=fail
            //presupondo que o teste terá um resultado positivo
            Integer testRestult = 1;
            //executando loop de geração de teste
            while (gwRestHandler.hasNextStep()) {
                //incrementando contador
                counter++;
                String nextElement = gwRestHandler.getNextStep();
                //TODO chamada para teste para TESTING SERVICE (obrigado lucas do passado!)
                if (tsServHandler.exeServiceOperation(nextElement)) {
                    //teste bem sucedido, adicionando informação no report 
                    repBody = tstRep.fillReportBody(repBody, nextElement, true);
                } else {
                    //caso o resultado seja negativo interromper loop e terminar terminar teste
                    repBody = tstRep.fillReportBody(repBody, nextElement, false);
                    testRestult = 0;
                    break;
                }
            }
            //verificando tempo necessário para executar loop de execuçao de testes
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            //finalizando execução do teste
            String executionData = Long.toString(elapsedTime) + "," + Integer.toHexString(counter) + "," + testRestult;
            finishTestReportAndStopGWService(executionData);
            System.out.println("[MBTestCaseGen]: Test finished!");
        } else {
            System.out.println("[MBTestCaseGen] problems loading model to GraphWalker");
        }
    }

    public String exeTestReturnResult(File modelFile, int maxTestInstruc) throws IOException {

        //metodo responsável por fazer o link entre o TS e o graphWalker RestClient
        //testes e resultados são salvos num arquivo de texto.
        //Texto adicionado no arquivo 'e retornado (usado para exibir dados na pagina WEB do serv)
        //NEW
        if (loadModeltoGW(modelFile)) {
            System.out.println("[MBTestCaseGen]: model loaded");

            //iniciando report
            startTestReportForModelFile();
            //iniciando contadores e tags para gerador de statistica
            Integer counter = 0;
            long startTime = System.currentTimeMillis();
            //result: 1=sucsess, 0=fail, -1 abort
            //presupondo que o teste terá um resultado positivo
            Integer testRestult = 1;
            
            //resetando variaveis usadas por IMAGESCRAPER, FEEDREADER, HYPERTIMETABLE
            tsServHandler.resetCompostionTestVariables();
            //executando loop de geração de teste
            
            while (gwRestHandler.hasNextStep()) {

                //verificando numero de instruçoes
                if (counter == maxTestInstruc) {
                    //caso o resultado seja negativo interromper loop e terminar terminar teste
                    repBody = tstRep.fillReportBody(repBody, "ABORT", false);
                    testRestult = -1;
                    break;
                }

                String nextElement = gwRestHandler.getNextStep();
                //caso o não exista proximo elemento (problema no modelo)
                //abortar execução
                if (nextElement.isEmpty()) {
                    //caso o resultado seja negativo interromper loop e terminar terminar teste
                    repBody = tstRep.fillReportBody(repBody, "ABORT", false);
                    testRestult = -1;
                    break;
                }
                
                
                
                
                // chamada para teste para TESTING SERVICE (obrigado lucas do passado!)
                if (nextElement.startsWith("e_") || nextElement.startsWith("v_")) {
                    //Não executar instrução pois a geração de teste vai parar
                } else if (tsServHandler.exeServiceOperation(nextElement)) {
                    //incrementando contador
                    counter++;
                    //teste bem sucedido, adicionando informação no report 
                    repBody = tstRep.fillReportBody(repBody, nextElement, true);
                } else {
                    //incrementando contador
                    counter++;
                    //caso o resultado seja negativo interromper loop e terminar terminar teste
                    repBody = tstRep.fillReportBody(repBody, nextElement, false);
                    testRestult = 0;
                    break;
                }
            }
            //verificando tempo necessário para executar loop de execuçao de testes
            long stopTime = System.currentTimeMillis();
            long elapsedTime = stopTime - startTime;
            //finalizando execução do teste
            String executionData = Long.toString(elapsedTime) + "," + Integer.toString(counter) + "," + testRestult;
            finishTestReportAndStopGWService(executionData);
            System.out.println("[MBTestCaseGen]: Test execution data: " + executionData);
            System.out.println("[MBTestCaseGen]: Test finished!");
        } else {
            System.out.println("[MBTestCaseGen] problems loading model to GraphWalker");
        }
        return repHead + repBody;
    }
}
