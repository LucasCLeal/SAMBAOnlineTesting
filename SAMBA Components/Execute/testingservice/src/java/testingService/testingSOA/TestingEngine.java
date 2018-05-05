package testingService.testingSOA;

import java.io.File;

import testingService.Layers;
import testingService.soaDescription.SoaDescription;
import testingService.soaDescription.SoaStatus;

/**
 * This is the Engine of the Testing Service
 * The Engine looks at the description of the SOA, and decides which service to test.
 * The controlled services are self-tested and only provides results.
 *
 */
public class TestingEngine extends Thread {


	/**
	 * Initialized to false;
	 * if some modification of the bpel file are detected,
	 * it is set to true;
	 * then the run methods can start understanding what changes and the new soa description
	 */
	public boolean modifiedBpelFile;

    /**
     * TODO very bad way of doing things
     * IP dell web service bpel con cui correntemente si sta interagendo.
     * serviceNameBpelModified Nome del servizio
     *
     */
    public String addressBpelModified="address not set";
    public String serviceNameBpelModified="address not set";

    /**
     * same as above
     * 
     */
    public String addressControllerModified="address not set";
    public String serviceNameControlledModified="address not set";

    /**
     * same as above
     */

    public String listOfDiscoveredServiceUsingControlled="addresses not set";

    /**
	 * Initialized to false;
	 * if some modification of the config file are detected,
	 * it is set to true;
	 * then the run methods can start understanding what changes and the new soa description
	 */
	public boolean modifiedConfiguration;
	
	/**
	 * Initialized to false;
	 * if some modification of the controller engine is detected,
	 * it is set to true;
	 * then the run methods can start understanding what changes and the new soa description
	 */
	public boolean modifiedControlled;
	
	/**
	 * starting phase: when it is true, we are "starting" and doing initial 
	 * queries
	 * when it is false, we are in the "running" phase
	 */
	public boolean startingPhase;

	/**
	 * the Bpel files involved
	 */
	public File bpelFile[];

	/**
	 * the wsdl files involved
	 */
	public File wsdlFile[];

	/**
	 * the number of wsdl files in the array
	 */
	public int numberOfWsdlFile;
	
	/**
	 * the number of BPEL files in the array
	 * (star numbering from 1)
	 * 0 means no bpel file availables
	 */
	public static int numberOfBpelFile;

	
	/**
	 * Offline testing object
	 * Object that performs the offline tests
	 */
	public OfflineTesting offTesting;
	
	/**
	 * Runtime testing object
	 * Object that performs the runtime tests
	 */
	public RunTimeTesting runTimeTesting;

	public TestingEngine(){
		bpelFile=new File[5];
		numberOfBpelFile=0;
		wsdlFile= new File[20];
		numberOfWsdlFile=0;	
		startingPhase=true;
		modifiedBpelFile=false;
		modifiedControlled=false;
		modifiedConfiguration=false;
		offTesting= new OfflineTesting();
		runTimeTesting=new RunTimeTesting();
	}

	/**
	 * This run method contains the activities that are performed by the testing engine.
	 */
	public void run(){
		int soaStatus;
		//set Soa status
		System.out.println("T_SERVICE: set soa status from config file");
		soaStatus=readAndSetSoaStatus();
		System.out.println("T_SERVICE: soa status is now set");

		//do testing without user and without system evolution
		//get fresh bpel file and update soa description
/*		while(getFreshBpel()==false){
			System.out.println("T_SERVICE: waiting for fresh bpel files");
			try{
				sleep(30000);
			}catch(Exception e){}
		}
		System.out.println("T_SERVICE: fresh bpel files found: starting soa description");
*/	
		//get soa description:
		//get description from config file information
		updateSoaDescription(2);
		System.out.println("T_SERVICE: soa description from config file completed");
		//get description from bpel information
		//updateSoaDescription(1);
		//System.out.println("T_SERVICE: soa description from bpel file completed");
		//get description from controlled service
		//updateSoaDescription(3);
		//System.out.println("T_SERVICE: soa description from controlled service completed");

		//starting phase is now finished
		startingPhase=false;
		
		while(true){
			//if soa status changed: RE-UPDATE IT
			if(soaStatus != SoaStatus.readStatus()){
				System.out.println("T_SERVICE: soa status changed");
				soaStatus=readAndSetSoaStatus();
			}
			//if soaStatus==0, i.e. no user on the system and no system evolution
			//we do the "offline" testing (closed service)
			if(soaStatus==0 && offTesting.isStarted()==false){
				System.out.println("T_SERVICE: starting testing on closed service");
				doOfflineTesting();
			} else if(soaStatus==1 && runTimeTesting.isStarted()==false) {
			//soaStatus=1, there are user, we are online and need to virtualize stuff
			//we do the "runtime" testing
				System.out.println("T_SERVICE: starting runtime testing");
				doRunTimeTesting();
			} else if(runTimeTesting.isStarted()==offTesting.isStarted()){
				System.out.println("T_SERVICE: dangerous situation in Testing Engine run. Parameters:");
				System.out.println("T_SERVICE: soaStatus = "+ soaStatus +" , offTesting.isStarted() = "+
						offTesting.isStarted()+" ,  runTimeTesting.isStarted() = "+runTimeTesting.isStarted());
			}
			//check changes in the soa description
			updateSoaDescription();
			try {
				//sleeps 1 minute
				sleep(60000);
			} catch (InterruptedException e) {
				System.out.println("T_SERVICE: exception in the sleep of thread TestingEngine");
				e.printStackTrace();
			}

		}
	}
	
