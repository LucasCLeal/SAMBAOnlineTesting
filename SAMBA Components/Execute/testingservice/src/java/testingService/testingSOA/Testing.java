package testingService.testingSOA;

import java.sql.ResultSet;
import java.sql.SQLException;

import testingService.soaDescription.DBConnection;

public class Testing {
	/**
	 * do not know, it is for stopping the thread
	 */
	volatile Thread blinker;

	/**
	 * True if the main thread is started, false otherwise.
	 * Initialized to false;
	 */
	public boolean isStarted=false;
	
	/**
	 * @return true if the thread is already started, false otherwise
	 */
	public boolean isStarted(){
		return isStarted;
	}
	/**
	 * variabile di appoggio for internal usage
	 * says: there is nothing to test.
	 */
	String NOTHING_TO_TEST="nothing to test";
	/**
	 * Indicates address not reachable
	 */
	String ADDRESS_NOT_REACHABLE="Address not reachable";
	int ADDRESS_NOT_REACHABLE_int=0;
	
	/**
	 * Indicates error during execution
	 */
	String ERROR = "A fatal problem has ocurred during test execution";
	int ERROR_int=1;

	/**
	 * Indicates test finished
	 */
	String FINISHED = "Finished";
	int FINISHED_int=2;
	
	/**
	 * Retry policy: when the test will be re-executed, considering its current outcome.
	 * (this parameter does not consider the "quality" of the test outcome)
	 * Does not consider the kind of service in use.
	 * -if retry_policy[ADDRESS_NOT_REACHABLE_int] ---> ADDRESS_NOT_REACHABLE, retry after (at least) 100 seconds
	 * -if retry_policy[ERROR_int] ---> ERROR doing the test, retry after (at least) 100 seconds
	 * -if retry_policy[FINISHED_int] ---> FINISHED test, not retry (not because of retry policy, 
	 * maybe it can be re-tested because it is a within_service)--> (-1 seconds)
	 *
	 * DEFAULT VALUE={100000,100000,-1};
	 * @see EXPIRE_WITHIN_REACH
	 */
	long retry_policy[]={100000,100000,-1};

	/**
	 * it is the expire time of within_reach services: how much often you need to re-test them.
	 * When we do a test, for within-reach service we put the retry_policy value or the expire_within_reach
	 * value, whichever come first.
	 * DEFAULT VALUE=600000
	 * @see retry_policy
	 */
	long EXPIRE_WITHIN_REACH=600000;
	
	/**
	 * Kill the Thread
	 * This method blocks until the thread is not killed
	 * Then it sets isStarted to false.
	 */
	public synchronized void kill(){
	    blinker = null;
	    try {
			wait();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	public synchronized void notifyDead(){
		isStarted=false;
		notifyAll();
	}

	
	/**
	 * Identify the next service to test
	 * -- get the table of the services
	 *   --if Last Test = 0 we test
	 *   --if Last Test < Time Gathered we test
	 *   --if re-try has expired, we test
	 *   
	 * otherwise we look at the within reach service, and we test the one which 
	 * has the "oldest" last_tested value (i.e., we do a sort of periodic test on the 
	 * within_reach services)
	 * 
	 * @param ResultSet that represents the table soaDescription
	 * @return the address of the service
	 */
	public String findWhoToTestNext(ResultSet rs){
		//for the whole result set
		String address=NOTHING_TO_TEST;
		//while there are elements, see if someone is completely untested (no attempts to test them)
		//e.g., a new service or a modified service
		address=findUntestedService(rs);
		if(address.equals(NOTHING_TO_TEST)==false){
			return address;
		}
		//There are no untested elements. Then we look for timeouts of retry_policy or EXPIRE_WITHIN_REACH
		//this returns the (one) WITHIN_REACH service that has the oldest last_tested value
		address = lookInExpiredOrWhithinReach(rs);
		if(address.equals(NOTHING_TO_TEST)==false){
			return address;
		}
		return NOTHING_TO_TEST;
	}
	
	/**
	 * Finds services in the table that have never been tested, nor attempts to test them were performed.
	 * @param Result set which contains the soaDescription table.
	 * The ResultSet points at the beginning of the RS (not at the first row of the table). 
	 * @return an address of a untested service or string NOTHING_TO_TEST
	 * @see NOTHING_TO_TEST
	 */
	public String findUntestedService(ResultSet res){
		String address;
		long last_tested;
		long address_gathered;
		try{
			//get the beginning of res
			res.beforeFirst();
			while(res.next()){
				address= DBConnection.getAddress(res);
				last_tested= DBConnection.getLastTested(res);
				address_gathered=DBConnection.getAddressGathered(res);
				//if we have that Last Test< Last Modified,
				if(address_gathered > last_tested){
					//we select the address for the testing.
					return address;
				}
				address=NOTHING_TO_TEST;
				last_tested=-1;
				address_gathered=-1;
			}
		}catch(SQLException e){
			System.out.println("T_SERVICE: Error querying ResultSet for an untested service");
			e.printStackTrace();
		}
		return NOTHING_TO_TEST;
	}
	
	
	/**
	 * Finds services in the table soaDescription that 
	 * needs to be re-tested because of the EXPIRE_WITHIN_REACH parameter or the re-try parameter.
	 * @param Result set which contains the soaDescription table.
	 * The ResultSet points at the beginning of the RS (not at the first row of the table). 
	 * @return an address of a WITHIN_REACH service or string NOTHING_TO_TEST
	 * @see NOTHING_TO_TEST
	 */
	public String lookInExpiredOrWhithinReach(ResultSet res){
		String address=null;
		//get the beginning of ResultSet res
		address=DBConnection.getMostExpired();
		//TODO possible synch problems with database and changes
		if(address!=null){
			return address;
		}
		return NOTHING_TO_TEST;
	}

	/**
	 * Define which is the value for the re-try: i.e, the corresponding value of the retry_policy[] 
	 * @param result of the test
	 * @long the re-try value computed on the basis of the result of the test
	 * @see testingService.testingSOA.Testing.retry_policy
	 */
	public long retryValue(String result){
		if(result.equals(FINISHED)){
			return retry_policy[FINISHED_int];
		}else if(result.equals(ERROR)){
			return retry_policy[ERROR_int];			
		}else if(result.equals(ADDRESS_NOT_REACHABLE)){
			return retry_policy[ADDRESS_NOT_REACHABLE_int];
		}else{
			System.out.println("T_ENGINE: Invalid value in retry_policy: check method retryValue for result: "+result);
			return -1;
		}
	}	
}
