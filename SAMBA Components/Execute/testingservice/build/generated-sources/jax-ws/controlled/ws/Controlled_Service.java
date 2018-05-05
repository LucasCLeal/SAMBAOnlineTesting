
package controlled.ws;

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
 * JAX-WS RI 2.1.3.3-hudson-757-SNAPSHOT
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "Controlled", targetNamespace = "http://ws.controlled/", wsdlLocation = "http://localhost:8080/Controlled/Controlled?wsdl")
public class Controlled_Service
    extends Service
{

    private final static URL CONTROLLED_WSDL_LOCATION;
    private final static Logger logger = Logger.getLogger(controlled.ws.Controlled_Service.class.getName());

    static {
        URL url = null;
        try {
            URL baseUrl;
            baseUrl = controlled.ws.Controlled_Service.class.getResource(".");
            url = new URL(baseUrl, "http://localhost:8080/Controlled/Controlled?wsdl");
        } catch (MalformedURLException e) {
            logger.warning("Failed to create URL for the wsdl Location: 'http://localhost:8080/Controlled/Controlled?wsdl', retrying as a local file");
            logger.warning(e.getMessage());
        }
        CONTROLLED_WSDL_LOCATION = url;
    }

    public Controlled_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public Controlled_Service() {
        super(CONTROLLED_WSDL_LOCATION, new QName("http://ws.controlled/", "Controlled"));
    }

    /**
     * 
     * @return
     *     returns Controlled
     */
    @WebEndpoint(name = "ControlledPort")
    public Controlled getControlledPort() {
        return super.getPort(new QName("http://ws.controlled/", "ControlledPort"), Controlled.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Controlled
     */
    @WebEndpoint(name = "ControlledPort")
    public Controlled getControlledPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://ws.controlled/", "ControlledPort"), Controlled.class, features);
    }

}