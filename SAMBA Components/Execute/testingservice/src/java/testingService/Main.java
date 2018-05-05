package testingService;

import testingService.soaDescription.DBConnection;
import testingService.config.*;

/**
 * Main class
 */
public class Main {

    public static boolean isStarted = false;

    public static void startTestingEngine() {
        if (isStarted == true) {
            System.exit(0);
        }
        isStarted = true;

        //INITIALIZE VARIOUS STAFF
        //get configuration parameters
        String confpath = "/home/andrea/NetBeansProjects/testingservice/config/config.properties";
        System.out.println("T_SERVICE: getting config parameters from: " + confpath);
        ConfigParameters.loadConfigFile(confpath);
        System.out.println("T_SERVICE: Config parameters loaded");

        //initialize the SOA description part (database)
        //first of all, drop old data
        DBConnection.connectToDatabaseMaster();
        DBConnection.cleanOldData();
        //now create database and start creating stuff
        DBConnection.connectToDatabase();
        System.out.println("T_SERVICE: Connection to database established");
        DBConnection.initializeDatabase();
        System.out.println("T_SERVICE: Database initialized");

        //start connection to BPEL service
        System.out.println("T_SERVICE: Starting layers...");
        new Layers().startLayers();
        System.out.println("T_SERVICE: testing service fully started...");

    }
}
