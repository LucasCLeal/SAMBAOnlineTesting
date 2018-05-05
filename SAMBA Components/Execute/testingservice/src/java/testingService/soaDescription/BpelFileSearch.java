package testingService.soaDescription;

import java.io.*;
import java.util.Scanner;

import testingService.Layers;

/**
 * Search in the BPEL file for strings, parameters, special values...
 * @author Andrea
 *
 */
public class BpelFileSearch {

	File file;
	/*
	 * String array where I record the wsdl addresses I found in the bpel file.
	 * Max 20 wsdl addresses.
	 */
	private String searchString[];

	public BpelFileSearch(){
		searchString=new String [20];
	}
	
	/**
	 * search the BPEL file for wsdl locations
	 * @return an array which contains all the wsdl location found in the current bpel file!
	 */
	public String[] searchWsdlInBpel(int k)  {
		int numberOfWsdlLocation=0;
		Scanner scanner=null;
	    String[] str=null;
		try{
			scanner = new Scanner(Layers.engine.bpelFile[k]);
		}catch(Exception e){
			System.out.println("T_SERVICE: Bpel file to scan not found");
			e.printStackTrace();
			return new String[1];
		}
		try {
	    	//first use a Scanner to get each line
	    	while (scanner.hasNextLine()){
	    		str=processLineForWsdlLocation(scanner.nextLine());
	    		if(str.length != 0){
	    			//there is a wsdl file in str
                    for(int i=0; i<str.length; i++){
    	    			searchString[numberOfWsdlLocation]=str[i];
            			numberOfWsdlLocation++;
                    }
	    		}
	      }
	    }catch(Exception e){
	    	System.out.println("T_SERVICE: unable to do a processLine(scanner.nextLine)");
	    	System.out.println("Or maybe it is searchString[20]: array index out of bound");
	    	System.out.println("At most, you can store 20 wsdl files for bpel file");
	    	e.printStackTrace();
	    }
	    finally {
	        //ensure the underlying stream is always closed
	        scanner.close();
	    }

	    String finalString[]=new String[numberOfWsdlLocation];
	    for(int i=0; i<numberOfWsdlLocation; i++ ){
		    finalString[i]=searchString[i];
	    }
	    return finalString;
	}

	  /** 
	   * Process a single string to find the wsdl location:
       * MAX 10 WSDL FILES IN A BPEL FILE
       *
       * DEVE ESSERE SCRITTO
       *
       *        location="
       *
       * SENZA SPAZI
	   * @param string in input
	   * @return wsdl location; null if the string does not contain any wsdl location
	   */
	  protected String[] processLineForWsdlLocation(String aLine){
            String[] res=new String[10];
            int counter=0;
            int token, token1;
            while(aLine!=null){
            if(aLine.contains("location") && aLine.contains(".wsdl")){
                token=aLine.indexOf("location");
                if(aLine.contains(".wsdl")){
                    token1=aLine.indexOf(".wsdl");
                    String prova=aLine.substring(token+10, token1+5);
                    aLine=aLine.substring(token1);
                    res[counter]=prova;
                    counter++;
                }
            }
            else
            {
                aLine=null;
            }
            }
          /*

           */
            String[] finalStr=new String[counter];
           for(int i=0; i< counter; i++){
               finalStr[i]=res[i];
           }
            return finalStr;
	  }
	  
	  /**
	   * I have a line like this, with no whitespaces:
	   *       location="AirlineReservation.wsdl"
	   * Need to extract AirlineReservation.wsdl
	   * @param the input line as location="AirlineReservation.wsdl"
	   * @return the extracted line as AirlineReservation.wsdl
	   */
	  protected String extractWsdlLocation(String str){
		  int i=str.length();
		  //location=" .... "
		  //sottostringa tra 10 e LunghezzaString-1 (il carattere ")
		  String substring=str.substring(10, i-1);	
		  return substring;
	  }
}

