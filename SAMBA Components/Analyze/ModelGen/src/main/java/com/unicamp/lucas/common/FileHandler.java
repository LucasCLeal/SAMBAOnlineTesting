/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unicamp.lucas.common.pojo.Model;
import com.unicamp.lucas.config.ServiceConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
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
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.StyledEditorKit;
import org.apache.commons.io.FilenameUtils;

/**
 *
 * @author LucasCLeal
 */
public class FileHandler {

    //variáveis usadas pela classe
    static String separator = System.getProperty("line.separator");
    static public Path dtPath = Paths.get("");
    static public Path rchvPath = Paths.get("");
    static public Path prdPath = Paths.get("");
    //variaveis usadas pelo servidor REST para conversão de modelos
    static public Path originPath = Paths.get("");
    static public Path targetPath = Paths.get("");

    static boolean fileHandlerloaded = false;

    //obtendo informações do caminho de configuração
    public void loadFileHandlerPaths() {
        if (!fileHandlerloaded) {
            if (ServiceConfig.loadConfigurations()) {
                rchvPath = Paths.get(ServiceConfig.pathToArchive);
                dtPath = Paths.get(ServiceConfig.pathToData);
                prdPath = Paths.get(ServiceConfig.pathToProduction);

                originPath = Paths.get(ServiceConfig.pathToOrigin);
                targetPath = Paths.get(ServiceConfig.pathToTarget);

                fileHandlerloaded = true;
            }
        }
    }

