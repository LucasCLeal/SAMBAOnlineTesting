/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sambamonitor;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Lucas LeaL - revised version of the file BpelVersionAnalyzer from the BPELEngineWS
 */
public class Monitor implements Runnable {

    public ArrayList<Long> lastModified;
    public Boolean BPEl_JAR_LOADED;
    public ArrayList<File> listOfAssemblies;

    /**
     * constructor
     */
    public Monitor() {
        BPEl_JAR_LOADED = false;
        listOfAssemblies = null;
        lastModified = null;
    }

    /**
     * Identify modification of the BPEL jar file
     * @return true if bpel jar file is modified
     */
    public boolean bpelJarIsModified(File jarFile, Integer jarIndex) {
        try {

            //pegando nova referencia para o arquivo no sistema
            File actualFile = new File(jarFile.getPath());

            long timeStamp = actualFile.lastModified();
            //System.out.println("[CompositionMonitor] SA: " + jarFile.getName() + " System Reg: " + Long.toHexString(lastModified.get(jarIndex)));
            //System.out.println("[CompositionMonitor] SA: " + jarFile.getName() + " last update: " + Long.toHexString(timeStamp));
            if (lastModified.get(jarIndex) != timeStamp) {
                lastModified.set(jarIndex, timeStamp);
                return true;
            }
        } catch (Exception e) {
            System.out.println("[CompositionMonitor] exception: " + e);
            return false;
        }
        return false;
    }

    /*
    Executes a search for BPEL JAR FILES on the Server
    Returns a list of JAR FILES.

     */
    private ArrayList<File> getServerBPELJarFiles() {

        //Listing the BPEL Services deployed
        String srvAssembliesPath = "/home/andrea/SUNWappserver/domains/domain1/jbi/service-assemblies/";
        File srvAssemblies = new File(srvAssembliesPath);

        //criando lista vazia de strings
        ArrayList<File> listOfBPEL = new ArrayList<File>();

        //Obtendo lista de arquivos, filtrando para remover referencias a pastas.
        File[] srvAssembliesFiles = srvAssemblies.listFiles();
        //Adicionando nomes dos diretorios a lista

        System.out.println("[CompositionMonitor] List of BPEL service assemblies found:");
        for (File file : srvAssembliesFiles) {
            if (file.isDirectory()) {
                String bpelName = file.getName();
                System.out.println(file.getName());

                //generating path to BPEL JAR File
                String jar = srvAssembliesPath + bpelName + "/" + bpelName + "-" + bpelName + "/" + bpelName + ".jar";

                //verifying the existence of file and adding it to list
                File jarFile = new File(jar);
                if (jarFile.isFile()) {
                    listOfBPEL.add(jarFile);
                } else {
                    System.out.println("[CompositionMonitor] unable to read  file at path: " + jar);
                }
            }
        }
        return listOfBPEL;
    }

    //Call the modelGen WS
    private void requestModelUpdate(String compostionName) {

        try { // Call Web Service Operation
            com.unicamp.lucas.modelupdater.ModelUpdater_Service service = new com.unicamp.lucas.modelupdater.ModelUpdater_Service();
            com.unicamp.lucas.modelupdater.ModelUpdater port = service.getModelUpdaterPort();
            // TODO initialize WS operation arguments here
            java.lang.String orchestrationName = compostionName;
            port.updateModel(orchestrationName);
        } catch (Exception ex) {
            System.out.println("[CompositionMonitor]: " + ex);
        }

    }

    //Call the MB online Test case generation WS
    //used to test SAMBA framework
    private void requestTestCaseGen(String compositionName) {


        try { // Call Web Service Operation
            testingservice.networkcommunication.TestingWSService service = new testingservice.networkcommunication.TestingWSService();
            testingservice.networkcommunication.TestingWS port = service.getTestingWSPort();
            // TODO initialize WS operation arguments here
            java.lang.String bpelName = compositionName;
            port.startOnlineModelBasedTestforBpel(bpelName);
        } catch (Exception ex) {
            // TODO handle custom exceptions here
            System.out.println("[CompositionMonitor]: " + ex);
        }

    }

    // run method: the ENGINE ORIGINAL!!!
    public void run() {
        while (true) {

            if (BPEl_JAR_LOADED) {
                //Jar files already loaded
                //check for updates in all jar files listed
                for (int i = 0; i < listOfAssemblies.size(); i++) {
                    if (bpelJarIsModified(listOfAssemblies.get(i), i)) {

                        //trigger model update for BPEL orchestration
                        System.out.println("[CompositionMonitor]: " + listOfAssemblies.get(i).getName() + " updated!");
                        System.out.println("[CompositionMonitor]: Starting model Generation");
                        requestModelUpdate(listOfAssemblies.get(i).getName());

                        //System.out.println("[CompositionMonitor]: Start Model-Based Online test generation");
                        //requestTestCaseGen(listOfAssemblies.get(i).getName());
                        //System.out.println("[CompositionMonitor]: Test Finished");

                    }
                }
            } else {
                try {
                    //jar files not loaded
                    //retrieving files
                    listOfAssemblies = getServerBPELJarFiles();
                    //starting the listarray
                    lastModified = new ArrayList<Long>();

                    //seting timestemps for each service Assemblie
                    for (File file : listOfAssemblies) {
                        System.out.println("[CompositionMonitor] adding BPEL Service: " + file.getName());
                        lastModified.add(file.lastModified());
                    }
                    BPEl_JAR_LOADED = true;
                } catch (Exception e) {
                    System.out.println("[CompositionMonitor] exception: " + e);
                }
            }

            //sleep 30 seconds at the end of each iteration
            try {
                Thread.sleep(30000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
    /**
     * run method: the ENGINE UPDATED TO MODELS FOR ALL BPEL FILES!!!

    public void run() {
    while (true) {

    if (BPEl_JAR_LOADED) {
    //Jar files already loaded
    //check for updates in all jar files listed
    for (int i = 0; i < listOfAssemblies.size(); i++) {
    //trigger model update for BPEL process
    System.out.println("[CompositionMonitor]: Starting model Generation");
    requestModelUpdate(listOfAssemblies.get(i).getName());
    System.out.println("[CompositionMonitor]: Model Gen finished");

    }
    } else {
    try {
    //jar files not loaded
    //retrieving files
    listOfAssemblies = getServerBPELJarFiles();
    //seting timestemps for each service Assemblie
    for (File file : listOfAssemblies) {
    System.out.println("[CompositionMonitor] adding BPEL Service: " + file.getName());
    }
    BPEl_JAR_LOADED = true;
    } catch (Exception e) {
    System.out.println("[CompositionMonitor] exception: " + e);
    }
    }

    //sleep 30 seconds at the end of each iteration
    try {
    Thread.sleep(30000);
    } catch (InterruptedException e) {
    e.printStackTrace();
    }

    }
    }
     */
}