	/**
	 * Do the offline testing
	 * If the run time testing is currently running, stops doing runtime testing
	 */
	public synchronized void doOfflineTesting(){
		//if we are currently doing offline testing
		if(offTesting.isStarted()==true){
			System.out.println("T_SERVICE: OffTesting is already started. This is very strange. Please check method doOfflineTesting.");
		} else if(runTimeTesting.isStarted()==true){
			//this blocks until the runtimeTesting is not shut down
			runTimeTesting.kill();
			//start our 'closed service' (offline) testing
			new Thread(offTesting).start();
		}//if we are not doing any test, and we start now
		else{
			new Thread(offTesting).start();			
		}
	}
	
	/**
	 * Do the runtime testing
	 * If the offline testing is currently running, stops doing it
	 */
	public void doRunTimeTesting(){
		//if we are currently doing runtime testing
		if(runTimeTesting.isStarted()==true){
			System.out.println("T_SERVICE: RunTime testing is already started. This is very strange. Please check method doRunTimeTesting.");
		}else if(offTesting.isStarted()==true){
			//this blocks until the offlinetTesting is not shut down
			offTesting.kill();
			new Thread(runTimeTesting).start();
		}//if we are not doing any test, and we start now
		else {
			new Thread(runTimeTesting).start();
		}
	}

	/**
	 * Updates the description of the soa.
	 * - if new info received from the bpel engine, updates them
	 * - if the config file with the list of services has changed, updates it
	 * - if new info received from the controlled services, updates them
	 * Invokes updateSoaDescription(int i).
	 * The soa description for updateSoaDescription(1) and updateSoaDescription(3)
	 * is updated automatically on the basis of the inputs received
	 */
	public void updateSoaDescription(){
		//receives info on modified bpel
		if(modifiedBpelFile==true){
			modifiedBpelFile=false;
			updateSoaDescription(1);
		}
		//check if someone changed the config file for services
		if(checkFileConfigurationChanges()==true){
			updateSoaDescription(2);
		}
		//receives info on modified controlled services
		if(modifiedControlled==true){
			modifiedControlled=false;
			updateSoaDescription(3);
		}
	}

	/**
	 * set the value of SOA STATUS = the value in soaDescription.SoaStatus
	 * this value is used in the testing service to understand if it has to do the runtime testing
	 * or the offline (without user) testing
	 * this value is also communicated to the bpel engine
	 */
	public int readAndSetSoaStatus(){
		int i=SoaStatus.readStatus();
		return i;
	}
	
	/**
	 * get the SOA value (0 or 1) in the BPEL engine
	 */
/*	public int getSoaFromBpel(){
		int i=Layers.layerApp.askSoaValue();
		SoaStatus.setSoa(i);
		System.out.println("soa Status = "+i);
		return i;
	}
*/
	/**
	 * the client asks for a fresh bpel file.
	 * It is invoked only the first time this service is started.
	 */
/*	public synchronized boolean getFreshBpel(){
		//discovers the number of bpel files
		if(refreshNumberOfBpelFile()>0){
			//ask the fresh BPEL files
			//when this ends, file are stored in bpelFile[];
			return Layers.layerApp.getFreshBpel();			
		}
		return false;
	}
*/
	/**
	 * refresh the value of variable numberOfBpelFile and return that value
	 */
/*	public int refreshNumberOfBpelFile(){
		numberOfBpelFile= Layers.layerApp.getNumberOfBpelFile();
		System.out.println("T_SERVICE: the number of bpel files to receive is "+numberOfBpelFile);
		return numberOfBpelFile;
	}
*/
	/**
	 * Draw description of the soa
	 * 
	 * @ param 
	 * if int == 1 a change in the BPEL files and jar happened
	 * if int == 2, a change in the config list of controlled services has happened
	 * if int == 3 a change in the controlled service internals has happened
	 */
	public void updateSoaDescription(int i){
		if(i == 1){
			System.out.println("T_SERVICE: changes detected in bpel jar: updating soa description.");
			new SoaDescription().updateBPEL(addressBpelModified, serviceNameBpelModified);
		}
        if(i==2){
			System.out.println("T_SERVICE: changes detected in service list-configuration file: updating soa description.");
			new SoaDescription().updateConfiguration();
		}
        if(i==3){
			System.out.println("T_SERVICE: changes detected in a controlled service: updating soa description.");
            //indirizzo completo wsdl controller, ip del controller
            new SoaDescription().updateControlled(addressControllerModified, addressControllerModified.substring(7, addressControllerModified.indexOf(':', 9)),addressControllerModified);
            //the following are only for debug, can be removed
            listOfDiscoveredServiceUsingControlled="value set to default in updateControlled";
            addressControllerModified="value set to default in updateControlled";

		}
	}
	
	/**
	 * when new bpel files are detected there is the need to do an update in the soa description; 
	 * i.e., to invoke updateSoaDescription(1)
	 * @param updated number of bpel file
	 */
	public void receivedModifiedBPELfiles(int i, String address, String servicename){
		if(startingPhase==false){
            addressBpelModified=address;
            serviceNameBpelModified=servicename;
			numberOfBpelFile=i;
			modifiedBpelFile=true;
		}
	}

    /**
     *
     * @param address address of the controlled service considered
     * @param services list of the services discovered
     */
	public void controlledIsModified(String address, String services){
		if(startingPhase==false){
            addressControllerModified=address;
            listOfDiscoveredServiceUsingControlled=services;
            modifiedControlled=true;
		}
	}

	/**
	 * check configuration of the soa in the file knownServices.properties
	 * if the file has changes, return true
	 * if it has not changed, return false
	 * @return true if file is changed
	 */
	public boolean checkFileConfigurationChanges(){
		return  new SoaDescription().isConfigFileModified();
	}	
}
