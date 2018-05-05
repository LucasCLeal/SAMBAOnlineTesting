package testingService.utils;

import java.net.UnknownHostException;
import java.util.StringTokenizer;

/**
 * Utility class
 */
public class Utils {
	  
	/**
	 * Remove white spaces in a string
	 * @param input string
	 * @return string without white spaces
	 */	
	public static String removeSpaces(String s) {
	  s=s.trim();
	  StringTokenizer st = new StringTokenizer(s," ",false);
	  String t="";
	  while (st.hasMoreElements()) t += st.nextElement();
	  return t;
	}
	
	/**
	 * prepare the string to do test with WSRBench:
	 * - replace localhost with 127.0.0.1
	 * - add ?wsdl at the end
	 * @param address of the service, e.g. http://localhost:8080/AirlineServiceLocation
	 * @return the formatted string (the address to give to wsrbench)
	 */
	public static String prepareStringForWsrBench(String address){
		address=address+"?wsdl";
		return address;
	}

	/**
	 * replace localhost with its real address
	 * @param the initial address
	 * @return
	 */
	public static String replaceHost(String localhost, String realAddress){
        localhost=localhost.replaceFirst("localhost", realAddress);
       localhost= localhost.replaceFirst("Localhost", realAddress);
       localhost= localhost.replaceFirst("LocalHost", realAddress);
       localhost= localhost.replaceFirst("LOCALHOST", realAddress);
      localhost=  localhost.replaceFirst("127.0.0.1", realAddress);
		return localhost;
	}

    /**
	 * Prepare the string for the insert in the Postgresql DB: substitute the special character ' with an " 
	 * @param str
	 * @return the string with special characters removed
	 */
	public static String prepareStringForDB(String str){
		return str.replace('\'','"');
	}
}
