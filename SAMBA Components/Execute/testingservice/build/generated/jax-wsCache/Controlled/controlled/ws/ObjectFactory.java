
package controlled.ws;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the controlled.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Stop_QNAME = new QName("http://ws.controlled/", "stop");
    private final static QName _StopVirtualization_QNAME = new QName("http://ws.controlled/", "stopVirtualization");
    private final static QName _VirtualizeForTestingResponse_QNAME = new QName("http://ws.controlled/", "virtualizeForTestingResponse");
    private final static QName _GetListOfServiceDiscovered_QNAME = new QName("http://ws.controlled/", "getListOfServiceDiscovered");
    private final static QName _StartControlledService_QNAME = new QName("http://ws.controlled/", "startControlledService");
    private final static QName _VirtualizeForTesting_QNAME = new QName("http://ws.controlled/", "virtualizeForTesting");
    private final static QName _GetListOfServiceDiscoveredResponse_QNAME = new QName("http://ws.controlled/", "getListOfServiceDiscoveredResponse");
    private final static QName _StartControlledServiceResponse_QNAME = new QName("http://ws.controlled/", "startControlledServiceResponse");
    private final static QName _StopVirtualizationResponse_QNAME = new QName("http://ws.controlled/", "stopVirtualizationResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: controlled.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StopVirtualization }
     * 
     */
    public StopVirtualization createStopVirtualization() {
        return new StopVirtualization();
    }

    /**
     * Create an instance of {@link StartControlledServiceResponse }
     * 
     */
    public StartControlledServiceResponse createStartControlledServiceResponse() {
        return new StartControlledServiceResponse();
    }

    /**
     * Create an instance of {@link VirtualizeForTesting }
     * 
     */
    public VirtualizeForTesting createVirtualizeForTesting() {
        return new VirtualizeForTesting();
    }

    /**
     * Create an instance of {@link Stop }
     * 
     */
    public Stop createStop() {
        return new Stop();
    }

    /**
     * Create an instance of {@link GetListOfServiceDiscovered }
     * 
     */
    public GetListOfServiceDiscovered createGetListOfServiceDiscovered() {
        return new GetListOfServiceDiscovered();
    }

    /**
     * Create an instance of {@link StopVirtualizationResponse }
     * 
     */
    public StopVirtualizationResponse createStopVirtualizationResponse() {
        return new StopVirtualizationResponse();
    }

    /**
     * Create an instance of {@link VirtualizeForTestingResponse }
     * 
     */
    public VirtualizeForTestingResponse createVirtualizeForTestingResponse() {
        return new VirtualizeForTestingResponse();
    }

    /**
     * Create an instance of {@link GetListOfServiceDiscoveredResponse }
     * 
     */
    public GetListOfServiceDiscoveredResponse createGetListOfServiceDiscoveredResponse() {
        return new GetListOfServiceDiscoveredResponse();
    }

    /**
     * Create an instance of {@link StartControlledService }
     * 
     */
    public StartControlledService createStartControlledService() {
        return new StartControlledService();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stop }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.controlled/", name = "stop")
    public JAXBElement<Stop> createStop(Stop value) {
        return new JAXBElement<Stop>(_Stop_QNAME, Stop.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopVirtualization }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.controlled/", name = "stopVirtualization")
    public JAXBElement<StopVirtualization> createStopVirtualization(StopVirtualization value) {
        return new JAXBElement<StopVirtualization>(_StopVirtualization_QNAME, StopVirtualization.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VirtualizeForTestingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.controlled/", name = "virtualizeForTestingResponse")
    public JAXBElement<VirtualizeForTestingResponse> createVirtualizeForTestingResponse(VirtualizeForTestingResponse value) {
        return new JAXBElement<VirtualizeForTestingResponse>(_VirtualizeForTestingResponse_QNAME, VirtualizeForTestingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListOfServiceDiscovered }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.controlled/", name = "getListOfServiceDiscovered")
    public JAXBElement<GetListOfServiceDiscovered> createGetListOfServiceDiscovered(GetListOfServiceDiscovered value) {
        return new JAXBElement<GetListOfServiceDiscovered>(_GetListOfServiceDiscovered_QNAME, GetListOfServiceDiscovered.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartControlledService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.controlled/", name = "startControlledService")
    public JAXBElement<StartControlledService> createStartControlledService(StartControlledService value) {
        return new JAXBElement<StartControlledService>(_StartControlledService_QNAME, StartControlledService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link VirtualizeForTesting }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.controlled/", name = "virtualizeForTesting")
    public JAXBElement<VirtualizeForTesting> createVirtualizeForTesting(VirtualizeForTesting value) {
        return new JAXBElement<VirtualizeForTesting>(_VirtualizeForTesting_QNAME, VirtualizeForTesting.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetListOfServiceDiscoveredResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.controlled/", name = "getListOfServiceDiscoveredResponse")
    public JAXBElement<GetListOfServiceDiscoveredResponse> createGetListOfServiceDiscoveredResponse(GetListOfServiceDiscoveredResponse value) {
        return new JAXBElement<GetListOfServiceDiscoveredResponse>(_GetListOfServiceDiscoveredResponse_QNAME, GetListOfServiceDiscoveredResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartControlledServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.controlled/", name = "startControlledServiceResponse")
    public JAXBElement<StartControlledServiceResponse> createStartControlledServiceResponse(StartControlledServiceResponse value) {
        return new JAXBElement<StartControlledServiceResponse>(_StartControlledServiceResponse_QNAME, StartControlledServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopVirtualizationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.controlled/", name = "stopVirtualizationResponse")
    public JAXBElement<StopVirtualizationResponse> createStopVirtualizationResponse(StopVirtualizationResponse value) {
        return new JAXBElement<StopVirtualizationResponse>(_StopVirtualizationResponse_QNAME, StopVirtualizationResponse.class, null, value);
    }

}
