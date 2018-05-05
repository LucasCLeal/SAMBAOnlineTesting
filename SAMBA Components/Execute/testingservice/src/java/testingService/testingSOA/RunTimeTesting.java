package testingService.testingSOA;

import java.util.logging.Level;
import java.util.logging.Logger;
import testingService.config.ConfigParameters;
import testingService.soaDescription.KindOfService;
import testingService.soaDescription.SoaDescription;
import testingService.utils.Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.validator.UrlValidator;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import pt.uc.dei.wsrbench.common.configuration.Configuration;
import pt.uc.dei.wsrbench.common.exception.DriverException;
import pt.uc.dei.wsrbench.common.pojo.Wsdl;
import pt.uc.dei.wsrbench.core.AsynchDriverImpl;
import pt.uc.dei.wsrbench.core.SynchDriverImpl;
import testingService.soaDescription.DBConnection;

import java.io.FileInputStream;
import java.util.Properties;
import testingService.Layers;


/**
 * Does the runtime tests, including virtualization.
 * @author Andrea
 * TODO: all this class needs to be done to include virtualization
 */
public class RunTimeTesting extends Testing implements Runnable, Validator  {

	/**
	 * Object of type pt.uc.dei.wsrbench.common.pojo.Wsdl.
	 */
	private Wsdl wsdl;

	/**
	 * true if the service address for testing is valid, false otherwise.
	 */
	boolean isValid=false;


	/**
	 * the run of this method: it is the core of the runtime tests
	 * 
	 */
    @Override
	public void run(){
		System.out.println("T_SERVICE: runtime testing is now started");
		//initialize variable isStarted
		isStarted=true;
		setRetryValuesRunTime();
		Thread thisThread = Thread.currentThread();
		blinker= thisThread;
		ResultSet rs=null;
		/*
		 * here the core of the run starts
		 */
		while (blinker == thisThread) {
			//get a copy of table soaDescription
			rs=new SoaDescription().getCopyOfServices();
			//finds who to test next
			String address = findWhoToTestNext(rs);
			System.out.println("T_SERVICE: the address selected for the testing is: "+address);
                try{
                    //riposo
	    			Thread.sleep(10000);
	    		}catch(Exception e){
	    			System.out.println("T_SERVICE: Sleep did not work in OfflineTesting runnable thread");
	    		}

            //if there is nothing to test, simply sleeps a bit
			//sleeps 60 seconds: he has no address to test, so he sleeps and will re-try later
	    	if(address.equals(NOTHING_TO_TEST)){
	    		try{
	    			Thread.sleep(60000);
	    		}catch(Exception e){
	    			System.out.println("T_SERVICE: Sleep did not work in OfflineTesting runnable thread");
	    		}
	    	}else{
	    		//found one service to test: will test it!!!
	    		//get kind of service
    			String kindOfService=DBConnection.getKindOfService(address);
                String result;
                //start virtualization
                if(kindOfService.equals(KindOfService.CONTROLLED) || kindOfService.equals(KindOfService.BPEL_SERVICE)){
                    String temporaryAddress="error";
                     temporaryAddress=startVirtualization(address, DBConnection.getKindOfService(address));
                    //do the test
                    System.out.println("T_SERVICE: virtual copy prepared at address "+temporaryAddress);
        			if(temporaryAddress.equalsIgnoreCase("error")){
                        result=ERROR;
                    }else{
                        result=doWsrBenchTesting(temporaryAddress);
                    }
                    //stop virtualization
                    stopVirtualization(address, DBConnection.getKindOfService(address));

                }else{
    			//do the test
        			result=doWsrBenchTesting(address);
                }

                long last_tested=System.currentTimeMillis();
    			//updates the table soaDescription:
	    		//if kind_of_service==within_reach
    			if(kindOfService.equals(KindOfService.WITHIN_REACH)){
		    		//puts the expire_within_reach or the retry_policy in the table soaDescription, whichever comes first
	    			if(result.equals(FINISHED)==false && retryValue(result)<EXPIRE_WITHIN_REACH){
			    		DBConnection.updateLastTest(address, System.currentTimeMillis(), result, retryValue(result));
	    			}
	    			else{
			    		DBConnection.updateLastTest(address, last_tested, result, EXPIRE_WITHIN_REACH);
	    			}
	    		//the service is not a within_reach, so we put the retry_policy in the soaDescription table according to information of retryValue(result)
	    		}else{
		    		DBConnection.updateLastTest(address, last_tested, result, retryValue(result));
	    		}
		    	//if the test is FINISHED (i.e., it completed and so there are results that we can analyze),
	    		//we analyzes the results and writes them in the database
	    		if(result.equalsIgnoreCase(FINISHED)){
	    			String tableName=DBConnection.createResultTable(address, last_tested);
	    			System.out.println("T_SERVICE: Storing results in table "+tableName+"...");
	    			int operation=0, parameter=0, fault=0;
	    			//for each operation
	    			for(operation=0; operation < wsdl.getOperationList().size(); operation++){
	    				//for each parameter
	    				for(parameter=0; parameter < wsdl.getOperationList().get(operation).getParameterList().size(); parameter++){
	    					//for each faults injected (each request-response collected)
	    					for(fault=0; fault < wsdl.getOperationList().get(operation).getParameterList().get(parameter).getFaultList().size(); fault++){
			    				addResultTable(tableName, address, last_tested, wsdl, operation, parameter, fault);
	    					}
	    					//if wsrBench could not identify any test case (fault id)
	    					if(fault==0){
	    						addResultTable(tableName, address, last_tested, wsdl, operation, parameter);
	    					}
	    				}
    					//if wsrBench could not identify any test parameter (and consequently any fault) for a given operation
	    				if(parameter==0){
    						addResultTable(tableName, address, last_tested, wsdl, operation);
	    				}
	    			}
	    			System.out.println("T_SERVICE: Finished storing results!");
                try{
                    //riposo
	    			Thread.sleep(10000);
	    		}catch(Exception e){
	    			System.out.println("T_SERVICE: Sleep did not work in OfflineTesting runnable thread");
	    		}
	    		}
	    	}
	    }
		//notify that the thread is going to die
		notifyDead();
	}
	
