package testingService.soaDescription;

import java.io.File;
import java.util.Scanner;
import org.apache.commons.lang.ArrayUtils;
import testingService.config.ConfigParameters;
import testingService.utils.Utils;

/**
 * search in wsdl files for particular stuff
 */
public class WsdlFileSearch {

	/**
	 * Explore the set of wsdl files to find addresses of services
	 * @param array which contains a set of wsdl files
	 * @return the addresses of the wsdl files
	 */
	public String[] wsdlAddressesSearch(String str[]){
		//looks in directory STORED_WSDL_FILE for the required file name
		String[] addresses=null; 		
		for(int i=0; i<str.length; i++){
			String[] a=exploreWsdlFile(str[i]);
			addresses = (String[]) ArrayUtils.addAll(addresses, a);

		}
		//get the files
		return addresses;
	}
	
	
	/**
	 * search the single wsdl file for addresses location, like:
	 * <soap:address location="http://localhost:8080/HotelStatusServiceLocation"/>
	 * @param name of the wsdl file stored in STORED_WSDL_FILE
	 * @return an array which contains the addresses found in the file
	 */
	public String[] exploreWsdlFile(String str)  {
		String[] addresses=null;
		//variabile di appoggio
		String[] app= new String[1];
		File wsdlFile= new File(ConfigParameters.storedWsdlFile+"/"+str);
		Scanner scanner=null;
		try{
			scanner = new Scanner(wsdlFile);
		}catch(Exception e){
			System.out.println("T_SERVICE: Wsdl file to scan not found in path "+ConfigParameters.storedWsdlFile+"/"+str);
			e.printStackTrace();
			return new String[1];
		}
	    try {
	    	//first use a Scanner to get each line
	    	while (scanner.hasNextLine()){
	    		app[0]=null;
	    		app[0]=processLineForAddress(scanner.nextLine());
	    		if(app[0]!=null){
	    			//there is an address location="http: in the string considered
		    		System.out.println("T_SERVICE: The following addresses was found in the wsdl: " +app[0]);
		    		addresses = (String[]) ArrayUtils.addAll(addresses, app);

	    		}
	      }
	    }catch(Exception e){
	    	System.out.println("T_SERVICE: unable to do a \"processLine(scanner.nextLine)\" to search the wsdl file");
	    	e.printStackTrace();
	    }
	    finally {
	        //ensure the underlying stream is always closed
	        scanner.close();
	      }
	    return addresses;
	}

	  /** 
	   * Process a single string to find the address: sample string
	   * <soap:address location="http://localhost:8080/HotelStatusServiceLocation"/>
	   * @param string in input
	   * @return the address; null if the string does not contain any relevant address
	   */
	  protected String processLineForAddress(String aLine){
		  //remove leading and trailing white spaces
		  //remove spaces
		  aLine=Utils.removeSpaces(aLine);

		  //<soap:addresslocation="http://localhost:8080/HotelStatusServiceLocation"/>
		  if(aLine.startsWith("<soap:addresslocation"))
		  {
			  int i=aLine.length();
              return aLine.substring(23, i-3).replace("${HttpDefaultPort}", "9080");
		  }else{
			  return null;
		  }
          
	  }
}
