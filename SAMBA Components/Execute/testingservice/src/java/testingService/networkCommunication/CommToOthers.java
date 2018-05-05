package testingService.networkCommunication;

import java.io.File;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import org.apache.commons.io.FileUtils;
import testingService.config.ConfigParameters;
import testingService.Layers;
import testingService.testingSOA.TestingEngine;

/**
 *Communication to the Bpel Engine
 */
public class CommToOthers {
	
	//variabili di appoggio per le chiamate asincrone con la socket


	/**
	 * wait for answer about bpel number of file
	 */
	public int bpelF=-1;
	
	/**
	 * number of bpel file transmitted
	 */
	public int bpelReceived=0;

    /**
     * Invoked to virtualize a BPEL service
     * @param ip
     * @return
     */
    public String doBpelVirtualization(String ip) {
        URL BPELWSSERVICE_WSDL_LOCATION;
        URL url = null;
        try {
           URL baseUrl;
           baseUrl = supportfortestingservice.communication.BpelWSService.class.getResource(".");
           url = new URL(baseUrl, "http://"+ip+":8080/BpelEngineWS/BpelWSService?wsdl");
           BPELWSSERVICE_WSDL_LOCATION = url;

           supportfortestingservice.communication.BpelWSService service =
               new supportfortestingservice.communication.BpelWSService(BPELWSSERVICE_WSDL_LOCATION, new QName("http://communication.supportForTestingService/", "BpelWSService"));
           supportfortestingservice.communication.BpelWS port = service.getBpelWSPort();
            return port.startVirtualCopy();

        } catch (Exception e) {
            return "error";
        }

    }

