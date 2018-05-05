package testingService.soaDescription;

import java.sql.ResultSet;

import testingService.Layers;
import testingService.testingSOA.TestingEngine;
import testingService.utils.Utils;

/**
 * Contains the description of the SOA
 */
public class SoaDescription {

	/**
	 * Current scenario: necessary for tracing soa evolution.
     * When invoking: keep trace of soa evolution, we add a counter to the current
     * scenario.
	 */
	static int scenario=1;
	/**
	 * false if there have never been previous invocation of SOA DESCRIPTION.
	 */
	static boolean isInitialized=false;
	
	/**
	 * default constructor
	 */
	public SoaDescription(){
	}

	/**
	 * When new (fresh or updated) BPEL information is stored,
	 * the SOA description is updated
	 * WE ARE SEARCHING THE     		
	 * location= .... .wsdl"
	 * the part "location" and .wsdl"
	 * 
	 * --> for each bpel file, finds the command "location"
	 *	--> if .wsdl, get the corresponding file (we consider only relative paths)
	 *	--> get the address in the wsdl
	 * --> updates soa description
	 *
	 * WE ADOPT THE IMPORT COMMAND TO IMPORT WSDL FILES (from bpel specification):
	 * 
	 * Location. The location attribute contains a URI indicating the location of a document that 
	 * contains relevant definitions. The location URI may be a relative URI, following the usual 
	 * rules for resolution of the URI base (XML Base and RFC 2396). The location attribute is 
	 * optional. An <import> element without a location attribute indicates that external definitions 
	 * are used by the process but makes no statement about where those definitions may be found. The 
	 * location attribute is a hint and a WS-BPEL processor is not required to retrieve the document 
	 * being imported from the specified location.
	 * 
	 * A WS-BPEL process definition MUST import all XML Schema and WSDL definitions 
	 * it uses. This includes all XML Schema type and element definitions, all WSDL port 
	 * types and message types as well as <vprop:property> and <vprop:propertyAlias> definitions 
	 * used by the process. [SA00053], [SA00054] A WS-BPEL processor MUST verify that all message 
	 * parts referenced by a <vprop:propertyAlias>, <from>, <to>, <fromPart>, and <toPart> are 
	 * found in their respective WSDL message definitions. In order to support the use of definitions 
	 * from namespaces spanning multiple documents, a WS-BPEL process MAY include more than one import 
	 * declaration for the same namespace and importType, provided that those declarations include 
	 * different location values. <import> elements are conceptually unordered. [SA00014] A WS-BPEL process 
	 * definition MUST be rejected if the imported documents contain conflicting definitions of a component 
	 * used by the importing process definition (as could be caused, for example, when the XSD redefinition 
	 * mechanism is used).
	 * 
	 * An example:
	 *     <import namespace="http://localhost/BluePrint5/Reservation"
     *      location="Reservation.wsdl"
     *       importType="http://schemas.xmlsoap.org/wsdl/"></import>
	 */
	public void updateBPEL(String ipBpel, String bpelservicename){
    //ipBpel e' l'ip del servizio bpel che usiamo come riferimento

        DBConnection.cancelInOriginatedBy(KindOfService.BPEL_JAR, ipBpel);
		System.out.println("T_SERVICE: updating soa description looking at the bpel file");
		for (int i =0; i< TestingEngine.numberOfBpelFile; i++){
			String wsdlNames[];
			wsdlNames=new BpelFileSearch().searchWsdlInBpel(i);

            getWSDLFiles(wsdlNames);
			String address[]=new WsdlFileSearch().wsdlAddressesSearch(wsdlNames);
			populationFromBpel(address, ipBpel, bpelservicename);
			System.out.println("T_SERVICE: finished populating soa description based on the bpel file");
		}
		//delete values that are not used anymore
		DBConnection.synchronizeTables(ipBpel);
        //trace the evolution of the SOA
        DBConnection.traceSoaEvolution(scenario++);
	}


