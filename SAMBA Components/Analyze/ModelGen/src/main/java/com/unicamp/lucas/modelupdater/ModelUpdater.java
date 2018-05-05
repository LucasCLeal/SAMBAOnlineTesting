/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.modelupdater;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import com.unicamp.lucas.common.FileHandler;
import com.unicamp.lucas.config.ServiceConfig;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import javafx.scene.chart.PieChart;
import org.apache.commons.io.FilenameUtils;
import com.unicamp.lucas.modelupdater.DescriptionAnalyser;
import com.unicamp.lucas.testcasegen.MBTestCaseGen_Service;
import javax.xml.ws.WebServiceRef;

/**
 *
 * @author LucasCLeal
 */
@WebService(serviceName = "ModelUpdater")
public class ModelUpdater {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/localhost_8080/MBTestCaseGen/MBTestCaseGen.wsdl")
    private MBTestCaseGen_Service service;

    static ServerFunctions svFunc = new ServerFunctions();
    static DescriptionAnalyser descAna = new DescriptionAnalyser();

    /**
     * Operação de Web service
     *
     * @param fileName
     * @param fileData
     * @return
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "wsdlUpLoad")
    public boolean wsdlUpDate(@WebParam(name = "fileName") String WSDLName, @WebParam(name = "fileData") byte[] WSDLData) throws IOException {

        System.out.println("[ModelUpdater]: " + WSDLName + " updated");
        boolean out;
        try {
            svFunc.recievedWsdlFile(WSDLName, WSDLData);
            out = true;
        } catch (Exception e) {
            out = false;
            System.err.println("[ModelUpdater]: " + e);
        }
        return out;
    }

    /**
     * Operação de Web service
     *
     * @param fileName
     * @throws java.io.IOException
     */
    @WebMethod(operationName = "UpdateModel")
    public void UpdateModel(@WebParam(name = "orchestrationName") String orchestrationName) throws IOException {
        
        String bpelName = FilenameUtils.removeExtension(orchestrationName);
        System.out.println("[ModelUpdater] UpLoad orchestration description file: " + orchestrationName);
        
        
        try {
            svFunc.bpelFileUpdate(orchestrationName);

            
            if(descAna.checkModelUpdateNecessity(orchestrationName)){
                //generation of new modelo
                System.out.println("[ModelUpdater] Starting modelGen");
                ServiceConfig.loadConfigurations();
                svFunc.generateModelFromBpelFilePRD(bpelName);
                //requesting test execution for updated model
                RequestTestExecution(orchestrationName);
            }
            

        } catch (Exception e) {
            System.out.println("[ModelUpdater] Exception: " + e );
        }

    }
    
    private void RequestTestExecution(String orchestrationName){
    
        
        try { // Call Web Service Operation
            com.unicamp.lucas.testcasegen.MBTestCaseGen port = service.getMBTestCaseGenPort();
            // TODO initialize WS operation arguments here
            java.lang.String orchestrationFileName = orchestrationName;
            port.executeTest(orchestrationFileName);
        } catch (Exception ex) {
            System.out.println("[ModelUpdater] Exception: " + ex );
        }

    }

}
