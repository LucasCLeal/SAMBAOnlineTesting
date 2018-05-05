package testingService.soaDescription;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

import testingService.config.ConfigParameters;
import testingService.utils.Utils;

public class DBConnection {

	/**
	 * represent column wsdl_address (number of the column)
	 */
	public static int row_AddressNumber=1;
	/**
	 * represent column wsdl_address
	 */
	public static String row_AddressString="WSDL_ADDRESS";

	/**
	 * represent column kindOfService (number of the column)
	 */
	public static int row_KindOfServiceNumber=2;
	/**
	 * represent column kindOfService
	 */
	public static String row_KindOfServiceString="KIND_OF_SERVICE";

	/**
	 * represent column last_tested (number of the column)
	 */
	public static int row_LastTestedNumber=3;
	/**
	 * represent column last_tested
	 */
	public static String row_LastTestedString="LAST_TESTED";

	/**
	 * represent column address_gathered (number of the column)
	 */
	public static int row_AddressGatheredNumber=4;
	/**
	 * represent column address_gathered
	 */
	public static String row_AddressGatheredString="ADDRESS_GATHERED";
	
	/**
	 * represent column test_result (number of the column)
	 */
	public static int row_TestOutcomeNumber=5;
	/**
	 * represent column test_result (name of the column)
	 */
	public static String row_TestOutcomeString="TEST_RESULT";


	/**
	 * connection to the database
	 */
	static Connection connection; 

	/**
	 * connection to the database
	 */
	static Connection connectionMaster; 
	
	/**
	 * establish a connection to the database andrea
	 * (user name and password: andrea andrea
	 * (see config.properties file)
	 */
	public static synchronized void connectToDatabaseMaster(){
	  try {
	    Class.forName("org.postgresql.Driver");
	  } catch (ClassNotFoundException e) {
	    System.out.println("T_SERVICE: Where is your PostgreSQL JDBC Driver? Include in your library path!");
	    e.printStackTrace();
	    return;
	  }		 
	  try {
		  connectionMaster = DriverManager.getConnection("jdbc:postgresql://"+ ConfigParameters.databaseMaster, ConfigParameters.databaseUName, ConfigParameters.databasePassword);
	  } catch (SQLException e) {
	    e.printStackTrace();
	    connectionMaster=null;
	    System.exit(0);
	    return;
	  }
	}
	
