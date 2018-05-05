package testingService.soaDescription;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;
import testingService.config.ConfigParameters;

/**
 * Utility class to identify the kind of service corresponding to a wsdl address
 */
public class KindOfService {

	/**
	 * date of last modification of the file that enlists the known services
	 */
	public static long lastModified=new File(ConfigParameters.knownServices).lastModified() ;
 
	
	/**
	 * String that is written in the database to identify the kind of service
	 * CONTROLLED SERVICE
	 */
	public static final String CONTROLLED="CONTROLLED";

	/**
	 * String that is written in the database to identify the kind of service
	 * WITHIN_REACH SERVICE
	 */
	public static final String WITHIN_REACH="WITHIN_REACH";
	
	/**
	 * String that is written in the database to identify the kind of service
	 * BPEL_ENGINE 
	 */
	public static final String BPEL_SERVICE="BPEL_SERVICE";
	
	/**
	 * String to identify that the service was gathered looking at the CONFIG file knownService.properties
	 * @see DBConnection table originated_by
	 * @see BPEL_JAR
	 */

	public static final String CONFIG_FILE="CONFIG_FILE";

	/**
	 * String to identify that the service was gathered looking at the BPEL JAR file
	 * @see CONFIG_FILE
	 * @see DBConnection table originated_by
	 */
	public static final String BPEL_JAR="BPEL_JAR";
	
	/**
	 * get method to use the controlled variable
	 */
	public static String getControlled(){
		return CONTROLLED;
	}
	
	/**
	 * get method to use the WithinReach variable
	 */
	public static String getWithinReach(){
		return WITHIN_REACH;
	}

	/**
	 * get method to use the BPEL_SERVICE variable
	 */	
	public static String getBpelService(){
		return BPEL_SERVICE;
	}

	/**
	 * Discovers if a service is CONTROLLED, BPEL_ENGINE or WITHIN_REACH
	 * (basically, if it is enlisted in the knownServices.properties, it is CONTROLLED or BPEL_ENGINE
	 * otherwise it is WITHIN_REACH
	 */
	public static String discoverIdentity(String serviceAddress){
		Properties configFile = new Properties();
        FileInputStream a=null;
		try{
             a=new FileInputStream(ConfigParameters.knownServices);
			configFile.load(a);
		}catch(Exception e){
			System.out.println("T_SERVICE: knownServices.properties file not readable");
            System.out.println("T_SERVICE: "+e);
		}

        //check if it is enlisted amongst bpel_engine
		int numberOfBpelService = Integer.valueOf(configFile.getProperty("BPEL_SERVICES")).intValue();
		for(int i=1; i<=numberOfBpelService; i++){
			if(serviceAddress.equals(configFile.getProperty("BPEL"+i))){
                try {
                    a.close();
                } catch (IOException e) {
            System.out.println("T_SERVICE: "+e);
                }
				return BPEL_SERVICE;
			}
		}
        //check if it is enlisted amongst Controlled
		int numberOfControlledService = Integer.valueOf(configFile.getProperty("CONTROLLED_SERVICES")).intValue();
		for(int i=1; i<=numberOfControlledService; i++){
			if(serviceAddress.equals(configFile.getProperty("CONTROLLED"+i))){
                try {
                    a.close();
                } catch (IOException e) {
            System.out.println("T_SERVICE: "+e);
                }
				return CONTROLLED;
			}
		}
		//it is a within_reach (may not be enlisted, but it is a within reach)
        try {
            a.close();
        } catch (IOException e) {
            System.out.println("T_SERVICE: "+e);
        }
		return WITHIN_REACH;
	}
	
	/**
	 * Verify if config file has been modified or not.
	 * @param valore di confronto per la modifica del CONFIG FILE: lastModified e' pari all'ultima modifica, o piu vecchio?
	 * se piu' vecchio, si ritorna true
	 * @return true if it has been modified since last check, false otherwise
	 */
	public static boolean isModified(){
		long timestamp = new File(ConfigParameters.knownServices).lastModified();
		if(timestamp > lastModified){
			lastModified=timestamp;
			return true;
		}
		return false;
	}
	
	/**
	 * number of controlled services enlisted
	 * @return
	 */
	public static int numberOfControlled(){
		Properties configFile = new Properties();
		FileInputStream f=null;
        try{
             f = new FileInputStream(ConfigParameters.knownServices);
			configFile.load(new FileInputStream(ConfigParameters.knownServices));
		}catch(Exception e){
			System.out.println("knownServices.properties file not readable");
		}
        try {
            f.close();
        } catch (IOException ex) {
            System.out.println("T_SERVICE: "+ex);
        }
		return Integer.valueOf(configFile.getProperty("CONTROLLED_SERVICES")).intValue();

	}
	
	/**
	 * number of bpel services enlisted
	 * @return
	 */
	public static int numberOfBpel(){
		Properties configFile = new Properties();
		FileInputStream f=null;
		try{
            f=new FileInputStream(ConfigParameters.knownServices);
			configFile.load(f);
		}catch(Exception e){
			System.out.println("knownServices.properties file not readable");
		}
        try {
            f.close();
        } catch (IOException ex) {
            Logger.getLogger(KindOfService.class.getName()).log(Level.SEVERE, null, ex);
        }
		return Integer.valueOf(configFile.getProperty("BPEL_SERVICES")).intValue();
	}
	
	/**
	 * number of within reach services enlisted
	 * @return
	 */
	public static int numberOfWithinReach(){
		Properties configFile = new Properties();
        FileInputStream f= null;
		try{
            f= new FileInputStream(ConfigParameters.knownServices);
			configFile.load(f);
		}catch(Exception e){
			System.out.println("knownServices.properties file not readable");
		}
        try {
            f.close();
        } catch (IOException ex) {
            System.out.println("T_SERVICE: "+ex);
        }
		return Integer.valueOf(configFile.getProperty("WITHIN_REACH_SERVICES")).intValue();
	}
	
	/**
	 * get the address of the i-th controlled service
	 * 
	 */
	public static String getAddressControlled(int i){
		Properties configFile = new Properties();
        FileInputStream f= null;
		try{
            f= new FileInputStream(ConfigParameters.knownServices);
			configFile.load(f);
		}catch(Exception e){
			System.out.println("knownServices.properties file not readable");
		}
        try {
            f.close();
        } catch (IOException ex) {
            System.out.println("T_SERVICE: "+ex);
        }
		return configFile.getProperty("CONTROLLED"+i);
	}
	
	/**
	 * get the address of the i-th bpel service
	 * 
	 */
	public static String getAddressBpelEngine(int i){
		Properties configFile = new Properties();
        FileInputStream f= null;
		try{
            f= new FileInputStream(ConfigParameters.knownServices);
			configFile.load(f);
		}catch(Exception e){
			System.out.println("knownServices.properties file not readable");
		}
        try {
            f.close();
        } catch (IOException ex) {
        }
		return configFile.getProperty("BPEL"+i);
	}

	/**
	 * get the address of the i-th within reach service
	 * 
	 */
	public static String getAddressWithinReach(int i){
		Properties configFile = new Properties();
        FileInputStream f= null;
		try{
            f= new FileInputStream(ConfigParameters.knownServices);
			configFile.load(f);
		}catch(Exception e){
			System.out.println("knownServices.properties file not readable");
		}
        try {
            f.close();
        } catch (IOException ex) {
            System.out.println("T_SERVICE: "+ex);
        }
		return configFile.getProperty("WITH_REACH"+i);
	}
}