	/**
	 * set retry_policy values and Expire_WITHIN_REACH for RunTime testing
	 * @see testingService.testingSOA.Testing.retry_policy
	 * @see testingService.testingSOA.Testing.EXPIRE_WITHIN_REACH
	 */
	public void setRetryValuesRunTime(){
		Properties configFile = new Properties();
		try{

			configFile.load(new FileInputStream(ConfigParameters.retryParameters));
		}catch(Exception e){
			System.out.println("T_SERVICE: Retry.properties file not readable.");
			System.out.println("T_SERVICE: Using default values.");
			return;
		}
		
		retry_policy[ADDRESS_NOT_REACHABLE_int]=Integer.valueOf(configFile.getProperty("RUNTIME_NOT_REACHABLE")).longValue();
		retry_policy[ERROR_int]=Integer.valueOf(configFile.getProperty("RUNTIME_ERROR")).longValue();
		retry_policy[FINISHED_int]= Integer.valueOf(configFile.getProperty("RUNTIME_FINISHED")).longValue();
		EXPIRE_WITHIN_REACH=Integer.valueOf(configFile.getProperty("RUNTIME_WITHIN_REACH")).longValue();		
		
	}
	
	/**
	 * Do wsrbench testing for a specific address (extracted from the database)
	 * without any virtualization.
	 * @param address of the service e.g., http://localhost:8080/AirlineServiceLocation
	 * @return final outcome of the test (finished, failed, not started)
	 */
	public String doWsrBenchTesting(String address){
		//prepare the string for wsrbench
		String url=Utils.prepareStringForWsrBench(address);
		//use wsrbench api
		wsdl = new Wsdl();
		wsdl.setUrl(url);

		isValid=false;
		System.out.println("T_SERVICE: checking validity of URL: "+address);
		//validate url using stuff from Nuno: check if address is reachable
 		validate(wsdl, null);

		if (isValid==false){
			wsdl.setStatus(Wsdl.NOT_STARTED);
			System.out.println("T_SERVICE: test not started. Address not reachable: "+address);
			return ADDRESS_NOT_REACHABLE;
		}

		System.out.println("T_SERVICE: URL "+address+" is valid.");
		//set some config parameters
		Configuration config = new Configuration();
		System.out.println("T_SERVICE: Set config information for current test");

		//if url is valid, the test starts.
		//Set how many faulty requests should be sent for each injected fault.
		//MAX_FAULTY_REQUEST_COUNT=10;
		config.setFaultyRequestCount(Configuration.MIN_FAULTY_REQUEST_COUNT);
		//Number of requests without faults to send to each operation.
		//MAX_PLAIN_REQUEST_COUNT=10;
		config.setPlainRequestCount(Configuration.MAX_PLAIN_REQUEST_COUNT);
		//Define the timeout value for each connection to the server.
		//DEFAULT_TIMEOUT=10000L
		config.setTimeout(Configuration.DEFAULT_TIMEOUT);
		System.out.println("T_SERVICE: WsrBench config information are now set and are:");
		System.out.println("T_SERVICE: "+config.toString());

		SynchDriverImpl sinctest = new SynchDriverImpl();
		//get the wsdl and analyzes it
		try {
		System.out.println("T_SERVICE: invoking submitWSDL method");
			wsdl = sinctest.submitWsdl(config, wsdl);
		} catch (DriverException e) {
			System.out.println("T_SERVICE: Impossible to retrieve the wsdl file.");
			System.out.println("T_SERVICE: Test aborted with result "+ ERROR+".");
			wsdl.setStatus(Wsdl.ERROR);
			return ERROR;
		}
		AsynchDriverImpl asinctest = new AsynchDriverImpl();
		//set wsdl and config files
		asinctest.setWsdl(wsdl);
		asinctest.setConfig(config);
        //start the test
        asinctest.startWsdlTestA();
		//get result of the test from pt.uc.dei.wsrbench.common.pojo.Wsdl.java
		String result=wsdl.getStatus();
		System.out.println("T_SERVICE: test of "+ url + " completed with result: " + result);
		if(result.equals(FINISHED)){
			return result;
		}else{
			System.out.println("T_SERVICE: entered a strange ELSE CONDITION in doWsrBenchTesting! Check and debug to understand what happened.");
			return result;
		}
	}

