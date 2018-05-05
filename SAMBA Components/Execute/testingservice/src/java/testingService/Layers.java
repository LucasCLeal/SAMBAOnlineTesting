package testingService;

import testingService.networkCommunication.CommToOthers;
import testingService.testingSOA.TestingEngine;

/**
 * Handles (starts and stops) the layers: 
 * Application layer: BPEL Engine, 
 * Middle layer: CommToBpelEngines
 */
public class Layers {
	public static CommToOthers layerApp;
	public static TestingEngine engine;
	
	public void startLayers(){
		layerApp= new CommToOthers();
		
//		sock=new ConnectionBPEL();
//		sock.start();
		
		engine = new TestingEngine();
		engine.start();
	}
	
	
/*	public void closeLayers(){
		sock.close();
	}

	public void closeConnectionToTesting(){
		sock.close();
	}
*/
}