    //buscar por arquivo
    public boolean fileOrfolderExist(Path filePath) throws IOException {
        //System.out.println("[ModelUpdater] checking for: " + filePath.toString());
        return Files.exists(filePath);
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
    public String getFileNameWithOutExt(File file){ 
        return FilenameUtils.removeExtension(file.getName());
    }
    

    //obtendo atributos de um arquivo
    public BasicFileAttributes getFileAttributes(Path filePath) throws IOException {
        //dados do arquivo buscado
        BasicFileAttributes attr = Files.readAttributes(filePath, BasicFileAttributes.class);
        //System.out.println("creationTime: " + attr.creationTime());
        //System.out.println("size: " + attr.size());
        return attr;

    }

    public void createNewDirectoriesForBpelFile(String fileName) {
        //Dependendo do sistema operacional e da pasta selecionada o loca do destino dos modelos tem
        //que ser atualizado, ou as permissões escrita na pasta alvo tem que ser atualizadas
        System.out.println("[ModelUpdater]: Creating SRC and RCHV folders for new file");

        try {
            // Create the empty file with default permissions, etc.
            Files.createDirectory(generatePathDirForFileName(prdPath, fileName));
            Files.createDirectories(generatePathDirForFileName(rchvPath, fileName));
            System.out.println("[ModelUpdater]: empty Directories created.");

        } catch (IOException y) {
            // Some other sort of failure, such as permissions.
            System.err.format("[ModelUpdater]: createDirectory error %s%n", y);
        }

    }

    public Path createNewArchiveDirectoryForFile(String fileName) {
        System.out.println("[ModelUpdater]: Creating new archive folder for file: " + fileName);

        try {
            Path rchv = generatePathDirForFileName(rchvPath, fileName);
            Path newArchive = generatePathDirForFileName(rchv, currentDateandTime());
            Files.createDirectories(newArchive);

            return newArchive;
        } catch (Exception e) {
            System.err.println(e);
            return null;
        }

    }

    //criar arquivo
    public void createNewFileAtPath(Path path) {

        //Dependendo do sistema operacional e da pasta selecionada o loca do destino dos modelos tem
        //que ser atualizado, ou as permissões escrita na pasta alvo tem que ser atualizadas
        //System.out.println("Creating new file...");
        try {
            // Create the empty file with default permissions, etc.
            Files.createFile(path);
            //System.out.println("empty file created.");
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
        DateFormat df = new SimpleDateFormat("dd" + separator + "MM" + separator + "yyyy");
        String dateCreated = df.format(attr.creationTime().toMillis());
        return dateCreated;
    }

    public String currentDateandTime() {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("dd" + "_" + "MM" + "_" + "yyyy" + "@" + "HH" + "-" + "mm");
        String actualDate = df.format(date);
        System.out.println(actualDate);
        return actualDate;
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
    public void backupFile(File file, Path copyDestination) throws IOException {

        //copiar arquivo para nova pasta onde outros backups são guardados
        //verificando se arquivo existe no local de origem
        try {
            //verificando se destino existe, se existir criar backup
            if (fileOrfolderExist(copyDestination)) {
                //System.out.println("folder path exist");
                Path fileCopyPath = generateFilePath(copyDestination, file.getName());
                Files.copy(file.toPath(), fileCopyPath, COPY_ATTRIBUTES);
                //System.out.println("file copied to archive");
            } else {
                System.out.println("[ModelUpdater]: archive file:" + file.getName() + " not found, backup aborted.");
            }

        } catch (IOException xin) {
            System.out.println("path to archive folder invalid");
            System.out.println("Exception" + xin);
        }

    }

    public void deleteFileAtPath(Path filePath) {

        try {
            Files.delete(filePath);
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

        try {
            Files.write(filePath, data, StandardOpenOption.APPEND);
        } catch (IOException e) {
            //exception handling left as an exercise for the reader
            System.err.format("IOException: %s%n", e);
        }

    }

    public void writeJsonInToFileAtPath(Path filePath, Model mdl) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            //Object to JSON in file
            mapper.writeValue(filePath.toFile(), mdl);
        } catch (IOException ex) {
            Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public ArrayList<File> allFilesAtDirectory(Path directory) {
        File folder = new File(directory.toString());
        File[] listOfFiles = folder.listFiles();
        //criando lista de arquivos.
        ArrayList<File> files = new ArrayList<>();

        for (File file : listOfFiles) {
            //caso seja um arquivo e tenha a extenção desejada é adicionado a lista
            if (file.isFile()) {
                files.add(file);
            }
        }
        return files;
    }

    public ArrayList<File> filesAtDirectory(Path directory) {
        File folder = new File(directory.toString());
        File[] listOfFiles = folder.listFiles();
        //criando lista de arquivos.
        ArrayList<File> files = new ArrayList<>();

        for (File file : listOfFiles) {
            //caso seja um arquivo e tenha a extenção desejada é adicionado a lista
            if (file.isFile()) {
                files.add(file);
            }
        }
        return files;
    }

    public ArrayList<File> directoriesAtDirectory(Path directory) {

        File dir = new File(directory.toString());
        File[] list = dir.listFiles();

        //criando lista de pastas.
        ArrayList<File> folders = new ArrayList<>();

        for (File file : list) {
            if (file.isDirectory()) {
                folders.add(file);
            }
        }
        return folders;
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

    public void backUpProductionAtArchive(File file, boolean andDelete) throws IOException {

        if (!fileHandlerloaded) {
            loadFileHandlerPaths();
        }

        //verificando é um arquivo ou diretorio
        if (file.isFile()) {
            System.out.println("[ModelUpdater]: Startin backup for file: " + file.getName());

        } else {
            System.out.println("[ModelUpdater]: Startin backup for directory: " + file.getName());
        }

        //criar nova pasta com data da mudança dentro do diretorio respectivo do arquivo
        Path newArchive = createNewArchiveDirectoryForFile(file.getName());
        //listar todos os arquivos na pasta PRD
        ArrayList<File> listOfFiles = filesAtDirectory(generatePathDirForFileName(prdPath, FilenameUtils.removeExtension(file.getName())));
        //copiando e deletando todos os arquivo da PRD para ARCHV
        for (File fl : listOfFiles) {
            try {
                backupFile(fl, newArchive);
            } catch (IOException ex) {
                Logger.getLogger(FileHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (andDelete) {
                deleteFileAtPath(fl.toPath());
            }
        }
        System.out.println("[ModelUpdater]: Finished backup for directory: " + file.getName());

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