	/**
	 * sends results to the results table in the database
	 * @param name of the result table
	 * @param service address
	 * @param time of last test
	 * @param Wsdl object from wsrbench API
	 * @param counter of operation that is currently analyzed
	 * @param counter of parameter that is currently analyzed
	 * @param counter of fault that is currently analyzed
	 */
	public void addResultTable(String tablename, String address, long last_tested, Wsdl ws, int op, int par, int f){
		String operation, parameter, fault, faultymsg, receivedmsg, problem;
		try{
			operation=wsdl.getOperationList().get(op).getOperationName();
			operation= Utils.prepareStringForDB(operation);
		}catch(NullPointerException e){
			operation=null;
		}
		try{
			parameter= wsdl.getOperationList().get(op).getParameterList().get(par).getParameterName()+" "+wsdl.getOperationList().get(op).getParameterList().get(par).getParameterType();
			parameter= Utils.prepareStringForDB(parameter);
		}catch(NullPointerException e){
			parameter=null;
		}
		try{
			fault=wsdl.getOperationList().get(op).getParameterList().get(par).getFaultList().get(f).getFaultName();
			fault= Utils.prepareStringForDB(fault);
		}catch(NullPointerException e){
			fault=null;
		}
		try{
			faultymsg=wsdl.getOperationList().get(op).getParameterList().get(par).getFaultList().get(f).getRequest();
			faultymsg= Utils.prepareStringForDB(faultymsg);
		}catch(NullPointerException e){
			faultymsg=null;
		}
		try{
			receivedmsg=wsdl.getOperationList().get(op).getParameterList().get(par).getFaultList().get(f).getResponse();
			receivedmsg= Utils.prepareStringForDB(receivedmsg);
		}catch(NullPointerException e){
			receivedmsg=null;
		}
		try{
			problem=WsrbenchData.getStatusProblem(wsdl.getOperationList().get(op).getParameterList().get(par).getFaultList().get(f));
		}catch(NullPointerException e){
			problem=null;
		}

		DBConnection.addToResultTable(
			//name of the table
			tablename,
			//service address
			address,
			//last tested value
			last_tested,
			//name of the current operation
			operation,
			//name of the current parameter
			parameter,
			//name of the current fault
			fault,
			//sent (faulty) message
			faultymsg,
			//received message
			receivedmsg,
			//analysis of received message: problem detected/undetected/unknown
			problem);
	}

