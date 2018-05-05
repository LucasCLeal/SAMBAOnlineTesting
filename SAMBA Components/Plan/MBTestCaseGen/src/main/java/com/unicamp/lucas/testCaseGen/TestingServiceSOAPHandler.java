/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.testCaseGen;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import javax.ws.rs.core.MediaType;
import javax.xml.ws.WebServiceRef;
import testingservice.networkcommunication.TestingWSService;

/**
 *
 * @author LucasCLeal
 */
public class TestingServiceSOAPHandler {

    @WebServiceRef(wsdlLocation = "WEB-INF/wsdl/192.168.56.5_8080/testingservice/TestingWSService.wsdl")
    private TestingWSService service;

    Client client = Client.create();

    public boolean exeServiceOperation(String nextOp) {

        boolean result = false;
        
        try { // Call Web Service Operation
            testingservice.networkcommunication.TestingWS port = service.getTestingWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String operation = nextOp;
            // TODO process result here
            result = port.exeTestInstruction(operation);
        } catch (Exception ex) {
             System.err.println("exeTestInstruction "+ex);
        }

        return result;
    }

    public boolean checkTestServiceAvailability() throws IOException {

        WebResource webResource = client.resource("http://192.168.56.5:8080/testingservice/");
        ClientResponse response = webResource.type(MediaType.TEXT_PLAIN).put(ClientResponse.class);
 
        if (response.getStatus() == 200) {
            System.out.println("[MBTestCaseGen] TestingService online");
            return true;
        } else {
            System.out.println("[MBTestCaseGen] TestingService not online");
            return false;
        }

    }
    
    public void resetCompostionTestVariables() throws IOException {

        try { // Call Web Service Operation
            
            testingservice.networkcommunication.TestingWSService service = new testingservice.networkcommunication.TestingWSService();
            testingservice.networkcommunication.TestingWS port = service.getTestingWSPort();
            port.resetTestVariables();
            
        } catch (Exception ex) {
            System.err.println("resetCompostionTestVariables "+ex);
        }

    }
    
}
