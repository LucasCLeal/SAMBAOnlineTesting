/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.modelupdater;

import com.unicamp.lucas.common.FileHandler;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;
import org.w3c.dom.Document;

/**
 *
 * @author LucasCLeal
 */
public class DataGatherer {

    static FileHandler flHand = new FileHandler();
    static XmlParser xmlParser = new XmlParser();

    public ArrayList<File> filesAffectedByWSDL(String fileName) {

        ArrayList<File> BPELFilesAffected = new ArrayList<>();
        //procurando por arquivos com o mesmo nome nos diretorios em produção
        flHand.loadFileHandlerPaths();
        //listando pastas dentro da pasta prd
        ArrayList<File> prdFolders = flHand.directoriesAtDirectory(FileHandler.prdPath);
        //varrendo arquivos dentro das pastas em produção
        for (File folder : prdFolders) {
            ArrayList<File> files = flHand.allFilesAtDirectory(folder.toPath());
            //verificando se exsite arquivo com o mesmo nome na pasta
            for (File doc : files) {
                if (doc.getName().equals(fileName)) {
                    BPELFilesAffected.add(folder);
                }
            }
        }
        return BPELFilesAffected;
    }

    public void backUpProductionAtArchiveForFileName(String fileName) throws IOException {

        flHand.loadFileHandlerPaths();
        //executa backup do arquivo e deleta arquivos em produção
        //deleta todos os arquivos 

        Path prdPath = flHand.generateFilePath(FileHandler.prdPath, FilenameUtils.removeExtension(fileName));
        File prd = new File(prdPath.toString());

        System.out.println("[ModelUpdater] New version of BPEL file recieved");
        //backup PRD Files to Archive directory
        flHand.backUpProductionAtArchive(prd, true);

    }

    public void createDirectoriesFornewBPELFile(String fileName) {
        flHand.loadFileHandlerPaths();
        flHand.createNewDirectoriesForBpelFile(fileName);
    }

    public boolean isBPELFileUnderPRD(String fileName) throws IOException {
        flHand.loadFileHandlerPaths();
        //check if ther is a folder at PRD that maches the BPEL file name
        try {
            return flHand.fileOrfolderExist(flHand.generatePathDirForFileName(FileHandler.prdPath, FilenameUtils.removeExtension(fileName)));
        } catch (Exception e) {
            System.err.println("[ModelUpdater] ERROR - BPEL directory not find for file:" + fileName);
            return false;
        }

    }

    public Path getBPELDirectoryPathAtProduction(String fileName) {
        flHand.loadFileHandlerPaths();
        return flHand.generateFilePath(FileHandler.prdPath, FilenameUtils.removeExtension(fileName));
    }

    public File getBPELFile(String fileName) {
        flHand.loadFileHandlerPaths();
        Path prdPath = flHand.generateFilePath(FileHandler.prdPath, FilenameUtils.removeExtension(fileName));
        File prd = new File(prdPath.toString() + "/" + fileName);
        return prd;
    }

    public ArrayList<String> listOfBPELDependencies(File file) {
        //obtendo parser do arquivo
        Document doc = xmlParser.parseFile(file);
        ArrayList<String> partners = xmlParser.getBPELPartnerLinks(doc);

        for (int p = 0; p < partners.size(); p++) {
            System.out.println(partners.get(p));
        }

        //bucando por tags com dados sobre serviços
        //litando BPEL Files
        //Listando dependencias do arquivos
        //verificando depdencias
        return null;
    }

    public ArrayList<String> fetchDependeciesPathListForBpelFile(String fileName) {

        ArrayList<String> files = new ArrayList<>();

        try { // Call Web Service Operation
            testingservice.networkcommunication.TestingWSService service = new testingservice.networkcommunication.TestingWSService();
            testingservice.networkcommunication.TestingWS port = service.getTestingWSPort();
            // TODO initialize WS operation arguments here

            files = (ArrayList<String>) port.getDependenciesPathForBPELName(FilenameUtils.removeExtension(fileName));

        } catch (Exception ex) {
            System.err.println(ex);
        }

        return files;

    }

    public boolean downloadFileWithStringPathToDir(String OriginalPath, Path dir) {

        try { // Call Web Service Operation
            testingservice.networkcommunication.TestingWSService service = new testingservice.networkcommunication.TestingWSService();
            testingservice.networkcommunication.TestingWS port = service.getTestingWSPort();
            
            byte[] result = port.fetchDataFromFilePath(OriginalPath);

            //salvando arquivo 
            flHand.loadFileHandlerPaths();
            //obtendo nome do arquivo a partir do path
            Path original = Paths.get(OriginalPath);
            Path newFile = flHand.generateFilePath(dir, original.getFileName().toString());
            flHand.createNewFileAtPath(newFile);
            flHand.writeDataInToFilePath(newFile, result);
            System.out.println("[ModelUpdater] new file downloaded at:" + newFile.toString());
            return true;

        } catch (Exception ex) {
            System.err.println(ex);
            return false;
        }

    }

    public void backUpProductionAtArchiveForFile(File file) throws IOException {
        flHand.loadFileHandlerPaths();
        flHand.backUpProductionAtArchive(file, false);
    }

    public void updateWSDLFileAtPRDDir(String wsdl, byte[] fileData, File prd) {
        //deletando arquivo antigo com o mesmo nome na produção
        flHand.deleteFileAtPath(flHand.generateFilePath(prd.toPath(), wsdl));
        //copiando arquivo
        //criando copia do arquivo na sua pasta respectiva na produçao
        flHand.createNewFileAtPath(flHand.generateFilePath(prd.toPath(), wsdl));
        //escrevendo conteudo do arquivo
        flHand.writeDataInToFilePath(flHand.generateFilePath(prd.toPath(), wsdl), fileData);
        //baixando dependencias do BPEL file
    }

}