    /**
	 * sends results to the database with TIME VALUES, with null values for faults
	 * @param name of the result table
	 * @param service address
	 * @param time of last test
	 * @param Wsdl object from wsrbench API
	 * @param counter of operation that is currently analyzed
	 * @param counter of parameter that is currently analyzed
	 */
	public void addResultTableTimeValue(String tablename, String address,
            String operation, long minValue, long maxValue, long average){
		DBConnection.addToResultTableTimeValue(
			//name of the table
			tablename,
			//service address
			address, operation,minValue, maxValue, average);
	}

	/**
	 * sends results to the database, with null values for faults
	 * @param name of the result table
	 * @param service address
	 * @param time of last test
	 * @param Wsdl object from wsrbench API
	 * @param counter of operation that is currently analyzed
	 * @param counter of parameter that is currently analyzed
	 */
	public void addResultTable(String tablename, String address, long last_tested, Wsdl ws, int op, int par){
		String operation=wsdl.getOperationList().get(op).getOperationName();
		operation= Utils.prepareStringForDB(operation);
		String parameter= wsdl.getOperationList().get(op).getParameterList().get(par).getParameterName()+" "+wsdl.getOperationList().get(op).getParameterList().get(par).getParameterType();
		parameter= Utils.prepareStringForDB(parameter);

		DBConnection.addToResultTable(
			//name of the table
			tablename,
			//service address
			address,
			//last tested value
			last_tested,
			//name of the current operation
			operation,
			//name of the current parameter
			parameter,
			//name of the current fault
			null,
			//sent (faulty) message
			null,
			//received message
			null,
			//analysis of received message: problem detected/undetected/unknown
			null);
	}

	/**
	 * sends results to the database, with null values for parameters and faults
	 * @param name of the result table
	 * @param service address
	 * @param time of last test
	 * @param Wsdl object from wsrbench API
	 * @param counter of operation that is currently analyzed
	 */
	public void addResultTable(String tablename, String address, long last_tested, Wsdl ws, int op){
		String operation=wsdl.getOperationList().get(op).getOperationName();
		operation= Utils.prepareStringForDB(operation);

		DBConnection.addToResultTable(
			//name of the table
			tablename,
			//service address
			address,
			//last tested value
			last_tested,
			//name of the current operation
			operation,
			//name of the current parameter
			null,
			//name of the current fault
			null,
			//sent (faulty) message
			null,
			//received message
			null,
			//analysis of received message: problem detected/undetected/unknown
			null);
	}