    /**
     * Invoked to virtualize a controlled service
     * @param ip
     * @return
     */
    public String doControlledVirtualization(String ip) {
        URL CONTROLLEDSERVICE_WSDL_LOCATION;
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = controlled.ws.Controlled_Service.class.getResource(".");
            url = new URL(baseUrl, "http://"+ip+":8080/Controlled/Controlled?wsdl");
            CONTROLLEDSERVICE_WSDL_LOCATION = url;

            controlled.ws.Controlled_Service service =
                new controlled.ws.Controlled_Service(CONTROLLEDSERVICE_WSDL_LOCATION,
                    new QName("http://ws.controlled/", "Controlled"));
            controlled.ws.Controlled port = service.getControlledPort();

            return port.virtualizeForTesting();
        } catch (Exception e) {
            System.out.println("T_SERVICE: exception in communication with controlled service.");
            System.out.println("T_SERVICE: "+e);
            return "error";
        }
    }


    public Boolean stopControlledVirtualization(String ip){
        URL CONTROLLEDSERVICE_WSDL_LOCATION;
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = controlled.ws.Controlled_Service.class.getResource(".");
            url = new URL(baseUrl, "http://"+ip+":8080/Controlled/Controlled?wsdl");
            CONTROLLEDSERVICE_WSDL_LOCATION = url;

            controlled.ws.Controlled_Service service =
                new controlled.ws.Controlled_Service(CONTROLLEDSERVICE_WSDL_LOCATION,
                    new QName("http://ws.controlled/", "Controlled"));
            controlled.ws.Controlled port = service.getControlledPort();

            return port.stopVirtualization();
        } catch (Exception e) {
            return false;
        }
    }

    public String stopBpelVirtualization(String ip) {
        URL BPELWSSERVICE_WSDL_LOCATION;
        URL url = null;
        try {
           URL baseUrl;
           baseUrl = supportfortestingservice.communication.BpelWSService.class.getResource(".");
           url = new URL(baseUrl, "http://"+ip+":8080/BpelEngineWS/BpelWSService?wsdl");
           BPELWSSERVICE_WSDL_LOCATION = url;

           supportfortestingservice.communication.BpelWSService service =
               new supportfortestingservice.communication.BpelWSService(BPELWSSERVICE_WSDL_LOCATION, new QName("http://communication.supportForTestingService/", "BpelWSService"));
           supportfortestingservice.communication.BpelWS port = service.getBpelWSPort();

            return port.stopVirtualCopy();
        } catch (Exception e) {
            return "error";
        }



    }
	
	/**
	 * send soa status to the bpel engine
	 */
    public int setSoa(int i, String ip){
        URL BPELWSSERVICE_WSDL_LOCATION;
        URL url = null;
        try {
           URL baseUrl;
           baseUrl = supportfortestingservice.communication.BpelWSService.class.getResource(".");
           url = new URL(baseUrl, "http://"+ip+":8080/BpelEngineWS/BpelWSService?wsdl");
           BPELWSSERVICE_WSDL_LOCATION = url;

           supportfortestingservice.communication.BpelWSService service =
               new supportfortestingservice.communication.BpelWSService(BPELWSSERVICE_WSDL_LOCATION, new QName("http://communication.supportForTestingService/", "BpelWSService"));
           supportfortestingservice.communication.BpelWS port = service.getBpelWSPort();
           return port.setSoaStatus(i);
        } catch (Exception e) {
            return 0;
        }
    }
	
	/**
	 * get the status of the soa
	 * it is a send-receive message.
	 * To evaluate if this may work
	 */
    public int askSoaValue(String ip){
        URL BPELWSSERVICE_WSDL_LOCATION;
        URL url = null;
        try {
           URL baseUrl;
           baseUrl = supportfortestingservice.communication.BpelWSService.class.getResource(".");
           url = new URL(baseUrl, "http://"+ip+":8080/BpelEngineWS/BpelWSService?wsdl");
           BPELWSSERVICE_WSDL_LOCATION = url;

           supportfortestingservice.communication.BpelWSService service =
               new supportfortestingservice.communication.BpelWSService(BPELWSSERVICE_WSDL_LOCATION, new QName("http://communication.supportForTestingService/", "BpelWSService"));
           supportfortestingservice.communication.BpelWS port = service.getBpelWSPort();
           return port.getSoaStatus();
        } catch (Exception e) {
            return 0;
        }
    }
	
		
	/**
	 * get number of bpel files availables
	 * @return number of bpel file
	 */		
	public int getNumberOfBpelFile(String ip){
		bpelF=-1;
        URL BPELWSSERVICE_WSDL_LOCATION;
        URL url = null;
        try {

           URL baseUrl;
           baseUrl = supportfortestingservice.communication.BpelWSService.class.getResource(".");
           url = new URL(baseUrl, "http://"+ip+":8080/BpelEngineWS/BpelWSService?wsdl");
           BPELWSSERVICE_WSDL_LOCATION = url;

           supportfortestingservice.communication.BpelWSService service =
               new supportfortestingservice.communication.BpelWSService(BPELWSSERVICE_WSDL_LOCATION, new QName("http://communication.supportForTestingService/", "BpelWSService"));
           supportfortestingservice.communication.BpelWS port = service.getBpelWSPort();

            bpelF= port.getBpelFileNumber();
            return bpelF;
        } catch (MalformedURLException e) {
            return 0;
        }
	}

	/**
	 * sends a request for fresh bpel files
	 * the final outcome is that the bpel files will be stored in the directory ConfigParameters.storedBpelFile
	 * @return true if the files were received, false otherwise
	 */
	public boolean getFreshBpel(String ip){
        URL BPELWSSERVICE_WSDL_LOCATION;
        URL url = null;
        try {
           URL baseUrl;
           baseUrl = supportfortestingservice.communication.BpelWSService.class.getResource(".");
           url = new URL(baseUrl, "http://"+ip+":8080/BpelEngineWS/BpelWSService?wsdl");
           BPELWSSERVICE_WSDL_LOCATION = url;

           supportfortestingservice.communication.BpelWSService service =
               new supportfortestingservice.communication.BpelWSService(BPELWSSERVICE_WSDL_LOCATION, new QName("http://communication.supportForTestingService/", "BpelWSService"));
           supportfortestingservice.communication.BpelWS port = service.getBpelWSPort();

           TestingEngine.numberOfBpelFile=port.getBpelFileNumber();
        } catch (Exception e) {
            return false;
        }
        if(TestingEngine.numberOfBpelFile>0){
            for(int i=0; i < TestingEngine.numberOfBpelFile; i++){


                supportfortestingservice.communication.BpelWSService service1 =
                        new supportfortestingservice.communication.BpelWSService();
                supportfortestingservice.communication. BpelWS port1=service1.getBpelWSPort();
                
                Layers.engine.bpelFile[i]=
                    new File(ConfigParameters.storedBpelFile +"/bpelFile"+i+".bpel");
                try {
                    FileUtils.writeStringToFile(Layers.engine.bpelFile[i], port1.getBpelFile(bpelReceived));
                } catch (IOException ex) {
                	System.out.println("T_SERVICE: Problems writing bpel file "+Layers.engine.bpelFile[i]);
                    Layers.engine.bpelFile[i]=null;
                    i--;
                }
            }
            return true;
		}
		return false;
	}

	/**
	 * received the modified bpel files
	 * the files are stored in the directory ConfigParameters.storedBpelFile
	 * now it is time for a SOA_UPDATE for new bpel files!
	 * @param number of bpel file
     * @Deprecated
	 */
/*	public void receivedModifiedBPELfiles(int i){
		if(i>0){
    		Layers.engine.receivedModifiedBPELfiles(i, "");
        }
	}
*/
	/**
	 * gets wsdl file
	 * it has a wait: stalling until he does not receive the required wsdl file
	 */
    public File askWsdlFile(String str, String ip){
        URL BPELWSSERVICE_WSDL_LOCATION;
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = supportfortestingservice.communication.BpelWSService.class.getResource(".");
            url = new URL(baseUrl, "http://"+ip+":8080/BpelEngineWS/BpelWSService?wsdl");
        } catch (MalformedURLException e) {
        }

        BPELWSSERVICE_WSDL_LOCATION = url;
        supportfortestingservice.communication.BpelWSService service1 =
               new supportfortestingservice.communication.BpelWSService(BPELWSSERVICE_WSDL_LOCATION, new QName("http://communication.supportForTestingService/", "BpelWSService"));
        supportfortestingservice.communication.BpelWS port1 = service1.getBpelWSPort();

        File f= new File(ConfigParameters.storedWsdlFile +"/"+str);
        try {
            FileUtils.writeStringToFile(f, port1.getAWsdlFiles(str));
        } catch (IOException ex) {
            System.out.println("T_SERVICE: Problems writing wsdl file "+str);
            return null;
        }
        return f;
    }
}
