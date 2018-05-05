
package testingservice.networkcommunication;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TestingWSService", targetNamespace = "http://networkCommunication.testingService/", wsdlLocation = "http://192.168.56.5:8080/testingservice/TestingWSService?wsdl")
public class TestingWSService
    extends Service
{

    private final static URL TESTINGWSSERVICE_WSDL_LOCATION;
    private final static WebServiceException TESTINGWSSERVICE_EXCEPTION;
    private final static QName TESTINGWSSERVICE_QNAME = new QName("http://networkCommunication.testingService/", "TestingWSService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://192.168.56.5:8080/testingservice/TestingWSService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TESTINGWSSERVICE_WSDL_LOCATION = url;
        TESTINGWSSERVICE_EXCEPTION = e;
    }

    public TestingWSService() {
        super(__getWsdlLocation(), TESTINGWSSERVICE_QNAME);
    }

    public TestingWSService(WebServiceFeature... features) {
        super(__getWsdlLocation(), TESTINGWSSERVICE_QNAME, features);
    }

    public TestingWSService(URL wsdlLocation) {
        super(wsdlLocation, TESTINGWSSERVICE_QNAME);
    }

    public TestingWSService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TESTINGWSSERVICE_QNAME, features);
    }

    public TestingWSService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TestingWSService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TestingWS
     */
    @WebEndpoint(name = "TestingWSPort")
    public TestingWS getTestingWSPort() {
        return super.getPort(new QName("http://networkCommunication.testingService/", "TestingWSPort"), TestingWS.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TestingWS
     */
    @WebEndpoint(name = "TestingWSPort")
    public TestingWS getTestingWSPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://networkCommunication.testingService/", "TestingWSPort"), TestingWS.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TESTINGWSSERVICE_EXCEPTION!= null) {
            throw TESTINGWSSERVICE_EXCEPTION;
        }
        return TESTINGWSSERVICE_WSDL_LOCATION;
    }

}