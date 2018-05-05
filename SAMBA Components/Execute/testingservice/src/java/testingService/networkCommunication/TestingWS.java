/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.networkCommunication;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import javax.jws.Oneway;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.time.StopWatch;
import testingService.Layers;
import testingService.Main;
import testingService.config.ConfigParameters;
import testingService.modelBasedTest.TestCaseGenHandler;

/**
 *
 * @author Andrea
 */
@WebService()
public class TestingWS {

    int numberOfFileTMP = 0;
    int numberOfFileTotal = 0;
    static boolean isstarted = false;
    //add by lucasCLeal
    static TestCaseGenHandler mbTsHandler = new TestCaseGenHandler();

    @WebMethod(operationName = "startTestingService")
    public String startTestingService() {
        if (isstarted == true) {
            return "testing service already started";
        }
        isstarted = true;
        Main.startTestingEngine();
        return "testing service started";
    }

    /**
     *
     * @param address
     * @param numero di file
     * @return
     */
    @WebMethod(operationName = "sendUpdateNumberOfBpelFiles")
    public boolean sendUpdateNumberOfBpelFiles(@WebParam(name = "machineIP") String machineIP,
            @WebParam(name = "nameBpelServer") String nameBpelServer, @WebParam(name = "numberOfFile") int numberOfFile) {
        numberOfFileTMP = 0;
        numberOfFileTotal = numberOfFile;
        return true;
    }

    @WebMethod(operationName = "sendUpdateBpelFiles")
    public boolean sendUpdateBpelFiles(@WebParam(name = "machineIP") String machineIP,
            @WebParam(name = "nameBpelServer") String nameBpelServer,
            @WebParam(name = "bpelFile") String bpelFile) {
        Layers.engine.bpelFile[numberOfFileTMP] =
                new File(ConfigParameters.storedBpelFile + "/bpelFile" + numberOfFileTMP + ".bpel");
        try {
            FileUtils.writeStringToFile(Layers.engine.bpelFile[numberOfFileTMP], bpelFile);
            numberOfFileTMP++;
        } catch (IOException ex) {
            System.out.println("T_SERVICE: Problems writing bpel file " + Layers.engine.bpelFile[numberOfFileTMP]);
        }
        //tutti i file bpel nuovi sono stati ricevuti, la trasmissione dei file termina
        if (numberOfFileTMP == numberOfFileTotal) {
            Layers.engine.receivedModifiedBPELfiles(numberOfFileTotal, machineIP, nameBpelServer);
            numberOfFileTMP = 0;
        }
        return true;
    }

    /**
     * actually, does nothing. to stop, try stopping the server, or killing the thread, or undeploy...
     */
    @WebMethod(operationName = "stop")
    @Oneway
    public void stop() {
    }

    /**
     * signal that a controlled service changed
     * @param name of the Service that is controlled, written http://....?wsdl
     * @param lista dei servizi coinvolti, separata da ",":
     * esempio: http://....?wsdl, http://......?wsdl
     * @return
     */
    @WebMethod(operationName = "controlServiceHasChanged")
    public boolean controlChanged(@WebParam(name = "contrService") String nameControlledService, @WebParam(name = "listaServizi") String listOfService) {
        Layers.engine.controlledIsModified(nameControlledService, listOfService);
        return true;
    }

    /**
     * Web service operation
     * ADDED BY LucasCLeal for SAMBA-TS
     */
    @WebMethod(operationName = "exeTestInstruction")
    public boolean exeTestInstruction(@WebParam(name = "operation") String operation) {
        System.out.println("[TestService] exeTestInstruction" + operation);
        return mbTsHandler.performTestOperation(operation);
    }

     /**
     * Web service operation
     * ADDED BY LucasCLeal for SAMBA-TS
     */
    @WebMethod(operationName = "resetTestVariables")
    public void resetTestVariables() {
        System.out.println("[TestService] reseting variables");
        mbTsHandler.resetTestCaseGenVariables();
    }




    /**
     * Web service operation
     * ADDED BY LucasCLeal for SAMBA-TS
     */
    @WebMethod(operationName = "getDependenciesPathForBPELName")
    public ArrayList<String> getDependenciesPathForBPELName(@WebParam(name = "bpelName") String bpelName) {

        System.out.println("[TestService] getDependenciesPathForBPELName");
        //RECEBENDO NOME DO ARQUIVO BPEL
        //GERANDO PATH PARA ROOT E PARTNERS
        String srvAssemblies = "/home/andrea/SUNWappserver/domains/domain1/jbi/service-assemblies/";
        String dirPath = srvAssemblies + bpelName + "/" + bpelName + "-" + bpelName + "/sun-bpel-engine";
        String partnerDir = dirPath + "/Partners";

        File root = new File(dirPath);
        File partners = new File(partnerDir);

        //criando lista vazia de strings
        ArrayList<String> listOfFiles = new ArrayList<String>();

        //Obtendo lista de arquivos, filtrando para remover referencias a pastas.
        File[] rootFiles = root.listFiles();
        //Adicionando nomes dos arquivos a lista
        for (File file : rootFiles) {
            if (file.isFile()) {
                listOfFiles.add(file.getPath());
            }

        }

        //Obtendo lista de arquivos, filtrando para remover referencias a pastas.
        File[] partnersFiles = partners.listFiles();
        //Adicionando nomes dos arquivos a lista
        for (File file : partnersFiles) {
            if (file.isFile()) {
                listOfFiles.add(file.getPath());
            }

        }
        return listOfFiles;
    }

    /**
     * Web service operation
     * ADDED BY LucasCLeal for SAMBA-TS
     */
    @WebMethod(operationName = "fetchDataFromFilePath")
    public byte[] fetchDataFromFilePath(@WebParam(name = "filePath") String filePath) throws FileNotFoundException, IOException {

        System.out.println("[TestService] fetchDataFromFilePath");
        //carrengando file a partir do path recebido
        File file = new File(filePath);
        RandomAccessFile f = new RandomAccessFile(file, "r");
        if (f.length() > Integer.MAX_VALUE) {
            throw new IOException("File is to large");
        }
        byte[] data = new byte[(int) f.length()];
        f.readFully(data);
        if (f.getFilePointer() != f.length()) {
            throw new IOException("File length changed while reading");
        }
        return data;
    }

    /**
     * Web service operation
     * ADDED BY LucasCLeal for SAMBA-TS
     */
    @WebMethod(operationName = "startOnlineModelBasedTestforBpel")
    public void startOnlineModelBasedTestforBpel(@WebParam(name = "bpelName") String bpelName) {
        System.out.println("[TestService] Starting Online ModelBased Test for service composition");
        mbTsHandler.executeMbTestForBepel(bpelName);

    }
}
