/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unicamp.lucas.testCaseGen;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.nio.file.Path;
import javax.ws.rs.core.MediaType;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author LucasCLeal
 */
public class GraphWalkerRestHandler {

    //declarar websource para serviço graphwalker como final
    /**
     *
     * @return
     */
    Client client = Client.create();

    public boolean isServiceOnline() throws IOException, ConnectException {

        WebResource webResource = client.resource("http://localhost:8887/graphwalker");
        ClientResponse response = webResource.type(MediaType.TEXT_PLAIN).put(ClientResponse.class);

        if (response.getStatus() != 404) {
            System.out.println("[MBTestCaseGen] GraphWalker online");
            return true;
        } else {
            System.out.println("[MBTestCaseGen] GraphWalker not online");
            return false;
        }

    }

    public boolean resetService() {

        WebResource webResource = client.resource("http://localhost:8887/graphwalker/restart");

        ClientResponse response = webResource.type(MediaType.TEXT_PLAIN).put(ClientResponse.class);
        String outPut = response.getEntity(String.class);

        JSONObject jsObject = new JSONObject(outPut);
        String result = jsObject.getString("result"); // get the name from data.

        //getting a 'nok' as result not necessaraly means that the service is not working, since there is nothing loaded
        //in the server this is the default response
        System.out.println("reset request result: " + result);

        return result.equals("ok");

    }

    public boolean stopService() {

        WebResource webResource = client.resource("http://localhost:8887/graphwalker/fail/Service%20stoped%20as%20requested");
        ClientResponse response = webResource.type(MediaType.TEXT_PLAIN).put(ClientResponse.class);

        if (response.getStatus() == 200) {
            System.out.println("Graph Walker Rest service stoped");
        } else {
            System.out.println("WARNING: Graph Walker Rest service error while stopping");
        }

        return response.getStatus() == 200;

    }

    public boolean loadModel(String jsModel) {
        /*
            @POST
            @Consumes("text/plain;charset=UTF-8")
            @Produces("text/plain;charset=UTF-8")
            @Path("load")
         */

        //Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8887/graphwalker/load");

        ClientResponse response = webResource.type(MediaType.TEXT_PLAIN).post(ClientResponse.class, jsModel);
        String outPut = response.getEntity(String.class);

        JSONObject jsObject = new JSONObject(outPut);
        String result = jsObject.getString("result"); // get the name from data.
        System.out.println("load model result: " + result);

        return result.equals("ok");

    }

    public boolean hasNextStep() {

        //Client client = Client.create();
        WebResource webResource = client.resource("http://localhost:8887/graphwalker/hasNext");

        ClientResponse response = webResource.type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        String output = response.getEntity(String.class);

        JSONObject jsObject = new JSONObject(output);
        String result = jsObject.getString("result"); // get the name from data.
        String hasNext = jsObject.getString("hasNext");

        return result.equals("ok") && hasNext.equals("true");

    }

    public String getNextStep() {

        try {
            WebResource webResource = client.resource("http://localhost:8887/graphwalker/getNext");

            ClientResponse response = webResource.type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
            //convertendo resposta em JSON e obtendo e passando reposta ao cliente
            String output = response.getEntity(String.class);

            //System.out.println(output);
            JSONObject jsObject = new JSONObject(output);
            //String result = jsObject.getString("result"); // get the name from data.
            String currentElement = jsObject.getString("currentElementName");

            return currentElement;
        } catch (Exception e) {
            System.out.println("[GRAPWALKER] não existe proximo elemento");
            System.err.println("Modelo com problemas: "+e);
            //retornando string vazia para abortar esse caso de teste
            return "";  
        }

    }

    public JSONObject getTestStatistics() {

        WebResource webResource = client.resource("http://localhost:8887/graphwalker/getStatistics");

        ClientResponse response = webResource.type(MediaType.TEXT_PLAIN).get(ClientResponse.class);
        //convertendo resposta em JSON e obtendo e passando reposta ao cliente
        String output = response.getEntity(String.class);

        //convertendo em JSON
        JSONObject jsObject = new JSONObject(output);

        //criar um POGO para recever esses dados
        /*{
            "edgeCoverage":                   <The coverage of edges in percent>,
            "result":                         <Either "OK" or "NOK">,
            "totalNumberOfEdges":             <Total number of edges in the model>,
            "totalNumberOfUnvisitedEdges":    <Number of edges not yet visited, or executed in the model>,
            "totalNumberOfUnvisitedVertices": <Number of vertices not yet visited, or executed in the model>,
            "totalNumberOfVertices":          <Total number of vertices in the model>,
            "totalNumberOfVisitedEdges":      <Number of edges visited, or executed in the model>,
            "totalNumberOfVisitedVertices":   <Number of vertices visited, or executed in the model>,
            "vertexCoverage":                 <The coverage of vertices in percent>
        }*/
        return jsObject;

    }
}