    /**
     * questa fa anche la cancellazione
     * 
     * @param controlledName indirizzo wsdl controller
     * @param ipController indirizzo ip del controller in esame
     */
    public void updateControlled(String controlledName, String ipController, String controllerservicename){
        DBConnection.cancelInOriginatedBy(KindOfService.CONTROLLED, ipController);
		System.out.println("T_SERVICE: updating soa description looking at the information received from a controlled service");
		String tmp= Layers.engine.listOfDiscoveredServiceUsingControlled;

        
        //prendi un indirizzo e aggiorna il db conseguentemente
        populationFromControlled(tmp, controlledName, ipController, controllerservicename);
        System.out.println("T_SERVICE: finished populating soa description based on the controlled service");
		//delete values that are not used anymore
		DBConnection.synchronizeTables();
        //trace the evolution of the SOA
        DBConnection.traceSoaEvolution(scenario++);
    }
    
	/**
	 * update the soa description because of a change in the configuration file
	 * knownServices.properties
	 */
	public void updateConfiguration(){
		//clean rows in originated by; removed those that have column originated_by = configuration file
		DBConnection.cancelInOriginatedByForConfig(KindOfService.CONFIG_FILE);
		//add the controlled services
		System.out.println("T_SERVICE: updating soa description looking at configuration");
		//add the bpel engine services
		for(int i=1; i <= KindOfService.numberOfBpel(); i++){
			String str=KindOfService.getAddressBpelEngine(i);
			//if there already is the address in the database, do nothing (only add to originated_by)
			if(DBConnection.searchAddressinDb(str)==true){
				DBConnection.updateOriginatedBy(str, KindOfService.CONFIG_FILE, KindOfService.getBpelService());
				//if it was not classified as bpel engine, now it is
				DBConnection.updateAddress(str, KindOfService.getBpelService());
			}else{
				//insert the value and set time address_gathered
				DBConnection.insertNewAddress(str, KindOfService.getBpelService(), KindOfService.CONFIG_FILE);
			}
		}

		for(int i=1; i <= KindOfService.numberOfControlled(); i++){
			String str=KindOfService.getAddressControlled(i);
			//if there already is the address in the database, do nothing (only add to originated_by)
			if(DBConnection.searchAddressinDb(str)==true){
				DBConnection.updateOriginatedBy(str, KindOfService.CONFIG_FILE, KindOfService.getControlled());
				//if it was classified as within reach, now it is controlled
				DBConnection.updateAddress(str, KindOfService.getControlled());
			}else{
				//insert the value and set time address_gathered
				DBConnection.insertNewAddress(str, KindOfService.getControlled(), KindOfService.CONFIG_FILE);
			}
		}
		//add the within reach services
		for(int i=1; i <= KindOfService.numberOfWithinReach(); i++){
			String str=KindOfService.getAddressWithinReach(i);
			//if there already is the address in the database, do nothing (only add to originated_by)
			if(DBConnection.searchAddressinDb(str)==true){
				DBConnection.updateOriginatedBy(str, KindOfService.CONFIG_FILE, KindOfService.getWithinReach());
			}else{
				//insert the value and set time address_gathered
				DBConnection.insertNewAddress(str, KindOfService.getWithinReach(), KindOfService.CONFIG_FILE);
			}
		}
		DBConnection.synchronizeTablesForConfig();
		System.out.println("T_SERVICE: finished populating soa description based on the config file");
        //trace the evolution of the SOA
        DBConnection.traceSoaEvolution(scenario++);
	}
	
