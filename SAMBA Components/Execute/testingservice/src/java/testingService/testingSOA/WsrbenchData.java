package testingService.testingSOA;

import pt.uc.dei.wsrbench.common.pojo.Fault;

/**
 * Contains means/handlers to wsrBench. 
 * E.g., retrieves the data to automatically define if a received message has a problem or not.
 * It relies on analysis made by WsrBench.
 * @see pt.uc.dei.wsrbench.common.pojo.Fault
 * @see pt.uc.dei.wsrbench.common.pojo.Fault.UNDEFINED
 * @see pt.uc.dei.wsrbench.common.pojo.Fault.PROBLEM
 * @see pt.uc.dei.wsrbench.common.pojo.Fault.NO_PROBLEM
 * @author Andrea
 *
 */
public class WsrbenchData {
	/**
	 * String that states there are no problem
	 * (it will be written in the db)
	 */
	public static String NO_PROBLEM="NO PROBLEM";
	
	/**
	 * String that states that we can not decide if there are problems or not
	 * (it will be written in the db)
	 */
	public static String UNDEFINED="CANNOT DEFINE";

	/**
	 * String that states there are problem detected
	 * (it will be written in the db)
	 */
	public static String PROBLEM="PROBLEM DETECTED";
	
	public static String  getStatusProblem(Fault f){
		int i=f.getProblem();
		switch(i){
		case 0:
			return UNDEFINED;
		case 1:
			return PROBLEM;
		case 2:
			return NO_PROBLEM;
		default: 
			System.out.println("T_SERVICE: Error defining message problems in getStatusProblem");
			return "ERROR IN GET_STATUS_PROBLEM";		
		}
	}

}