	/**
	 * from Nuno code: detects if an address is valid
	 * (otherwise wsrbench does not work and stuck on invalid address)
	 * @param target
	 * @param errors
	 */
	@Override
	@SuppressWarnings("deprecation")
	public void validate(Object target, Errors errors)
	{
		Long timeout=Configuration.DEFAULT_TIMEOUT;

		Wsdl wsdl1 = (Wsdl) target;
		UrlValidator urlValidator = new UrlValidator();
		isValid = urlValidator.isValid(wsdl1.getUrl());

		// check if server exists
		if (isValid)
		{
			HttpClient client = new HttpClient();
			client.setConnectionTimeout(timeout.intValue());
			HttpMethod method = new GetMethod(wsdl1.getUrl());

			try
			{
				int statusCode = client.executeMethod(method);
				if (statusCode != HttpStatus.SC_OK)
				{
					isValid = false;
					return;
				}
			} catch (HttpException e)
			{
				isValid = false;
				return;
				//e.printStackTrace();
			} catch (IOException e)
			{
				isValid = false;
				return;
				//e.printStackTrace();
			}
		}
		return;
	}

	/**
	 * (from Nuno stuff)
	 */
        @Override
	@SuppressWarnings("unchecked")
	public boolean supports(Class clazz)
	{
		boolean result = false;

		try {
			result = (clazz.equals(Wsdl.class));
		} catch (ClassCastException e)
		{
		}
		return result;
	}

    /**
     * 
     * @param address esempio: http://localhost:8080/AirlineServiceLocation
     * @param kindOfService
     * @return temporary address for testing
     */
    public String startVirtualization(String address, String kindOfService) {
        //se devo virtualizzare un bpel
        if(kindOfService.equals(KindOfService.BPEL_SERVICE)){
            //dovrebbe darmi 192.168.81.128
            String ip= address.substring(7, address.indexOf(':', 9));
            //tell to the bpel engine at what IP to virtualize
            String addressVirtualCopy= Layers.layerApp.doBpelVirtualization(ip);
            System.out.println("T_SERVICE: time waiting for deploy of virtual copy on server");
            //wait 10 seconds: time needed to deploy the virtual copy on the server
       		try{
	   			Thread.sleep(20000);
	   		}catch(Exception e){
	   			System.out.println("T_SERVICE: Sleep did not work.");
	   		}
            return addressVirtualCopy;

        }else{
            if(kindOfService.equals(KindOfService.CONTROLLED)){
                //dovrebbe darmi 192.168.81.128
                String ip= address.substring(7, address.indexOf(':', 9));
                //tell to the bpel engine at what IP to virtualize
                String addressVirtualCopy= Layers.layerApp.doControlledVirtualization(ip);
                System.out.println("T_SERVICE: time waiting for deploy of virtual copy on server");
                //wait 10 seconds: time needed to deploy the virtual copy on the server
                try{
                    Thread.sleep(20000);
                }catch(Exception e){
                    System.out.println("T_SERVICE: Sleep did not work.");
                }
                return addressVirtualCopy;

            }
        }
        return address;
    }

    /**
     *
     * @param address esempio: http://localhost:8080/AirlineServiceLocation
     * @param kindOfService
     */
    public void stopVirtualization(String address, String kindOfService) {
         //se devo virtualizzare un bpel
        if(kindOfService.equals(KindOfService.BPEL_SERVICE)){
            //dovrebbe darmi 192.168.81.128
            String ip= address.substring(7, address.indexOf(':', 9));
            //tell to the bpel engine at IP to stop virtualization
	   		 System.out.println("T_SERVICE: Stopping virtualization of bpel file at ip "+ ip);
             System.out.println("T_SERVICE: Undeploying... ");
             Layers.layerApp.stopBpelVirtualization(ip);
             System.out.println("T_SERVICE: Undeploying completed.");

        }else{
        //se devo virtualizzare un controlled
            if(kindOfService.equals(KindOfService.CONTROLLED)){
                //dovrebbe darmi 192.168.81.128
                String ip= address.substring(7, address.indexOf(':', 9));
                //tell to the bpel engine at IP to stop virtualization
                 System.out.println("T_SERVICE: Stopping virtualization of controlled service at ip "+ ip);
                 System.out.println("T_SERVICE: Undeploying... ");
                 Layers.layerApp.stopControlledVirtualization(ip);
                 System.out.println("T_SERVICE: Undeploying completed.");
            }
       }
    }
}

