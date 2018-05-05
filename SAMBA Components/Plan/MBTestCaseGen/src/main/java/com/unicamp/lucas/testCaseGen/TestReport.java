 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.testCaseGen;

import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.common.pojo.Model;
import com.unicamp.lucas.common.pojo.ModelData;
import com.unicamp.lucas.config.ServiceConfig;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FilenameUtils;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author LucasCLeal
 */
public class TestReport {

    //configuracoes para criacao de arquivos de relatorio
    static String ext = ".txt";
    static String ext2 = ".csv";
    static String separator = System.getProperty("line.separator");
    static Charset charset = Charset.forName("US-ASCII");
    static FileHandler flHand = new FileHandler();
    static ObjectMapper mapper = new ObjectMapper(); // can reuse, share globaly

    public Path lookUpTestReportFile(String modelFileName) {

        ServiceConfig.loadConfigurations();

        String reportName = modelFileName + ext;

        Path reportPath = flHand.generateFilePath(Paths.get(ServiceConfig.pathToReports), reportName);
        try {
            if (!flHand.fileOrfolderExist(reportPath)) {
                //criar arquivo caso não exista
                flHand.createNewFileAtPath(reportPath);
            }
        } catch (IOException ex) {
            Logger.getLogger(TestReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reportPath;

    }

    public Path lookUpTestStatFile(String modelFileName) {

        ServiceConfig.loadConfigurations();

        String statName = "st_" + modelFileName + ext2;

        Path statPath = flHand.generateFilePath(Paths.get(ServiceConfig.pathToReports), statName);
        
        try {
            if (!flHand.fileOrfolderExist(statPath)) {
                //criar arquivo caso não exista
                flHand.createNewFileAtPath(statPath);
            }
        } catch (IOException ex) {
            Logger.getLogger(TestReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return statPath;

    }

    public void updateTestReportFile(String modelName, String reportData) {
        //escreve conteúdo no arquivo de relatório
        //removendo extenção do modelo
        String modelNameWithOutExt = FilenameUtils.removeExtension(modelName);
        //buscando por relatório com o mesmo nome
        Path reportPath = lookUpTestReportFile(modelNameWithOutExt);
        //escrevendo conteudo do teste no arquivo
        flHand.writeDataInToFilePath(reportPath, reportData.getBytes(charset));

    }

    public void updateTestStatFile(String modelName, String data) {
        //escreve conteúdo no arquivo de relatório com dados para statistica
        //removendo extenção do modelo
        String modelNameWithOutExt = FilenameUtils.removeExtension(modelName);
        //buscando por relatório com o mesmo nome 
        Path statPath = lookUpTestStatFile(modelNameWithOutExt);
        //formating line
        data = data.concat(separator);
        //escrevendo conteudo do teste no arquivo
        flHand.writeDataInToFilePath(statPath, data.getBytes(charset));
    }

    public String generateReportHeader(File modelfile) {
        //data e hora de execução
        Calendar caldt = Calendar.getInstance();

        String header = separator + separator + "___TEST EXECUTION INFO___ ";

        header = header.concat("Test execution date and time: " + caldt.getTime().toString());
        header = header.concat(System.getProperty("line.separator"));
        header = header.concat("Model used for test generation: " + modelfile.getName());
        header = header.concat(System.getProperty("line.separator"));

        try {
            String date = flHand.fileCreationDate(Paths.get(modelfile.getPath()), "/");
            header = header.concat("Model creation date: " + date);
            header = header.concat(System.getProperty("line.separator"));
        } catch (IOException ex) {
            System.out.println("Error while reading file: " + modelfile.getPath());
            header = header.concat("ERROR: no Model creation date found");
        }

        //modelo utilizado para elaboração do teste, data de criação do modelo
        //configuração do gerador de caminhos
        try {
            String jsonModel = flHand.File2String(modelfile);
            Model model = mapper.readValue(jsonModel, Model.class);
            mapper.configure(org.codehaus.jackson.map.DeserializationConfig.Feature.FAIL_‌​ON_UNKNOWN_PROPERTIE‌​S, false);
            List<ModelData> models = model.getModels();
            for (ModelData mdl : models) {
                header = header.concat("Path generator: " + mdl.getGenerator() + separator);
            }
        } catch (IOException ex) {
            System.out.println("Error while parsing file: " + ex);
            header = header.concat("ERROR: No path determined at model file");
        }

        return header;
    }

    public String fillReportBody(String reportBody, String test, boolean result) {

        if (reportBody.equals("") || reportBody.isEmpty()) {
            reportBody = reportBody.concat("___TEST CASE GENERATED___" + separator);
        }
        if (result) {
            return reportBody.concat(test + " : SUCCESS" + separator);
        } else if (test.startsWith("e_") || test.startsWith("v_")) {
            return reportBody;
        } else {
            if(test.equals("ABORT")){
                return reportBody.concat("Test case generation ABORTED" + " (Check Server log for information)" + separator);
            } else {
                return reportBody.concat(test + " : FAIL" + separator);
            }
        }
    }

}