	/**
	 * update the database with the wsdl addresses extracted from a bpel file, 
	 * or refresh the "timing" and the ordered_by of the address that was last gathered
	 * (it updates the timing -last_gathered- only if the address is a bpel engine).
	 * @param array of addresses
	 * @param ip of BPEL
	 */
	public void populationFromBpel(String str[], String bpelAddress, String serviceName){
		for(int i=0; i<str.length; i++){
			str[i]=Utils.replaceHost(str[i], bpelAddress);
            System.out.println("T_SERVICE: storing address from testing bpel "+ str[i]);
			//if there already is the address in the database, simply updates the last time the address was gathered
			if(DBConnection.searchAddressinDb(str[i])==true){
				DBConnection.updateOriginatedBy(str[i], KindOfService.BPEL_JAR, KindOfService.getWithinReach(), bpelAddress, serviceName);
				if(KindOfService.discoverIdentity(str[i]).equals(KindOfService.BPEL_SERVICE)){
					DBConnection.updateLastModified(str[i], System.currentTimeMillis());
                }
			}else{
				//insert the value
				DBConnection.insertNewAddress(str[i], KindOfService.discoverIdentity(str[i]), KindOfService.BPEL_JAR, bpelAddress, serviceName);
			}
		}
	}

    /**
     * 
     * @param listofServices separati da "," comprende anche ?wsdl
     * @param nameOfControlledService indirizzo e nome del controlled service coinvolto
     * @param ip del controller
     */
    public void populationFromControlled(String listofServices, String nameOfControlledService, String ipController, String serviceName){
        nameOfControlledService=Utils.removeSpaces(nameOfControlledService);
        //remove ?wsdl
        nameOfControlledService=nameOfControlledService.substring(0, nameOfControlledService.indexOf("?"));
        //aggiorna quando e' stato modificato il controlled
        System.out.println("T_SERVICE: storing controller address "+ nameOfControlledService);
        DBConnection.updateLastModified(nameOfControlledService, System.currentTimeMillis());

        //inserisce gli indirizzi contenuti in listofServices
			//if there already is the address in the database, simply updates the last time the address was gathered
        System.out.println("T_SERVICE: listofServices "+ listofServices);

        if(listofServices!=null){
            listofServices=Utils.removeSpaces(listofServices);
            String address=null;
            int index=-1;
            while(true){
                address=null;
                index=-1;

                index=listofServices.indexOf(",");
                if(index== -1){
                        address=listofServices;
                        address=address.substring(0, address.indexOf("?"));
                }else{
                        address=listofServices.substring(0, index);
                        address=address.substring(0, address.indexOf("?"));
                }
               //l'indirizzo non e' il controlled stesso, ma c'e' gia' nel database
                if(DBConnection.searchAddressinDb(address)==true && address.equalsIgnoreCase(nameOfControlledService)==false){
                    System.out.println("T_SERVICE: updating address "+ address);
                    DBConnection.updateOriginatedBy(address, KindOfService.CONTROLLED, KindOfService.getWithinReach(), ipController, serviceName);
                //l'indirizzo non e' nel database, per cui lo si aggiunge
                }else if(DBConnection.searchAddressinDb(address)==false) {
                    //insert the value
                    System.out.println("T_SERVICE: inserting value "+ address);
                    DBConnection.insertNewAddress(address, KindOfService.discoverIdentity(address),
                            KindOfService.CONTROLLED, ipController, serviceName);
                }
                //tutti gli indirizzi sono stati inseriti
                if(index==-1){
                    return;
                }
                try{
                    listofServices=listofServices.substring(index+1);
                }catch(Exception e){
                    return;
                }
            }
        }
    }

    /**
	 * get the WSDL files given a set of paths
	 */
	public void getWSDLFiles(String str[]){
		for (int i=0; i< str.length; i++){
			Layers.layerApp.askWsdlFile(str[i], Layers.engine.addressBpelModified);
		}
	}

	/**
	 * @return true if config file knownServices.properties was modified since value lastModifiedKnownServices
	 */
	public boolean isConfigFileModified(){
		return KindOfService.isModified();
	}
	
	/**
	 * @returns a copy of the Soa Description
	 */
	public ResultSet getCopyOfServices(){
		return DBConnection.returnSoaDescription();
	}
}
