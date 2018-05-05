/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package sambamonitor;

/**
 *
 * @author LucasCLeal
 * This Class is responsible to monitor the state of the Server and their BPEL Jars
 *
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //starting de compositionMonitor
        System.out.println("[SAMBAMonitor]: starting Composition Monitor");
        new Thread(new Monitor()).start();
        System.out.println("[SAMBAMonitor]: Composition Monitor fully started");
    }

}
