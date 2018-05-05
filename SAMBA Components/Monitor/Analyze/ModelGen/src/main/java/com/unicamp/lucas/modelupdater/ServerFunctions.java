/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.modelupdater;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.common.pojo.Edge;
import com.unicamp.lucas.common.pojo.Model;
import static com.unicamp.lucas.modelupdater.DataGatherer.flHand;
import static com.unicamp.lucas.modelupdater.ModelUpdater.svFunc;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;

/**
 *
 * @author LucasCLeal
 */
public class ServerFunctions {

    static FileHandler flHand = new FileHandler();
    static DataGatherer dtGather = new DataGatherer();
    static XmlParser xmlParser = new XmlParser();
    static ModelGenerator mdGenerator = new ModelGenerator();

    public void recievedWsdlFile(String WSDLName, byte[] WSDLData) {

        //buscando BPEL files afetados por arquivo
        ArrayList<File> files = dtGather.filesAffectedByWSDL(WSDLName);
        //caso existam arquivos, é necessário iniciar o backup de todos
        if (!files.isEmpty()) {
            System.out.println("[ServerFunctions]: there are: " + files.size() + " Bpel Process that need to be updaded.");
            for (File fl : files) {
                try {
                    dtGather.backUpProductionAtArchiveForFile(fl);
                    dtGather.updateWSDLFileAtPRDDir(WSDLName, WSDLData, fl);
                } catch (Exception e) {
                    System.err.println(e);
                }
            }

        } else {
            System.out.println("[ServerFunctions]: No Bpel Process affected by " + WSDLName + " updade.");
        }

    }

    public void bpelFileUpdate(String fileName) throws IOException {
        //buscando se arquivo esta em produção
        if (dtGather.isBPELFileUnderPRD(fileName)) {
            System.out.println("[ServerFunctions]: Dir for Bpel file Found. ");
            dtGather.backUpProductionAtArchiveForFileName(fileName);
        } else {
            System.out.println("[ServerFunctions]: Creating new Dir for Bpel");
            dtGather.createDirectoriesFornewBPELFile(fileName);
        }
        System.out.println("[ServerFunctions]: Downloading BPEL depencencies");
        //recebendo lista de dependencias do TestingService {HARD CODED NOT COOL}{Chamando direito do servidor ne não do repositorio original}
        ArrayList<String> pathList = dtGather.fetchDependeciesPathListForBpelFile(FilenameUtils.removeExtension(fileName));
        //fazendo DownLoad das dependencias
        for (String path : pathList) {
            System.out.println(path);
            dtGather.downloadFileWithStringPathToDir(path, dtGather.getBPELDirectoryPathAtProduction(fileName));
        }
        System.out.println("[ServerFunctions] UpLoad BPEL and Dependencies files complete");

    }


    public String generateModelFromBpelFile(String fileName, Path originPath, Path targetPath) {

        System.out.println("[ServerFunctions] loading BPEL file for model genaration");
        //recebe o nome do arquivo, a sua localização e onde o modelo deve ser guardado
        //caso já existe um arquivo de modelo como o mesmo nome o arquivo é sobreescrito
        
        flHand.loadFileHandlerPaths();
        Path bpFilePath = flHand.fileAtPathWithName(FileHandler.originPath, fileName, "bpel");
        //pré processando o arquivo antes da conversão
        Document bplDoc = xmlParser.parseFile(bpFilePath.toFile());
        //preparando array para receber sequencia
        ArrayList<ArrayList<ArrayList<Edge>>> sqnc = new ArrayList<>();
        sqnc.clear();
        sqnc = xmlParser.getBPELSequence(bplDoc);
        //gerando modelo
        mdGenerator.createMoldelForBpelFile(bpFilePath.toFile().getName(), sqnc);
        try {
            //salvando o modelo
            System.out.println("[ServerFunctions] Model created for bpelfile: " + fileName);
            return mdGenerator.savingModelToTargertDir(fileName, targetPath);
        } catch (IOException ex) {
            System.out.println("[ServerFunctions]: problems creating model file" + fileName + " - " + ex);
            return "problems creating model file" + fileName + " - " + ex;
        }
    }
    
    public String generateModelFromBpelFilePRD(String fileName) {

        System.out.println("[ServerFunctions] loading BPEL file for model genaration");
        
        flHand.loadFileHandlerPaths();
        
        Path prdBPELPath = flHand.generatePathDirForFileName(FileHandler.prdPath, FilenameUtils.removeExtension(fileName));
        Path bpFilePath = flHand.fileAtPathWithName(prdBPELPath, fileName, "bpel");
        //pré processando o arquivo antes da conversão
        Document bplDoc = xmlParser.parseFile(bpFilePath.toFile());
        //preparando array para receber sequencia
        ArrayList<ArrayList<ArrayList<Edge>>> sqnc = new ArrayList<>();
        sqnc.clear();
        sqnc = xmlParser.getBPELSequence(bplDoc);
        //gerando modelo
        mdGenerator.createMoldelForBpelFile(bpFilePath.toFile().getName(), sqnc);
        try {
            //salvando o modelo
            System.out.println("[ServerFunctions] Model created for bpelfile: " + fileName);
            return mdGenerator.savingModelToTargertDir(fileName, prdBPELPath);
        } catch (IOException ex) {
            System.out.println("[ServerFunctions]: problems creating model file" + fileName + " - " + ex);
            return "problems creating model file" + fileName + " - " + ex;
        }
    }
    
    
    

    public String generateMoldesFromFolder(Path originPath, Path targetPath) {

        flHand.loadFileHandlerPaths();
        ArrayList<File> bpelFiles = flHand.filesAtPathWithExtension(originPath, "bpel");

        int succ = 0;
        String reportSuc = "[ServerFunctions] Models Created:";

        int fail = 0;
        String reporFail = "[ServerFunctions] Problems with bpel files:";
        reporFail = reporFail.concat(System.getProperty("line.separator"));

        for (File bpFile : bpelFiles) {
            //pré processando o arquivo antes da conversão
            Document bplDoc = xmlParser.parseFile(bpFile);
            //preparando array para receber sequencia
            ArrayList<ArrayList<ArrayList<Edge>>> sqnc = new ArrayList<>();
            sqnc.clear();
            sqnc = xmlParser.getBPELSequence(bplDoc);
            //gerando modelo
            mdGenerator.createMoldelForBpelFile(bpFile.getName(), sqnc);
            try {
                //salvando o modelo
                String mdName = mdGenerator.savingModelToTargertDir(flHand.getFileNameWithOutExt(bpFile), targetPath);
                succ++;
            } catch (IOException ex) {
                System.out.println("[ServerFunctions]: problems creating model file: " + ex);
                fail++;
                reporFail = reporFail.concat(bpFile.getName());
                reporFail = reporFail.concat(System.getProperty("line.separator"));
            }
        }
        
        String result;
        //escrevendo MSG de resposta
        if(bpelFiles.size() == succ){
            result = "All " +Integer.toString(succ) + " were converted.";
        }else if (bpelFiles.size() == fail) {
            result = "It was not possible to save the models to the target Dir or";
            result = result.concat(System.getProperty("line.separator"));
            result = result.concat("there was a problem with the files origin Dir.");
        }else{
            result = "There was a problem with " + Integer.toString(fail) + "model creations";
            result = result.concat(System.getProperty("line.separator"));
            result = result.concat("No model was created for these files:");
            result = result.concat(System.getProperty("line.separator"));
            result = result.concat(reporFail);
        
        }
        return result;
    }

}