	/**
	 * Drop database testingService and recreate it
	 */
	public static void cleanOldData(){
		Statement statement = null;
		try {
			statement = connectionMaster.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch(SQLException e){
			System.out.println("T_SERVICE: Problems creating the statement for postgresql");
			e.printStackTrace();
		}
		try{
			//drop (clean)
			statement.executeUpdate("DROP DATABASE testingService");
		}catch(SQLException e){
			System.out.println("T_SERVICE: Impossible to drop database testingService");
			e.printStackTrace();
		}		
		try{
			statement.executeUpdate("CREATE DATABASE testingService WITH OWNER "+ConfigParameters.databaseUName);
			connectionMaster.commit();
			connectionMaster.close();
		}catch(SQLException e){
			System.out.println("T_SERVICE: Impossible to create database testingService");
			e.printStackTrace();
		}		
	}
	
	
	/**
	 * establish a connection to the database andrea/testingService
	 * (user name and password: andrea andrea
	 * (see config.properties file)
	 */
	public static synchronized void connectToDatabase(){
	  try {
	    Class.forName("org.postgresql.Driver");
	  } catch (ClassNotFoundException e) {
	    System.out.println("T_SERVICE: Where is your PostgreSQL JDBC Driver? Include in your library path!");
	    e.printStackTrace();
	    return;
	  }		 
	  try {
		 connection = DriverManager.getConnection("jdbc:postgresql://"+ ConfigParameters.database, ConfigParameters.databaseUName, ConfigParameters.databasePassword);
	  } catch (SQLException e) {
	    System.out.println("T_SERVICE: Connection to Database PostGreSql Failed!");
	    e.printStackTrace();
	    connection=null;
	    System.exit(0);
	    return;
	  }
	}

	/**
	 * initialize the tables of the database
	 * 
	 * TABLE soaDescription
	 * COLUMN:
	 * WSDL_ADDRESS: the address of the wsdl file: primary key
	 * KIND_OF_SERVICE: the kind of service: BPEL_ENGINE, CONTROLLED, WITHIN_REACH
	 * LAST_TESTED: the last time it was tested (ms)
	 * ADDRESS_GATHERED: the last time this address was gathered (refreshed) (ms)
	 * TEST_RESULT: the result of the test (finished or not)
	 * RETRY: defines after how many ms the test will be retried
	 * 
	 * CREATE ALSO A TABLE for the delete of elements from the soa:
	 * -originated_by
	 */
	public static synchronized void initializeDatabase(){
		// create our java jdbc statement
		Statement statement = null;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch(SQLException e){
			System.out.println("T_SERVICE: Problems creating the statement for postgresql");
			e.printStackTrace();
		}
/*		try{
			//drop (clean)
			statement.executeUpdate("DROP DATABASE testingService");
			statement.executeUpdate("DROP TABLE soaDescription CASCADE");
		}catch(SQLException e){
			System.out.println("T_SERVICE: There is no table soaDescription to drop in postgresql");	
		}try{
				//drop (clean)
			statement.executeUpdate("DROP TABLE originated_by CASCADE");
		}catch(SQLException e){
			System.out.println("T_SERVICE: There is no table originated_by to drop in postgresql");	
		}
*/		try{
			//re-create table soaDescription
			statement.executeUpdate("CREATE TABLE soaDescription ("+ row_AddressString+ " varchar(120) " +
					"  PRIMARY KEY, "+row_KindOfServiceString+" varchar(20) NOT NULL, " +
					row_LastTestedString+" bigint DEFAULT null, "+ row_AddressGatheredString+
					" bigint DEFAULT null, "+row_TestOutcomeString+" varchar(120), retry integer DEFAULT -1);");

			//re-create table originated_by
			statement.executeUpdate("CREATE TABLE originated_by ("+ row_AddressString+ " varchar(120) REFERENCES soaDescription("+row_AddressString+") ON DELETE CASCADE, originated_by varchar(120), kind_of_service varchar(120), originated_ID varchar(120), originated_wsdl varchar(120));");

			statement.executeUpdate("CREATE TABLE SOAEvolution (id serial primary key, " +
                    " scenario integer DEFAULT 0, wsdl_address varchar(120), kind_of_service varchar(20));");
         } catch (SQLException e) {
			System.out.println("T_SERVICE: Problems initializing database");
			e.printStackTrace();
		}
	}

    /**
	 * search an Address in the table soaDescription 
	 * @param the address
	 * @return true if the address exists
	 */
	public static synchronized boolean searchAddressinDb(String str){
		Statement stmt;
		ResultSet rs;
		try{
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			rs= stmt.executeQuery("SELECT * FROM soaDescription WHERE WSDL_ADDRESS = '"+str+"'");
			if(rs.next())
			{
				return true;
			}

		}catch(SQLException e){
			System.out.println("T_SERVICE: Error reading the table soaDescription in method searchAddressinDb for address "+str);
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * insert a new row in table soaDescription
	 * @param the address
	 * @param the kind of service (controlled, bpel engine, within reach)
	 * @param the way that allows to detect the current value (BPEL_ENGINE, or address_of_controlled, or CONFIG_FILE) 
	 */
	public static synchronized void insertNewAddress(String str, String kindOfService, String originated){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("INSERT INTO soaDescription VALUES ('"+str+
					"', '"+kindOfService+"', 0,"+new Date().getTime()+")");
			stmt.executeUpdate("INSERT INTO originated_by VALUES ('"+str+"', '"+originated+"', '"+ kindOfService +"', '"+ null+"')");
		} catch (SQLException e) {
			System.out.println("T_SERVICE: Error inserting a row the table soaDescription in method insertNewAddress");
			e.printStackTrace();
		}
	}
	
	/**
	 * insert a new row in table soaDescription
	 * @param the address
	 * @param the kind of service (controlled, bpel engine, within reach)
	 * @param the way that allows to detect the current value (BPEL_ENGINE, or address_of_controlled, or CONFIG_FILE)
	 */
	public static synchronized void insertNewAddress(String str, String kindOfService, String originated, String serviceName){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("INSERT INTO soaDescription VALUES ('"+str+
					"', '"+kindOfService+"', 0,"+new Date().getTime()+")");
			stmt.executeUpdate("INSERT INTO originated_by VALUES ('"+str+"', '"+originated+"', '"+ kindOfService +"', '"+ null+"')");
		} catch (SQLException e) {
			System.out.println("T_SERVICE: Error inserting a row the table soaDescription in method insertNewAddress");
			e.printStackTrace();
		}
	}



    /**
	 * insert a new row in table soaDescription
	 * @param the address
	 * @param the kind of service (controlled, bpel engine, within reach)
	 * @param the way that allows to detect the current value (BPEL_ENGINE, or address_of_controlled, or CONFIG_FILE)
	 */
	public static synchronized void insertNewAddress(String str, String kindOfService, String originated, String ipCommittente, String serviceName){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("INSERT INTO soaDescription VALUES ('"+str+
					"', '"+kindOfService+"', 0,"+new Date().getTime()+")");
			stmt.executeUpdate("INSERT INTO originated_by VALUES ('"+str+"', '"+originated+"', '"+ kindOfService +"', '"+ ipCommittente +"', '"+serviceName+"' )");
		} catch (SQLException e) {
			System.out.println("T_SERVICE: Error inserting a row the table soaDescription in method insertNewAddress");
			e.printStackTrace();
		}
	}

