
package com.unicamp.lucas.modelupdater;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.4-b01-
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "ModelUpdater", targetNamespace = "http://modelupdater.lucas.unicamp.com/", wsdlLocation = "http://192.168.56.1:8080/ModelGen/ModelUpdater?wsdl")
public class ModelUpdater_Service
    extends Service
{

    private final static URL MODELUPDATER_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(com.unicamp.lucas.modelupdater.ModelUpdater_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = com.unicamp.lucas.modelupdater.ModelUpdater_Service.class.getResource(".");
            url = new URL(baseUrl, "http://192.168.56.1:8080/ModelGen/ModelUpdater?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://192.168.56.1:8080/ModelGen/ModelUpdater?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        MODELUPDATER_WSDL_LOCATION = url;
    }

    public ModelUpdater_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public ModelUpdater_Service() {
        super(MODELUPDATER_WSDL_LOCATION, new QName("http://modelupdater.lucas.unicamp.com/", "ModelUpdater"));
    }

    /**
     * 
     * @return
     *     returns ModelUpdater
     */
    @WebEndpoint(name = "ModelUpdaterPort")
    public ModelUpdater getModelUpdaterPort() {
        return super.getPort(new QName("http://modelupdater.lucas.unicamp.com/", "ModelUpdaterPort"), ModelUpdater.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ModelUpdater
     */
    @WebEndpoint(name = "ModelUpdaterPort")
    public ModelUpdater getModelUpdaterPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://modelupdater.lucas.unicamp.com/", "ModelUpdaterPort"), ModelUpdater.class, features);
    }

}
