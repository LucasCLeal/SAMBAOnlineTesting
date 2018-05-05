package testingService.testingSOA;

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


/**
 * This class does the "OFFLINE" testing: by offline we mean that there are no users in the system.
 * So we test very roughly, without virtualizing anything.
 * If the service falls, it is notp my problem because I have no users that rely on it :D 
 * @author Andrea
 */
public class OfflineTesting extends Testing implements Runnable, Validator{


	/**
	 * Object of type pt.uc.dei.wsrbench.common.pojo.Wsdl.
	 */
	private Wsdl wsdl;
	
	/**
	 * true if the service address for testing is valid, false otherwise.
	 */
	boolean isValid=false;

	/**
	 * the run of this method: it is the core of the closed service (offline) tests.
	 * Finds what to test, and then tests it.
	 */
	public void run(){
		System.out.println("T_SERVICE: closed service testing is now started");
		//initialize variable isStarted and other variables

		isStarted=true;
		setRetryValuesOffline();
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
    			//do the test
    			String result=doWsrBenchTesting(address);
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
		config.setPlainRequestCount(Configuration.MIN_PLAIN_REQUEST_COUNT);
		//Define the timeout value for each connection to the server.
		//DEFAULT_TIMEOUT=10000L
		config.setTimeout(Configuration.DEFAULT_TIMEOUT);
		System.out.println("T_SERVICE: WsrBench config information are now set and are:");
		System.out.println("T_SERVICE: "+config.toString());

		SynchDriverImpl sinctest = new SynchDriverImpl();
		//get the wsdl and analyzes it
		try {
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
	 * set retry_policy values and Expire_WITHIN_REACH for Offline (closed service) testing
	 * @see testingService.testingSOA.Testing.retry_policy
	 * @see testingService.testingSOA.Testing.EXPIRE_WITHIN_REACH
	 */
	void setRetryValuesOffline(){
		Properties configFile = new Properties();
		try{

			configFile.load(new FileInputStream(ConfigParameters.retryParameters));
		}catch(Exception e){
			System.out.println("T_SERVICE: Retry.properties file not readable.");
			System.out.println("T_SERVICE: Using default values.");
			System.exit(0);
			return;
		}
		
		retry_policy[ADDRESS_NOT_REACHABLE_int]=Integer.valueOf(configFile.getProperty("OFFLINE_NOT_REACHABLE")).longValue();
		retry_policy[ERROR_int]=Integer.valueOf(configFile.getProperty("OFFLINE_ERROR")).longValue();
		retry_policy[FINISHED_int]= Integer.valueOf(configFile.getProperty("OFFLINE_FINISHED")).longValue();
		EXPIRE_WITHIN_REACH=Integer.valueOf(configFile.getProperty("OFFLINE_WITHIN_REACH")).longValue();		
	}

	/**
	 * for debugging purposes
	 * Does a testing of a service
	 * @param args
	 */
	public static void main(String[] args){
        
        //new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:9080/PictureAlbumsService/PictureAlbumsPort");
        new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/jSeduite/HelloWorld/HelloWorldService");
        //new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:9080/CachedFeedReaderVirtualService/CachedFeedReaderVirtualPort");


        /*
		 * JAVA ADVENTURE BUILDER
		 * 		those with http work (one can not found any test for it),
		 *  	those with https do not work (unable to query: if we deactivate the ws validation command in our code,
		 *   	it can retrieve the wsdl file, and query, but the ws does not answer
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:60870/webservice/PoEndpointBean");
		//new OfflineTesting().testWsrBenchTesting("https://localhost:60872/webservice/AirlinePOService");
		//new OfflineTesting().testWsrBenchTesting("https://localhost:60872/webservice/CreditCardService");
		//new OfflineTesting().testWsrBenchTesting("https://localhost:60872/webservice/ActivityPOService");
		//new OfflineTesting().testWsrBenchTesting("https://localhost:60872/webservice/WebServiceBroker");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:60870/webservice/OtEndpointBean");
		//new OfflineTesting().testWsrBenchTesting("https://localhost:60872/webservice/LodgingPOService");

		/*
		 * NETBEANS TRAVEL RESERVATION SERVICE
		 * does not work
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/webservice/AirlineReservationService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/webservice/VehicleReservationService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/webservice/HotelReservationService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:18181/TravelReservationService/buildItinerary");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:18181/TravelReservationService/airlineReserved");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:18181/TravelReservationService/hotelReserved");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:18181/TravelReservationService/vehicleReserved");
		
		/*
		 * JBOSS TRAVEL RESERVATION SERVICE (BPEL BLUEPRINT 5)
		 * all of these can be tested
		 */
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/CarServiceLocation");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/CarStatusServiceLocation");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/BPEL_BluePrint5_ReservationService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/AirlineServiceLocation");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/AirlineStatusServiceLocation");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/HotelServiceLocation");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/HotelServiceLocation");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/HotelStatusServiceLocation");
		