    /**
	 * update column ADDRESS_GATHERED for a specific service and table originated_by
	 * @param the service address
	 * @param the kind of originated_by
	 */
/*	public static synchronized void updateAddress(String str, String originated){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet srs = stmt.executeQuery("SELECT * FROM soaDescription WHERE "+row_AddressString +" = '"+str+"'");
			srs.next();
			srs.updateLong(row_AddressGatheredString, new Date().getTime());
			srs.updateRow();
			stmt.executeUpdate("INSERT INTO originated_by VALUES ('"+str+"', '"+originated+"')");
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error updating the "+ row_AddressGatheredString+" column for service "+str);
			System.out.println("Or the table originated_by with "+originated);
			e.printStackTrace();	
		}
	}
*/

	/**
	 * update column ADDRESS_GATHERED, KIND_OF_SERVICE for a specific service 
	 * @param the service address
	 * @param the kind of originated_by
	 */
	public static synchronized void updateAddress(String str, String kindOfService){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet srs = stmt.executeQuery("SELECT * FROM soaDescription WHERE "+row_AddressString +" = '"+str+"'");
			srs.next();
			srs.updateLong(row_AddressGatheredString, new Date().getTime());
			srs.updateRow();
			srs.updateString(row_KindOfServiceString, kindOfService);
			srs.updateRow();
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error updating the "+ row_AddressGatheredString+" column for service "+str);
			e.printStackTrace();
		}
	}

	/**
	 * update table originated_by for a specific service 
	 * @param the service address
	 * @param the kind of originated_by
	 * @param the kind of service
	 */
	public static synchronized void updateOriginatedBy(String str, String originated, String kindOfService){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("INSERT INTO originated_by VALUES ('"+str+"', '"+originated+"', '"+ kindOfService +"', '"+ null +"')");
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error updating table originated_by for address "+str+" and value originated = "+originated);
			e.printStackTrace();
		}
	}

	/**
	 * update table originated_by for a specific service
	 * @param the service address
	 * @param the kind of originated_by
	 * @param the kind of service
	 * @param ip del servizio che permette di fare la scoperta
	 * @param nome completo del servizio che permette di fare la scoperta
	 */
	public static synchronized void updateOriginatedBy(String str, String originated, String kindOfService, String ipsender, String servicesender){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("INSERT INTO originated_by VALUES ('"+str+"', '"+originated+"', '"+ kindOfService +"', '"+ ipsender+"',  '"+ servicesender+"')");
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error updating table originated_by for address "+str+" and value originated = "+originated);
			e.printStackTrace();
		}
	}
	
	/**
	 * Return the current description of the soa
	 * @return the table soaDescription, in an object of type ResultSet
	 */
	public static synchronized ResultSet returnSoaDescription(){
		Statement stmt;
		ResultSet srs=null;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			srs = stmt.executeQuery("SELECT * FROM soaDescription");
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error returning the current description of the soa;");
			e.printStackTrace();	
			return srs;
		}
		return srs;		
	}

	/**
	 * Return a Result Set of the WITHIN_REACH services that has the oldest testing parameter
	 * The result set points to the first row.
	 * The queries are two:
	 *
	 * select min(last_tested) from soaDescription;
	 * select * from soaDescription where last_tested=1;
	 * 
	 * @return a row of the table soaDescription, in an object of type ResultSet
	 */
	public static synchronized ResultSet getLastTestedWithinReach(){
		Statement stmt;
		ResultSet srs=null;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			srs = stmt.executeQuery("SELECT * FROM soaDescription WHERE "+row_LastTestedString+"=(SELECT MIN("+row_LastTestedString+") from soaDescription");
			srs.next();
			return srs;
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error executing the query: SELECT  min("+row_LastTestedString+") from soaDescription");
			e.printStackTrace();	
			return null;
		}
	}
	
	/**
	 * updates column LAST_TESTED for a specific address, with a new (fresh) timestamp
	 * The query is:
	 * UPDATE soaDescription SET last_tested = timestamp WHERE wsdl_address='address';
	 * @param wsdl_address of the tested service
	 * @param timestamp to store in last_tested column
	 * @param test result
	 */
	public static synchronized void updateLastTest(String address, long timestamp, String result, long retryValue){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("UPDATE soaDescription SET "+ row_LastTestedString +" = "+timestamp+", "+ row_TestOutcomeString+" = '"+ result+"', retry = "+retryValue
					+" WHERE "+row_AddressString+" = '"+ address+"'");
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error executing query in updateLastTest: UPDATE soaDescription SET "+ row_LastTestedString +" = "+timestamp+", "+ row_TestOutcomeString+" = '"+ result+"', retry = "+retryValue
					+" WHERE "+row_AddressString+" = '"+ address+"'");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param Result set in use, that MUST already point to the required row
	 * @param column
	 * @return the value that is in that column
	 */
	private static String getStringValueOfRow(ResultSet rs, int column){
		try {
			return rs.getString(column);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
	}

	/**
	 * @param Result set in use, that MUST already point to the required row
	 * @param column
	 * @return the value that is in that column
	 */
	private static String getStringValueOfRow(ResultSet rs, String column){
		try {
			return rs.getString(column);
		} catch (SQLException e) {
			e.printStackTrace();
			return "Error";
		}
	}
	
	/**
	 * @param Result set in use, that MUST already point to the required row
	 * @param column
	 * @return the value that is in that column
	 */
	static int getIntValueOfRow(ResultSet rs, int column){
		try {
			return rs.getInt(column);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * @param Result set in use, that MUST already point to the required row
	 * @param column
	 * @return the value that is in that column
	 */
	static int getIntValueOfRow(ResultSet rs, String column){
		try {
			return rs.getInt(column);
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * @param Result set in use, that MUST already point to the required row
	 * @param column
	 * @return the value that is in that column
	 */
	private static synchronized long getLongValueOfRow(ResultSet rs, int column){
		try {
			return rs.getLong(column);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	/**
	 * @param Result set in use, that MUST already point to the required row
	 * @param column
	 * @return the value that is in that column
	 */
	static long getLongValueOfRow(ResultSet rs, String column){
		try {
			return rs.getLong(column);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}	
	
	/**
	 * @param Result set in use, that MUST already point to the required row
	 * @return the ADDRESS (column wsdl_address)
	 */
	public static synchronized String getAddress(ResultSet rs){
		return getStringValueOfRow(rs, row_AddressNumber);
	}
	
	/**
	 * @param Result set in use, that MUST already point to the required row
	 * @return the time that service was last tested (last_tested)
	 */
	public static synchronized long getLastTested(ResultSet rs){
		return getLongValueOfRow(rs, row_LastTestedNumber);	
	}
	
	/**
	 * @param Result set in use, that MUST already point to the required row
	 * @return the time that service was last gathered(address_gathered)
	 */
	public static synchronized long getAddressGathered(ResultSet rs){
		return getLongValueOfRow(rs, row_AddressGatheredNumber);			
	}

	/**
	 * In table originated_by, cancels those rows with value keyWork in column originated_by:
	 * DELETE FROM originated_by WHERE originated_by= 'keyWord'
	 * @param value to search in column originated_by to understand who to remove
	 */
	public static synchronized void cancelInOriginatedByForConfig(String keyWord){
		Statement stmt, secondo;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			secondo= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("DROP TABLE IF EXISTS configuration;");
			stmt.executeUpdate("CREATE TABLE configuration (address varchar(120), kindofService varchar(120));");
            ResultSet prova =null;
			prova=stmt.executeQuery("SELECT wsdl_address, kind_of_service FROM originated_by WHERE originated_by.originated_by= '"+keyWord+"' AND originated_by.originated_ID='"+null+"' ");
            while(prova.next()){

                secondo.executeUpdate("INSERT INTO configuration VALUES ('"+
                        prova.getString("wsdl_address")+"', '"+ prova.getString("kind_of_service")+"')");
            }
			stmt.executeUpdate("DELETE FROM originated_by WHERE originated_by.originated_by= '"+keyWord+"' AND originated_by.originated_ID= '"+null+"' ");
        }catch(SQLException e){
			e.printStackTrace();
		}
	}
	
	/**
	 * In table originated_by, cancels those rows with value keyWork in column originated_by:
	 * DELETE FROM originated_by WHERE originated_by= 'keyWord'
	 * @param value to search in column originated_by to understand who to remove
	 */
	public static synchronized void cancelInOriginatedBy(String keyWord, String ipCommittente){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("DELETE FROM originated_by WHERE originated_by.originated_by= '"+keyWord+"' AND originated_by.originated_ID= '"+ipCommittente+"' ");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

    /**
	 * Makes wsdl_address in soaDescription and originated_by having the same values: cancel unused stuff from
	 * originated_by. Also now updates the parameter kind_of_service.
	 * delete from soaDescription WHERE NOT EXISTS (SELECT wsdl_address from originated_by where originated_by.wsdl_address=soaDescription.wsdl_address);
	 */
	public static synchronized void synchronizeTables(){
		Statement stmt, prova;
		ResultSet r_originatedBy=null;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("DELETE FROM soaDescription WHERE NOT EXISTS (SELECT wsdl_address from originated_by where originated_by.wsdl_address=soaDescription.wsdl_address)");
			prova= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//update the within reach service
			r_originatedBy= stmt.executeQuery("SELECT "+row_AddressString+", kind_of_service from originated_by where kind_of_service ='"+KindOfService.getWithinReach()+"'");
			while(r_originatedBy.next()){
				prova.executeUpdate("UPDATE soadescription SET kind_of_service =  '"+r_originatedBy.getString("kind_of_service")+"' WHERE " +
						" wsdl_address = '"+r_originatedBy.getString("wsdl_address")+"'");
			}
			//update the controlled service
			r_originatedBy=null;
			r_originatedBy= stmt.executeQuery("SELECT "+row_AddressString+", kind_of_service from originated_by where kind_of_service ='"+KindOfService.getControlled()+"'");
			while(r_originatedBy.next()){				
				prova.executeUpdate("UPDATE soadescription SET kind_of_service =  '"+r_originatedBy.getString("kind_of_service")+"' WHERE " +
						"wsdl_address = '"+r_originatedBy.getString("wsdl_address")+"'");
			}

			//update the bpel_service
			r_originatedBy=null;
			r_originatedBy= stmt.executeQuery("SELECT "+row_AddressString+", kind_of_service from originated_by where kind_of_service ='"+KindOfService.getBpelService()+"'");
			while(r_originatedBy.next()){
				prova.executeUpdate("UPDATE soadescription SET kind_of_service =  '"+r_originatedBy.getString("kind_of_service")+"' WHERE " +
						"wsdl_address = '"+r_originatedBy.getString("wsdl_address")+"'");

			}

		}catch(SQLException e){
			System.out.println("T_SERVICE: Problem in updating soa description.");
			e.printStackTrace();
		}
	}
	
	public static synchronized void synchronizeTables(String ipBpel){
		Statement stmt, prova;
		ResultSet r_originatedBy=null;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("DELETE FROM soaDescription WHERE NOT EXISTS (SELECT wsdl_address from originated_by where originated_by.wsdl_address=soaDescription.wsdl_address)");
			prova= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//update the within reach service
			r_originatedBy= stmt.executeQuery("SELECT "+row_AddressString+", kind_of_service from originated_by where kind_of_service ='"+KindOfService.getWithinReach()+"'");
			while(r_originatedBy.next()){
				prova.executeUpdate("UPDATE soadescription SET kind_of_service =  '"+r_originatedBy.getString("kind_of_service")+"' WHERE " +
						" wsdl_address = '"+r_originatedBy.getString("wsdl_address")+"'");
			}
			//update the controlled service
			r_originatedBy=null;
			r_originatedBy= stmt.executeQuery("SELECT "+row_AddressString+", kind_of_service from originated_by where kind_of_service ='"+KindOfService.getControlled()+"'");
			while(r_originatedBy.next()){
				prova.executeUpdate("UPDATE soadescription SET kind_of_service =  '"+r_originatedBy.getString("kind_of_service")+"' WHERE " +
						"wsdl_address = '"+r_originatedBy.getString("wsdl_address")+"'");
			}

			//update the bpel_service
			r_originatedBy=null;
			r_originatedBy= stmt.executeQuery("SELECT "+row_AddressString+", kind_of_service from originated_by where kind_of_service ='"+KindOfService.getBpelService()+"'");
			while(r_originatedBy.next()){
				prova.executeUpdate("UPDATE soadescription SET kind_of_service =  '"+r_originatedBy.getString("kind_of_service")+"' WHERE " +
						"wsdl_address = '"+r_originatedBy.getString("wsdl_address")+"'");

			}

		}catch(SQLException e){
			System.out.println("T_SERVICE: Problem in updating soa description.");
			e.printStackTrace();
		}
	}

    /**
     * Synchronize table for config file.
     * For example, if you delete a bpel, it also delete the values that were depending on that bpel.
     * see synchronizeTables()
     */
	public static synchronized void synchronizeTablesForConfig(){
		Statement configurationtable, stmt, prova, prova1;
		ResultSet r_originatedBy=null;
		try {
			prova1= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);

            //adesso uso la configuration table per fare tornare tutto (spero)
            configurationtable=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            r_originatedBy=configurationtable.executeQuery("SELECT address, kindofservice from " +
                    "configuration where NOT EXISTS (SELECT wsdl_address from originated_by " +
                    "where originated_by.originated_by='CONFIG_FILE' and originated_by.wsdl_address " +
                    "= configuration.address AND (originated_by.kind_of_service='CONTROLLED' OR originated_by.kind_of_service='BPEL_SERVICE')) ");
            while(r_originatedBy.next()){
                if(r_originatedBy.getString("kindofservice").equalsIgnoreCase("WITHIN_REACH")){
                }else if(r_originatedBy.getString("kindofservice").equalsIgnoreCase("CONTROLLED")){
                    String str=r_originatedBy.getString("address");
                    String ip=str.substring(7, str.indexOf(':', 9));
                    prova1.executeUpdate("DELETE FROM originated_by "+
                            "WHERE originated_ID='"+ip+"'AND originated_by.originated_by= 'CONTROLLED'");
                }else{
                    String str=r_originatedBy.getString("address");
                    String ip=str.substring(7, str.indexOf(':', 9));
                    prova1.executeUpdate("DELETE FROM originated_by "+
                            "WHERE originated_ID='"+ip+"'AND originated_by.originated_by= 'BPEL_JAR'");
                }
            }

            stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("DELETE FROM soaDescription WHERE NOT EXISTS (SELECT wsdl_address from originated_by where originated_by.wsdl_address=soaDescription.wsdl_address)");
			prova= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			//update the within reach service
			r_originatedBy= stmt.executeQuery("SELECT "+row_AddressString+", kind_of_service from originated_by where kind_of_service ='"+KindOfService.getWithinReach()+"'");
			while(r_originatedBy.next()){
				prova.executeUpdate("UPDATE soadescription SET kind_of_service =  '"+r_originatedBy.getString("kind_of_service")+"' WHERE " +
						" wsdl_address = '"+r_originatedBy.getString("wsdl_address")+"'");
			}
			//update the controlled service
			r_originatedBy=null;
			r_originatedBy= stmt.executeQuery("SELECT "+row_AddressString+", kind_of_service from originated_by where kind_of_service ='"+KindOfService.getControlled()+"'");
			while(r_originatedBy.next()){
				prova.executeUpdate("UPDATE soadescription SET kind_of_service =  '"+r_originatedBy.getString("kind_of_service")+"' WHERE " +
						"wsdl_address = '"+r_originatedBy.getString("wsdl_address")+"'");
			}

			//update the bpel_service
			r_originatedBy=null;
			r_originatedBy= stmt.executeQuery("SELECT "+row_AddressString+", kind_of_service from originated_by where kind_of_service ='"+KindOfService.getBpelService()+"'");
			while(r_originatedBy.next()){
				prova.executeUpdate("UPDATE soadescription SET kind_of_service =  '"+r_originatedBy.getString("kind_of_service")+"' WHERE " +
						"wsdl_address = '"+r_originatedBy.getString("wsdl_address")+"'");

			}

		}catch(SQLException e){
			System.out.println("T_SERVICE: Problem in updating soa description.");
			e.printStackTrace();
		}
	}

    /**
	 * @param address of the service
	 * @return the corresponding kind of service
	 */
	public static synchronized String getKindOfService(String s){
		Statement stmt;
		ResultSet srs=null;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			srs = stmt.executeQuery("SELECT "+row_KindOfServiceString+" FROM soaDescription WHERE "+row_AddressString+"= '"+s+"'");
			srs.next();
			return getStringValueOfRow(srs, row_KindOfServiceString);
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error executing the query: SELECT "+row_KindOfServiceString+" FROM soaDescription WHERE "+row_AddressString+"= '"+s+"'");
			e.printStackTrace();	
			return null;
		}
	}
	
	/**
	 * @return the address of the service, amongst those with a retry value,
	 * that is "most expired" (at the time of the invocation). 
	 * Returns the address or a null value if there is no target string that has
	 * expired.
	 * 
	 * The query is something like:
	 * select wsdl_address from soadescription where last_tested+retry < 22222222222222 AND (last_tested+retry)=(select min(last_tested+retry) from soadescription where retry <>-1) ;
	 */
	public static synchronized String getMostExpired(){
		Statement stmt;
		ResultSet srs=null;
		try {

			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			srs = stmt.executeQuery("SELECT "+row_AddressString+" FROM soaDescription WHERE "+System.currentTimeMillis()+" > "+row_LastTestedString +"+ retry AND ("+row_LastTestedString+"+retry) = (SELECT MIN("+row_LastTestedString+" + retry) FROM soaDescription WHERE retry <> -1) ");
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error executing the query: SELECT "+row_AddressString+" FROM soaDescription WHERE "+System.currentTimeMillis()+" > (SELECT MIN("+row_LastTestedString+" + retry) FROM soaDescription) AND retry <> -1;");
			e.printStackTrace();	
			return null;
		}
		try{
			if(srs.next()){
				return getAddress(srs);
			}
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error returning the address from result set in DBConnection.getMostExpired()");
			e.printStackTrace();
			return null;
		}	
		return null;		
	}

	/**
	 * create and populate the tables that contain results
	 * TABLE has name: address
	 * e.g., http://www.dataaccess.com/webservicesserver/numberconversion
	 * 
	 * COLUMNS:
	 * - wsdl_address
	 * - last_tested
	 * - operation_name 
	 * - parameter
	 * - fault_name 
	 * - request
	 * - response
	 * - problem
	 * 
	 * E.g.:
	 * create table address (wsdl_address varchar(120), last_tested bigint DEFAULT null, operation_name varchar(120), parameter varchar (120), fault_name varchar(120) 
	 * default null, request text, problem varchar(100));
	 * 
	 * @param address
	 * @param time in which the test was performed
	 */
	public static synchronized String createResultTable(String address, long last_tested){
		// create our java jdbc statement
		Statement statement = null;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch(SQLException e){
			System.out.println("T_SERVICE: Problems creating the statement for postgresql");
			e.printStackTrace();
		}
		//create table to store results
		try{
			//create table to store results
			statement.executeUpdate("CREATE TABLE \""+address+"\" ("+ row_AddressString+ " varchar(120), " +
					row_LastTestedString+" bigint DEFAULT null, operation_name text, parameter text," +
							" fault_name text, request text, response text, problem varchar(120))");
		}//EXCEPTION IF TABLE ALREADY EXISTS 
		catch (SQLException e) {
			System.out.println("T_SERVICE: Table \""+address+"\" already exists; new data will be appended to the table");
			//e.printStackTrace();
			//nothing to do: table already exists, then we have to simply add data to it
		}
		return address;
	}

	/**
	 * Insert the values in the result table "tableName"
	 * COLUMNS:
	 * - wsdl_address
	 * - last_tested
	 * - operation_name 
	 * - parameter
	 * - fault_name 
	 * - request
	 * - response
	 * - problem
	 * @param tableName the name of the table where to insert the results
	 */
	public synchronized static void addToResultTable(String tableName, String address, long lastTested, 
		String operationName, String parameter, String fault_name, String request, String response, String problem ){
		// create our java jdbc statement
		Statement statement = null;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch (SQLException e){
			System.out.println("T_SERVICE: problem creating statement in addToResultTable.");
		}
		try{	
			statement.executeUpdate("INSERT INTO \""+tableName+"\" VALUES (E'"+address+"', "+lastTested+", " +
					"E'"+operationName+"', E'"+parameter+"', E'"+
					fault_name+"', E'"+request+"', E'"+response+"', E'"
					+problem+"')"); 
		}catch(SQLException e){
			System.out.println("T_SERVICE: Problems populating table "+tableName);
			System.out.println("T_SERVICE: tablename = "+tableName);
			System.out.println("T_SERVICE: address = "+address);
			System.out.println("T_SERVICE: lastTested = "+lastTested);
			System.out.println("T_SERVICE: operationName = "+operationName);
			System.out.println("T_SERVICE: parameter = "+parameter);
			System.out.println("T_SERVICE: fault_name = "+fault_name);
			System.out.println("T_SERVICE: request = "+request );
			System.out.println("T_SERVICE: response = "+response);
			System.out.println("T_SERVICE: problem = "+problem);
			e.printStackTrace();
		}		
	}


	public synchronized static void addToResultTableTimeValue(String tableName, 
            String address, String operation, long minValue, long maxValue,
            long average){
		// create our java jdbc statement
		Statement statement = null;
		try {
			statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
		}catch (SQLException e){
			System.out.println("T_SERVICE: problem creating statement in addToResultTable.");
		}
		try{
			statement.executeUpdate("INSERT INTO \""+tableName+"\" VALUES (E'"+address+"', "+operation+", " +
					"E'"+minValue+"', E'"+maxValue+"', E'"+
					average+"')");
		}catch(SQLException e){
			e.printStackTrace();
		}
	}

    /**
	 * Stores in soadescription a new LastModified value for the service address "address"
	 * @param service address
	 * @param time
	 */
	public synchronized static void updateLastModified(String address, long time){
		Statement stmt;
		try {
			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
			stmt.executeUpdate("UPDATE soaDescription SET "+ row_AddressGatheredString +" = "+time+" WHERE "+row_AddressString+" = '"+ address+"'");
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error executing query in updateLastModified: UPDATE soaDescription SET "+ row_AddressGatheredString +" = "+time+", WHERE "+row_AddressString+" = '"+ address+"'");
			e.printStackTrace();
		}
	}

    /**
     * Trace the evolution of the SOA
     * statement.executeUpdate("CREATE TABLE SOAEvolution
     * (id serial primary key,
     * scenario integer,
     * wsdl_address varchar(120),
     * kind_of_service varchar(20),
     * connected_to varchar(120));
     */
    public synchronized static void traceSoaEvolution(int scenario){
		Statement stmt;
		ResultSet srs=null;
		try {

			stmt= connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            stmt.executeUpdate("INSERT INTO SOAEvolution (wsdl_address, kind_of_service) (SELECT wsdl_address, kind_of_service FROM soaDescription)");
			stmt.executeUpdate("UPDATE SOAEvolution SET scenario= "+scenario+" WHERE scenario = 0");
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error executing the query for soa tracing");
			e.printStackTrace();
		}
    }
}
