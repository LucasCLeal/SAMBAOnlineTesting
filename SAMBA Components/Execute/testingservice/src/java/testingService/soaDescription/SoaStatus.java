package testingService.soaDescription;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import java.util.logging.Level;
import java.util.logging.Logger;
import testingService.config.ConfigParameters;

/**
 * The only rule of this class is to contain the variable
 * SOA_STATUS that keeps the soa status 0 and 1, and perform updates of the status
 */
public class SoaStatus {
	private static int soa;
	
	/**
	 * invoked to re-set and read the variable soa status
	 * @return soa status
	 */
	public static int readStatus(){
		Properties configFile = new Properties();
        FileInputStream f=null;
		try{
            f=new FileInputStream(ConfigParameters.soaStatus);
			configFile.load(f);
		}catch(Exception e){
			System.out.println("T_ENGINE: soastatus file not readable");
            return 0;
		}
		soa=Integer.valueOf(configFile.getProperty("SOA_STATUS")).intValue();
        try {
            f.close();
        } catch (IOException ex) {
			System.out.println("T_ENGINE: unable to close soastatus file");
        }
        return soa;
	}
	
	/**
	 * @Deprecated
	 * I doubt that this should really be used.
	 * Set the soaStatus value here and in the config file.
	 * @param the new soa status
	 */
	public static void setSoa(int i){
		if (i!= soa){
			soa=i;
			Properties configFile = new Properties();
            FileInputStream f=null;
			try{
                f=new FileInputStream(ConfigParameters.soaStatus);
				configFile.load(f);
			}catch(Exception e){
				System.out.println("T_ENGINE: soastatus file not readable");
			}
			configFile.setProperty("SOA_STATUS", Integer.toString(soa));
            try {
                f.close();
            } catch (IOException ex) {
                System.out.println("T_ENGINE: unable to close soastatus file");
            }
			
		}
	}
}
