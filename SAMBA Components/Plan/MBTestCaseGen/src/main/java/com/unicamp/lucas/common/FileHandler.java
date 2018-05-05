/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.common;

import com.unicamp.lucas.config.ServiceConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author LucasCLeal
 */
public class FileHandler {
    
    static String separator = System.getProperty("line.separator");
    //

    //buscar por arquivo
    public boolean fileOrfolderExist(Path filePath) throws IOException {
        return Files.exists(filePath); 
    }

    //removendo ext da string com nome do arquivo
    public String removeFileExt(String fileName){
        return FilenameUtils.removeExtension(fileName);
    }
    
    //gerando path para arquivo
    public Path generateFilePath(Path path, String fileNameWithExt) {
        Path filePath = path.resolve(fileNameWithExt);
        return filePath;
    }
 
    //gerando path para diretorio
    public Path generatePathDirForFileName(Path path, String filename) {
        Path dirPath = path.resolve(FilenameUtils.removeExtension(filename));
        return dirPath;
    }
   
    //obtendo atributos de um arquivo
    public BasicFileAttributes getFileAttributes(Path filePath) throws IOException {
        //dados do arquivo buscado
        BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
        return attr;

    }

    //criar arquivo
    public void createNewFileAtPath(Path path) {

        //Dependendo do sistema operacional e da pasta selecionada o loca do destino dos modelos tem
        //que ser atualizado, ou as permissões escrita na pasta alvo tem que ser atualizadas
        System.out.println("Creating new file...");

        try {
            // Create the empty file with default permissions, etc.
            Files.createFile(path);
            System.out.println("empty file created.");
        } catch (FileAlreadyExistsException x) {
            System.err.format("file named %s"
                    + " already exists%n", path);
        } catch (IOException y) {
            // Some other sort of failure, such as permissions.
            System.err.format("createFile error: %s%n", y);
        }

    }

    public String fileCreationDate(Path file, String separator) throws IOException {
        BasicFileAttributes attr = getFileAttributes(file);
        DateFormat df = new SimpleDateFormat("MM" + separator + "dd" + separator + "yyyy");
        String dateCreated = df.format(attr.creationTime().toMillis());
        return dateCreated;
    }

    public String addCreationDateToFileName(Path file) throws IOException {

        String dateCreated = fileCreationDate(file, "_");
        String fileNameWithOutExt = FilenameUtils.removeExtension(file.getFileName().toString());
        String fileExt = FilenameUtils.getExtension(file.getFileName().toString());
        String newFileName = fileNameWithOutExt + "_" + dateCreated + "." + fileExt;
        System.out.println("backup file name: " + newFileName);
        return newFileName;

    }

    //copiar arquivo para pasta de backup
    public void backupFile(Path originalFile, Path copyDestination) throws IOException {

        //copiar arquivo para nova pasta onde outros backups são guardados
        System.out.println("starting backup");
        //verificando se arquivo existe no local de origem
        try {
            //criando novo nome para backup file, baseado na sua data e hora de modificação
            String newFileName = addCreationDateToFileName(originalFile);
            try {
                //verificando se destino existe, se existir criar backup
                if (fileOrfolderExist(copyDestination)) {
                    System.out.println("folder path exist");
                    Path fileCopyPath = generateFilePath(copyDestination, newFileName);
                    Files.copy(originalFile, fileCopyPath, COPY_ATTRIBUTES);
                    System.out.println("model copied to archive");
                } else {
                    System.out.println("archive file not found, backup aborted.");
                }

            } catch (IOException xin) {
                System.out.println("path to archive folder invalid");
                System.out.println("Exception" + xin);
            }
        } catch (IOException xout) {
            System.out.println("there is no file :" + originalFile.getFileName().toString() + " at root folder");
            System.out.println("Exception" + xout);
        }
    }

    public void deleteFileAtPath(Path filePath) {

        System.out.println("deleteModelFile:" + filePath.toString());

        try {
            Files.delete(filePath);
            System.out.println("done");
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", filePath);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", filePath);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }

    }

    public void writeDataInToFilePath(Path filePath, byte[] data) {

        System.out.println("writeDataInToFilePath: " + filePath.toString());

        try {
            Files.write(filePath, data, StandardOpenOption.APPEND);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            System.err.format("IOException: %s%n", e);
        }

    }
    
    public ArrayList<File> directoriesAtPath(Path rootPath) {

        File folder = new File(rootPath.toString());
        File[] listOfFiles = folder.listFiles();

        //criando lista de arquivos.
        ArrayList<File> modelFiles = new ArrayList<>();

        for (File file : listOfFiles) {
            //caso seja um diretorio, adicionado a lista
            if (file.isDirectory()) {
                modelFiles.add(file);
            }
        }

        return modelFiles;

    }
    
    public ArrayList<File> filesAtPathWithExtension(Path rootPath, String filesExtention) {

        File folder = new File(rootPath.toString());
        File[] listOfFiles = folder.listFiles();

        //criando lista de arquivos.
        ArrayList<File> modelFiles = new ArrayList<>();

        for (File file : listOfFiles) {
            //caso seja um arquivo e tenha a extenção desejada é adicionado a lista
            if (file.isFile() && FilenameUtils.getExtension(file.getName()).equals(filesExtention)) {
                modelFiles.add(file);
            }
        }

        return modelFiles;

    }

    public Path fileAtPathWithName(Path rootPath, String fileName, String filesExtention) {

        File folder = new File(rootPath.toString());
        File[] listOfFiles = folder.listFiles();
        Path filePath = null;
        for (File file : listOfFiles) {
            //caso seja um arquivo e tenha a extenção desejada é adicionado a lista
            if (file.isFile() && FilenameUtils.getBaseName(file.getName()).equals(fileName) && FilenameUtils.getExtension(file.getName()).equals(filesExtention)) {
                filePath = Paths.get(file.getPath());
            }
        }
        return filePath;
    }

    public Path updateFileAndArchive(String fileName, byte[] fileData) throws IOException {

        ServiceConfig.loadConfigurations();
        Path filePath = generateFilePath(Paths.get(ServiceConfig.pathToModels), fileName);

        //caso o arquivo já exista no WS é necessário salvar ele na pasta de archive, caso não exista executar o download normalmente
        if (fileOrfolderExist(filePath)) {
            System.out.println("File already exists exists");
            try {
                backupFile(filePath, Paths.get(ServiceConfig.pathToArchive));
                deleteFileAtPath(filePath);
                writeDataInToFilePath(filePath, fileData);

            } catch (IOException upfail) {
                System.err.format("IOException: %s%n", upfail);
            }
            System.out.println("old model archived and model updated at root folder");
        } else {
            System.out.println("new model");
            writeDataInToFilePath(filePath, fileData);
        }

        return filePath;
    }

    public String File2String(File modelfile) throws FileNotFoundException, IOException {

        //recieves a model file (the content is in JSON format) and returns a string with the content of the file
        String stringModel = "";
        InputStream modelInputStream = new FileInputStream(modelfile);
        InputStreamReader modelReader = new InputStreamReader(modelInputStream);
        BufferedReader br = new BufferedReader(modelReader);
        String line;
        while ((line = br.readLine()) != null) {
            stringModel = stringModel.concat(line + separator);
        }
        return stringModel;

    }

}