		/*
		 * How to Deliver Composite Applications with Java, WS-BPEL & SOA
		 * everything works fine
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/VehicleInformationServerService/VehicleInformationServer");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/FinancialIndexService/FinancialIndex");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/LoanApproverService/LoanApprover");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/FICOSimulatorService/FICOSimulator");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/ApplicantEvaluatorService/ApplicantEvaluator");
		
		
		/*
		 * JBOSS ESB SAMPLE: WEBSERVICE_ESB_BPEL
		 * problems testing ABI-SHIPPING and ABI-ORDER MANAGER
		 */
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/Webservice_esb_bpel_ABI_Customer");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8080/Webservice_esb_bpel_Retailer");	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/Webservice_esb_bpel_RetailerCallback");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/Quickstart_webservice_bpel-Quickstart_webservice_bpel/ABI_Shipping");	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/Quickstart_webservice_bpel-Quickstart_webservice_bpel/ABI_OrderManager");
					
		/*
		 * WEB SERVICES OF TPC-APP (developed by NUNO Laranjeiro)
		 * everything works fine
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8070/sut-0.0.1/NewCustomerService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/ChangePaymentMethodService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8070/sut-0.0.1/NewProductsService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8070/sut-0.0.1/ProductDetailService");
		//these are probably only stub (not fully implemented services)
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/Bank3Service?wsdl");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/BankService?wsdl");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/JamesSmithService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/PhoneDirService");

		/*
		 * WEB SERVICES OF TPC-W (developed by NUNO Antunes)
		 * everything works fine
		 */
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWDoTitleSearchService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWGetBestSellersService"); 
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWDoAuthorSearchService"); 
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWDoSubjectSearchService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWCreateNewCustomerService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWGetMostRecentOrderService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWGetCustomerService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWGetUsernameService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWGetPasswordService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWAdminUpdateService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWGetNewProductsService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCWCreateShoppingCartService");
		 
		/*
		 * WEB SERVICES OF TPC-C (developed by NUNO Antunes)
		 * everything works fine
		 */
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCCPaymentService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCCDeliveryService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCCNewOrderService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCCOrderStatusService"); 
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCCStockLevelService");

		/*
		 * WEB SERVICES OF TPC-APP (developed by NUNO Antunes)
		 * everything works fine
		 */
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCAppChangePaymentMethodService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCAppProductDetailService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCAppNewCustomerService");
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:8070/sut-0.0.1/WsvdbTPCAppNewProductsService");

		/*
		 * INSURANCE CLAIM EXAMPLE
		 */
		//not explored: 5-6 ws with a bpel process (that does not work)

	
		/*
		 * ON-LINE WEB-SITES
		 */
		//new OfflineTesting().testWsrBenchTesting("http://www.dataaccess.com/webservicesserver/numberconversion.wso");
		//new OfflineTesting().testWsrBenchTesting("http://terraservice.net/TerraService.asmx");
		//new OfflineTesting().testWsrBenchTesting("http://www.webservicex.com/uszip.asmx");
		//new OfflineTesting().testWsrBenchTesting("http://ripedevelopment.com/");
		
		
		/**
		 * JSEDUITE WEB SERVICES
		 */

		/**
		 * group of service: APAL WRAPPER
		 * Wraps the APAL platform at http://apal.polytech.unice.fr/ to do queries:
		 * - ask N most absent students
		 * - ask promoted students
		 * - ... 
		 */		
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ApalWrapper/ApalWrapperService");

		/**
		 * group of service: BREAKING NEWS
		 *  A data source service to handle breaking news:
		 *  - extract news from database
		 *  - works with CRUD objects (e.g., to insert news)
		 *  - search news 
		 */			
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/BreakingNews/BreakingNewsCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/BreakingNews/BreakingNewsService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/BreakingNews/BreakingNewsFinderService");

		/**
		 * group of service: DATA CACHE
		 * A Very Simple Cache service.
		 * Defines an entry as a "content" binded to a "key", and use a timestamp to
		 * perform temporal validity check.
		 * -initialize the content of the entry
		 * -retrieve the content
		 * - perform temporal validity checks on an entry
		 * -update content of an entry
		 */			
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/DataCache/DataCacheService");

		/**
		 * group of service: EPHEMERIDES
		 * - gives position of astronomical objects
		 */				
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/Ephemerides/EphemeridesService");

		/**
		 * group of service: ERROR LOGGER
		 * Error logger:
		 * - log errors
		 * - view logged errors
		 */			
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ErrorLogger/ErrorLoggerService");

		/**
		 * group of service: FEED REGISTRY
		 *  A web service to store Syndication Feed adresses using nicknames. 
		 *  It stores feeds and helps categorization, providing a simple way to classify known feeds 
		 *  into categories.
		 *  -  Retrieve an URL for a feed, availables categories, ... 
		 *  -  Ricerca feed e feed class
		 *  - CRUD patterns operations
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/FeedRegistry/FeedClassCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/FeedRegistry/FeedFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/FeedRegistry/FeedRegistryService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/FeedRegistry/FeedCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/FeedRegistry/FeedClassFinderService");

		/**
		 * group of service: FILE UPLOADER
		 * - upload a file (on a directory)
		 * - delete a file (in that directory)
		 * - see all files in that directory
		 * (NON MI FUNZIONA MA NON CI HO PERSO TEMPO; AL + E' FACILMENTE CORREGGIBILE)
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/FileUploader/FileUploaderService);

		/**
		 * group of service: FLICKR WRAPPER
		 * Wrapper of the API di Flickr. Provides several read-only operations to retrieve data and do searches. 
		 * My password to work with the FLICKR API: 
		 * KEY: c90b7e1bd89fc772d1470c756b6623c1
		 * SECRET: 2ddc2b97d63b711b
		 * E.g.: album 4095, key c90b7e1bd89fc772d1470c756b6623c1
		 */			
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/FlickrWrapper/FlickrWrapperService");

		/**
		 * group of service: HELLO WORLD
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/HelloWorld/HelloWorldService");

		/**
		 * group of service: HYPER MACHINE
		 * HyperEvents/HyperPromo management.
		 * 
		 * NEED ICAL THAT CURRENTLY DO NOT HAVE.
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/HyperMachin/HyperLocatorService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/HyperMachin/HyperPromoManagerService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/HyperMachin/HyperMachinService");

		/**
		 * group of service: ICAL READER
		 * Used to interact with an ICAL calendar
		 * 
		 * NEED ICAL THAT CURRENTLY DO NOT HAVE.
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ICalReader/ICalReaderService");

		/**
		 * group of service: INTERNAL NEWS
		 * This service allow internal information broadcasting. Contrarily to BreakingNews which represent 
		 * really short life information, an internal news specify a start date and an end date, a target
		 * (students, teachers, building, ...), and some others informations to broadcast meaninglul information
		 * to targeted people. 
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/InternalNews/InternalNewsCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/InternalNews/InternalNewsService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/InternalNews/InternalNewsFinderService");

		/**
		 * group of service: MENU SERVICES
		 * Business service for Menu business object:
		 * - return today's menu, available kind of dishes, ...
		 * - search for menus and dishes
		 */			
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/MenuService/CourseFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/MenuService/CourseFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/MenuService/MenuFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/MenuService/CourseCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/MenuService/MenuBusinessService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/MenuService/CourseBusinessService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/MenuService/MenuCRUDService");

		/**
		 * group of service: PARTNER KEYS
		 * A web service to store and manage partners keys.
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/PartnerKeys/PartnerKeysService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/PartnerKeys/PartnerKeysFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/PartnerKeys/PartnerKeysCRUDService");

		/**
		 * group of service: PICASA WRAPPER
		 * The service wrap the PicasaWeb API. It only provides read-only operations to retrieve the 
		 * content of a published picture album, or perform a folksonomy search inside the existing tags.
		 * Example:
		 * username: andreac@dei.uc.pt
		 * album:   prova
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/PicasaWrapper/PicasaWrapperService");

		/**
		 * group of service: PICTURE ALBUM REGISTRY
		 * Manages a local album of pictures. This service register pictures albums' description. 
		 * It stores the album' repository (Flickr, PicasaWeb, ...), and useful information to retrieve
		 * it (user and album identifier, validity date, ...). A informal name is associated to those
		 * technicals  informations, so a human can manipulate it.
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/PictureAlbumRegistry/PictureAlbumRegistryService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/PictureAlbumRegistry/PictureAlbumRegistryCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/PictureAlbumRegistry/PictureAlbumRegistryFinderService");

		/**
		 * group of service: PICTURE SET
		 * Works with the URL of selected pictures.
		 * (just a "utility" web service)
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/PictureSet/PictureSetService");

		/**
		 * group of service: PROFILE MANAGER
		 * Did not understood what it does. Does a lot of things for management of 
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ProfileManager/ParamCreatorService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ProfileManager/SourceFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ProfileManager/ParamFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ProfileManager/SourceCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ProfileManager/DeviceFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ProfileManager/SourceManagerService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/ProfileManager/DeviceCRUDService");

		/**
		 * group of service: RSS READER
		 * Read RSS from an URL
		 * Example: http://rss.html.it/alldaily.xml
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/RssReader/RssReaderService");

		/**
		 * group of service: SCHOOL LIFE
		 * Web services for a school management:
		 * - handle students summons, teachers' absences, ...
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/StudentSummonService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/TeacherAbsencesService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/PromoFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/PromoCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/BreakTimeFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/BreakTimeCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/AlarmFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/AlarmCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/AbsenceCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/AbsenceFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/SummoningFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/SummoningCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/PlanningFinderService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/PlanningCRUDService");
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/SchoolLife/PlanningsService");

		/**
		 * group of service: TIME MACHINE
		 * This is only a stub: do nothing.
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/TimeMachine/TimeMachineService");

		/**
		 * group of service: TV HELPER
		 * Extract information from a tv show title in a more easiest way than
		 * using BPEL natives constructions
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/TvHelper/TvHelperService");

		/**
		 * group of service: TWITTER
		 * Wrap the jTwitter library as a WebService.
		 * - Retrieve all '#channel' Tweets from twitter
		 * - Retrieve all '@username' Tweets from twitter
		 * - ...
		 * 
		 * USERNAME: AzaIsInChatNow
		 * PASSWORD: AzazelTheSeeker12A
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/Twitter/TwitterWrapperService");

		/**
		 * group of service: VIDEO PUBLISHER
		 * A Web Service to handle Video files (store and delete file).
		 * This is only a stub: do nothing.
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:8080/jSeduite/VideoPublisher/VideoPublisherBusinessService");

		/**
		 * group of service: USER PROFILE 
		 * --> described on the web site but not provided here.
		 * Maybe we can ask for it, if we want it.
		 * http://jseduite.org/doku/services/ws/userprofile
		 */

		/**
		 * JSEDUITE BPEL SERVICES
		 */
		/*
		 * BPEL: Picture album
		 */
		//new OfflineTesting().testWsrBenchTesting("http://127.0.0.1:9080/PictureAlbumsService/PictureAlbumsPort");
		/*
		 * BPEL: TV Shows
		 *(wsrbench lo identifica come non testabile - esegue tutto, ma non trova testabile l'operazione)
		 *
		 * IT WORKS!
		 */
		//new OfflineTesting().testWsrBenchTesting("http://localhost:9080/TvShowsService/TvShowsPort");
        /*
		 * BPEL: Weather Proxy
		 * 
		 * AN EXTERNAL WEB SERVICE CHANGED: THIS NEEDS AN UPDATE.
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:9080/WeatherProxyService/WeatherProxyPort");
		/*
		 * BPEL: Image Scraper	
		 * 
		 * IT WORKS!!!
		 */			
		//new OfflineTesting().testWsrBenchTesting("http://localhost:9080/ImageScraperService/ImageScraperPort");

        /*
		 * BPEL: Hyper Time Table	
		 *  Need ICAL to make it working
		 */				
		//new OfflineTesting().testWsrBenchTesting("http://localhost:9080/HyperTimeTableService/HyperTimeTablePort");

        /*
		 * BPEL: Feed Reader
		 *(wsrbench lo identifica come non testabile - esegue tutto, ma non trova testabile l'operazione)
		 * However, it works!!!
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:9080/FeedReaderService/FeedReaderPort");
		/*
		 * BPEL: Cached Feed Reader
		 *(wsrbench lo identifica come non testabile - esegue tutto, ma non trova testabile l'operazione)
		 * However, it works!!!
		 */	
		//new OfflineTesting().testWsrBenchTesting("http://localhost:9080/CachedFeedReaderService/CachedFeedReaderPort");

	}
	
	/**
	 * only for personal testing
	 * @see main
	 */
	private String testWsrBenchTesting(String address){
		//prepare the string for wsrbench
		String url=Utils.prepareStringForWsrBench(address);
		//use wsrbench api
		Wsdl wsdl2 = new Wsdl();
		wsdl2.setUrl(url);
		System.out.println(wsdl2.toString());
		isValid=false;
		//set some config parameters
		Configuration config = new Configuration();
		System.out.println("T_SERVICE: Set config information for current test");
		
		//validate url using stuff from Nuno: check if address is reachable
		//validate(wsdl2, null);
                isValid=true;
		if (isValid==false){
			wsdl2.setStatus(Wsdl.NOT_STARTED);
			System.out.println("T_SERVICE: test not started. Address not reachable: "+address);
			return ADDRESS_NOT_REACHABLE;
		}
		System.out.println(wsdl2.toString());

		//if url is valid, the test starts.
		//Set how many faulty requests should be sent for each injected fault.
		//MAX_FAULTY_REQUEST_COUNT=10;
		config.setFaultyRequestCount(Configuration.MAX_FAULTY_REQUEST_COUNT);
			//Number of requests without faults to send to each operation.
		//MAX_PLAIN_REQUEST_COUNT=10;
		config.setPlainRequestCount(Configuration.MAX_PLAIN_REQUEST_COUNT);

		//Define the timeout value for each connection to the server.
		//DEFAULT_TIMEOUT=10000L
		config.setTimeout(Configuration.DEFAULT_TIMEOUT);
		System.out.println("T_SERVICE: WsrBench config information are now set and are:");
		System.out.println("T_SERVICE: "+config.toString());
		System.out.println(wsdl2.toString());

		SynchDriverImpl sinctest = new SynchDriverImpl();
		//get the wsdl and analyzes it
		try {
			wsdl2 = sinctest.submitWsdl(config, wsdl2);
		} catch (DriverException e) {
			System.out.println("T_SERVICE: impossible to retrieve the wsdl file.");
			System.out.println("T_SERVICE: test aborted with result "+ ERROR);
			return ERROR;
		}
		System.out.println(wsdl2.toString());
		AsynchDriverImpl asinctest = new AsynchDriverImpl();
		//set wsdl and config files
		asinctest.setWsdl(wsdl2);
		asinctest.setConfig(config);
		//start the test
		asinctest.startWsdlTestA();
		//System.out.println(wsdl.toString());

		//get result of the test from pt.uc.dei.wsrbench.common.pojo.Wsdl.java
		String result=wsdl2.getStatus();
		//System.out.println(wsdl.toString());
		System.out.println("T_SERVICE: test of "+ url + "completed with result: " + result);
		
		System.out.println("PRINTING wsdl.getOperationList().toString()");
		System.out.println(wsdl2.getOperationList().toString());
		System.out.println("PRINTING size of operationList wsdl.getOperationList().size()");
		System.out.println(wsdl2.getOperationList().size());
		System.out.println("PRINTING element 0 of operationList wsdl.getOperationList().get(0)");
		System.out.println(wsdl2.getOperationList().get(0));
/*		System.out.println("PROVA: wsdl.getOperationList().get(0)");
		
		//1 operation --> wsdl.getOperationList().get(0);
		//parameters --->
		//fault: request and response
		System.out.println("PROVIAMOCI DAI!");
		System.out.println("GET OPERATION NAME");
		System.out.println(wsdl.getOperationList().get(0).getOperationName());
		System.out.println("GET PARAMETER LIST FOR OPERATION NAME");
		System.out.println(wsdl.getOperationList().get(0).getParameterList());
		System.out.println("GET SIZE OF PARAMETER LIST FOR OPERATION NAME");
		System.out.println(wsdl.getOperationList().get(0).getParameterList().size());
		System.out.println("GET FAULT LIST for PARAMETER LIST FOR OPERATION NAME");
		System.out.println(wsdl.getOperationList().get(0).getParameterList().get(0).getFaultList());
		System.out.println("GET NUMBER OF FAULTs");
		System.out.println(wsdl.getOperationList().get(0).getParameterList().get(0).getFaultList().size());
		System.out.println("GET FAULT NUMBER 0 FOR OPERATION 1");	
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(0));
		
		System.out.println("NOW IT IS THE TURN OF OPERATION 1");
		
		System.out.println("GET OPERATION NAME");
		System.out.println(wsdl.getOperationList().get(1).getOperationName());
		System.out.println("GET PARAMETER LIST SIZE");
		System.out.println(wsdl.getOperationList().get(1).getParameterList());
		System.out.println("GET SIZE OF PARAMETER LIST FOR OPERATION NAME");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().size());
		System.out.println("GET FAULT LIST for PARAMETER LIST FOR OPERATION NAME");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList());
		System.out.println("GET NUMBER OF FAULTs");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().size());
		System.out.println("GET FAULT NUMBER 0 FOR OPERATION 1");	
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(0));

		
		System.out.println("GET FAULT NAME FOR FAULT 0");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(0).getFaultName());
		System.out.println("GET FIELD REQUEST of FAULT NUMBER 0");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(0).getRequest());
		System.out.println("GET FIELD RESPONSE of FAULT NUMBER 0");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(0).getResponse());
		System.out.println("GET PROBLEM FOR FAULT NUMBER 0");
		//System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(0).getProblem());
		
		System.out.println("FINDING UBI NUM");
		System.out.println("GET PARAMETER NAME FOR OPERATION LIST X");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0));
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getParameterName());
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getParameterType());
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(1).getFaultName());
		System.out.println("GET FIELD REQUEST of FAULT NUMBER X");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(1).getRequest());
		System.out.println("GET FIELD RESPONSE of FAULT NUMBER X");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(1).getResponse());
		System.out.println("GET PROBLEM FOR FAULT NUMBER X");
		System.out.println(wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(1).getProblem());


		
		String str=wsdl.getOperationList().get(1).getParameterList().get(0).getFaultList().get(1).getResponse();
		String str1=str.replace('\'', ' ');
		System.out.println("TENTATIVO NUMERO 1");
		System.out.println(str1);

		
		String str2=str.replaceAll("\'", "'");
		System.out.println("TENTATIVO NUMERO 2");
		System.out.println(str2);

		String str3=str.replaceAll("'", "\'");
		System.out.println("TENTATIVO NUMERO 3");
		System.out.println(str3);
		

		String str4=str.replaceAll("'", "\\\\'");
		System.out.println("TENTATIVO NUMERO 4");
		System.out.println(str4);

*/		
		if(result.equals(FINISHED)){
			return result;			
		}else{
			System.out.println("T_SERVICE: STRANGE STUFF HERE!Check carefully!");
			return result;
		}			
	}
}
