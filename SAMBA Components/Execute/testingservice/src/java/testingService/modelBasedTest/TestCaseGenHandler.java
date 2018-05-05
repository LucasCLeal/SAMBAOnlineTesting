/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.modelBasedTest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.time.StopWatch;

/**
 *
 * @author Lucas Leal
 */
public class TestCaseGenHandler {

    //IMAGE SCRAPER
    static PictureSetServClient pictureCli = new PictureSetServClient();
    static PartnerKeysServClient partnerCli = new PartnerKeysServClient();
    static FlickerClient flCli = new FlickerClient();
    static PicasaClient pksCli = new PicasaClient();
    static ImageScraperVariables ISvariables = new ImageScraperVariables();
    //FEEDREADER
    static RssReaderClient RssCli = new RssReaderClient();
    static FeedRegistryClient feedCli = new FeedRegistryClient();
    static FeedReaderVariables FDvariables = new FeedReaderVariables();
    //HYPERTIMETABLE
    static HyperCacheClient HCCCli = new HyperCacheClient();
    static HyperReaderClient HRCli = new HyperReaderClient();
    static HyperTimeTableVariables HTTBvariables = new HyperTimeTableVariables();
    //control
    boolean variablesLoaded = false;

    public void executeMbTestForBepel(String bpelName) {

        //para guardar dados sobre a execucao
        StopWatch timer = new StopWatch();
        Integer counter = 0;
        timer.reset();

        try {
            if (checkMBtestCaseGenAvailability()) {
                System.out.println("[TestService] test case generator available.");
                if (startTestGenerator(bpelName)) {
                    System.out.println("[TestService] model loaded and ready");
                    //loadign IMAGESCRAPER VAR
                    ISvariables.resetVariables();
                    ISvariables.bpelStartAssingVariables();
                    System.out.println("[TestService] orchestration IMAGESCRAPPER variables ready");
                    //loadign HYPERTIME TABLE VAR
                    //HTTBvariables.resetVariables();
                    //HTTBvariables.bpelStartAssingVariables();
                    //loadign FEED READER VAR
                    //starting timer
                    timer.start();
                    //definindo tamanho maximo do caso de teste
                    System.out.println("[TestService] Max test case size defined: 100");
                    for (int i = 0; i < 100; i++) {
                        boolean result = false;
                        if (i == 99) {
                            //timeout
                            System.out.println("[TestService] Max testcase size reached");
                            //parando timer e enviando tempo de execucao para servidor
                            timer.stop();
                            //formantendo string para ser adiciona ao relatorio
                            String ln = Long.toString(timer.getTime()) + "," + Integer.toString(counter);
                            abortTestCaseGen(ln);
                            System.out.println("[TestService] Abrting test case generation");
                            break;
                        } else {
                            String next = getNextStep();
                            if (next == null) {
                                System.out.println("[TestService] Stop condition reached");
                                //parando timer e enviando tempo de execucao para servidor
                                timer.stop();
                                //formantendo string para ser adiciona ao relatorio
                                String ln = Long.toString(timer.getTime()) + "," + Integer.toString(counter);
                                stopTestCaseGen(ln);
                                System.out.println("[TestService] Test execution finished");
                                break;
                            } else {
                                //verificando proximo passo
                                if (isItNotReserverWord(next)) {
                                    //incrementando o counter (contador de opera'coes executadas)
                                    counter++;
                                    //QUEBRANDO STRING EM WS e OP
                                    String[] data = next.split("@");
                                    String op = data[0];
                                    String ws = data[1];
                                    //passando para a grande gambiarra do mundo
                                    //FAZENDO O TESTE DA OPERACAO
                                    if (ws.equals("helper")) {
                                        result = pictureCli.performOperation(op, ISvariables);
                                    } else if (ws.equals("key_registry")) {
                                        result = partnerCli.performOperation(op, ISvariables);
                                    } else if (ws.equals("flickr")) {
                                        //} else if (ws.equals("fotos")) {
                                        result = flCli.performOperation(op, ISvariables);
                                    } else if (ws.equals("picasa")) {
                                        result = pksCli.performOperation(op, ISvariables);
                                    } else {
                                        System.out.println("[TestService] NO PartnerLink named: " + ws + " found!");
                                        result = false;
                                    }
                                    System.out.println("[TestService]: " + op + "@" + ws + " test is " + result);

                                    if (!result) {
                                        //caso o resultado seja negativo
                                        //enviando resposta e parando geracao de caso de teste
                                        returWsOperationResult(next, result);
                                        System.out.println("[TestService] Fail to continue with testcase");
                                        //parando timer e enviando tempo de execucao para servidor
                                        timer.stop();
                                        //formantendo string para ser adiciona ao relatorio
                                        String ln = Long.toString(timer.getTime()) + "," + Integer.toString(counter);
                                        stopTestCaseGen(ln);
                                        System.out.println("[TestService] Test case generation stop. ");
                                        break;

                                    }
                                    //guardando o resultado
                                    returWsOperationResult(next, result);
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(TestCaseGenHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean performTestOperation(String nextOp) {

        boolean result = false;

        //Starting ORACLES.
        if (!variablesLoaded) {
            //iniciando variaveis para compositions
            ISvariables.bpelStartAssingVariables();
            HTTBvariables.bpelStartAssingVariables();
            FDvariables.bpelStartAssingVariables();
            variablesLoaded = true;
        }

        if (isItNotReserverWord(nextOp)) {
            //So esta funcionando para o ImageScraper

            //incrementando o counter (contador de opera'coes executadas)
            //QUEBRANDO STRING EM WS e OP
            String[] data = nextOp.split("@");
            String op = data[0];
            String ws = data[1];
            //passando para a grande gambiarra do mundo
            //FAZENDO O TESTE DA OPERACAO
            //IMAGESCRAPER
            if (ws.equals("helper")) {
                result = pictureCli.performOperation(op, ISvariables);
            } else if (ws.equals("key_registry")) {
                result = partnerCli.performOperation(op, ISvariables);
            } else if (ws.equals("flickr")) {
                //} else if (ws.equals("fotos")) {
                result = flCli.performOperation(op, ISvariables);
            } else if (ws.equals("picasa")) {
                result = pksCli.performOperation(op, ISvariables);

            //FEED READER
            } else if (ws.equals("feedRegistry")) {
                result = feedCli.performOperation(op, FDvariables);
            } else if (ws.equals("rssReader")) {
                result = RssCli.performOperation(op, FDvariables);

            //HYPERTIMETABLE
            } else if (ws.equals("hypercache")) {
                result = HCCCli.performOperation(op, HTTBvariables);
            } else if (ws.equals("hyperreader")) {
                result = HRCli.performOperation(op, HTTBvariables);

            //any other
            } else {
                System.out.println("[TestService] NO PartnerLink named: " + ws + " found, test: " + result);
                result = false;
            }
            System.out.println("[TestService]: " + op + "@" + ws + " test is " + result);
        }
        return result;

    }

    public void resetTestCaseGenVariables(){

        ISvariables.resetVariables();
        HTTBvariables.resetVariables();
        FDvariables.resetVariables();
        variablesLoaded = false;

    }



    private boolean checkMBtestCaseGenAvailability() throws IOException {
        try {
            URL servUrl = new URL("http://192.168.56.1:8080/MBTestCaseGen/");

            URLConnection servUrlc = servUrl.openConnection();
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(
                    servUrlc.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                //System.out.println(inputLine);
            }
            in.close();
            return true;

        } catch (MalformedURLException ex) {
            Logger.getLogger(TestCaseGenHandler.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }

    }

    private boolean isItNotReserverWord(String next) {

        //verificando se o conteudo da resposta e ou nao um passo a ser executado
        if (next.startsWith("e_") || next.startsWith("v_")) {
            return false;
        } else {
            return true;
        }

    }

    //METODOS USADOS PARA COMUNICACAO COM MBTestCaseGen
    private boolean startTestGenerator(String bpelModel) {

        try { // Call Web Service Operation
            com.unicamp.lucas.testcasegen.MBTestCaseGen_Service service = new com.unicamp.lucas.testcasegen.MBTestCaseGen_Service();
            com.unicamp.lucas.testcasegen.MBTestCaseGen port = service.getMBTestCaseGenPort();

            // TODO process result here
            return port.startTestCaseGenForBpelModelNamed(bpelModel);
        } catch (Exception ex) {
            System.err.println("[TestService] " + ex);
            return false;
        }
    }

    private String getNextStep() {
        try { // Call Web Service Operation
            com.unicamp.lucas.testcasegen.MBTestCaseGen_Service service = new com.unicamp.lucas.testcasegen.MBTestCaseGen_Service();
            com.unicamp.lucas.testcasegen.MBTestCaseGen port = service.getMBTestCaseGenPort();
            // TODO process result here
            return port.getNextStepForBpelModelNamed();
        } catch (Exception ex) {
            System.err.println("[TestService] " + ex);
            return null;
        }
    }

    private void returWsOperationResult(String testOp, boolean resultado) {

        try { // Call Web Service Operation
            com.unicamp.lucas.testcasegen.MBTestCaseGen_Service service = new com.unicamp.lucas.testcasegen.MBTestCaseGen_Service();
            com.unicamp.lucas.testcasegen.MBTestCaseGen port = service.getMBTestCaseGenPort();
            // TODO initialize WS operation arguments here
            port.addExeResulttoTestReport(testOp, resultado);
        } catch (Exception ex) {
            System.err.println("[TestService] " + ex);
        }
    }

    private void stopTestCaseGen(String data) {

        try { // Call Web Service Operation
            com.unicamp.lucas.testcasegen.MBTestCaseGen_Service service = new com.unicamp.lucas.testcasegen.MBTestCaseGen_Service();
            com.unicamp.lucas.testcasegen.MBTestCaseGen port = service.getMBTestCaseGenPort();
            // TODO initialize WS operation arguments here
            java.lang.String testeExeData = data;
            port.finishtestCaseGeneration(testeExeData);
        } catch (Exception ex) {
            System.err.println("[TestService] " + ex);
        }
    }

    private void abortTestCaseGen(String data) {

        try { // Call Web Service Operation
            com.unicamp.lucas.testcasegen.MBTestCaseGen_Service service = new com.unicamp.lucas.testcasegen.MBTestCaseGen_Service();
            com.unicamp.lucas.testcasegen.MBTestCaseGen port = service.getMBTestCaseGenPort();
            // TODO initialize WS operation arguments here
            java.lang.String testeExeData = data;
            port.abortTestCaseGen(testeExeData);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
        }


    }
}
