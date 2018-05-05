package testingService.config;

import java.util.Properties;
import java.io.*;

public class ConfigParameters {
	/**
	 * address of the BPEL engine socket
	 */
	public static String bpelAddress;
	

	/**
	 * it's where I store the bpel files received
	 */
	public static String storedBpelFile;

	/**
	 * it's where I store the wsdl files received
	 */
	public static String storedWsdlFile;
	
	/**
	 * name of the POSTGRESQL database that I use
	 */
	public static String database;

	/**
	 * name of the POSTGRESQL database that is my "root" database
	 */
	public static String databaseMaster;

	/**
	 * name and path of the knownServices.properties file
	 */
	public static String knownServices;

	/**
	 * name and path of the retry.properties file
	 */
	public static String retryParameters;

	/**
	 * name and path of the soastatus.properties file
	 */
	public static String soaStatus;
	
	/**
	 * POSTGRESQL database USER NAME
	 */
	public static String databaseUName;
	
	/**
	 * POSTGRESQL database PASSWORD
	 */
	public static String databasePassword;

	/**
	 * load config parameters
	 * @str address of the config file
	 */
	public static void loadConfigFile(String str){
		Properties configFile = new Properties();
		try{
			configFile.load(new FileInputStream(str));
		}catch(Exception e){
			System.out.println("T_SERVICE: config.properties file not readable");
			//System.exit(0);
			return;
		}
		
		bpelAddress = configFile.getProperty("BPEL_ENGINE_SOCKET_ADDRESS");
		storedBpelFile = configFile.getProperty("STORED_BPEL_FILE");
		databaseUName = configFile.getProperty("DB_USERNAME");
		database = configFile.getProperty("DATABASE");
		databaseMaster = configFile.getProperty("DATABASE_MASTER");
		databasePassword = configFile.getProperty("DB_PASSWORD");
		storedWsdlFile=configFile.getProperty("STORED_WSDL_FILE");
		knownServices= configFile.getProperty("KNOWN_SERVICES_PROPERTIES");
		soaStatus= configFile.getProperty("SOA_STATUS_PROPERTIES");
		retryParameters=configFile.getProperty("RETRY_PARAMETERS");
	}
	
}
