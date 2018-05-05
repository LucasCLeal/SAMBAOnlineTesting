
package fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin package. 
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

    private final static QName _GetHyperEvents_QNAME = new QName("http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", "getHyperEvents");
    private final static QName _GetHyperEventsRestrictionResponse_QNAME = new QName("http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", "getHyperEventsRestrictionResponse");
    private final static QName _GetHyperEventsResponse_QNAME = new QName("http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", "getHyperEventsResponse");
    private final static QName _HyperException_QNAME = new QName("http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", "HyperException");
    private final static QName _GetHyperEventsRestriction_QNAME = new QName("http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", "getHyperEventsRestriction");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetHyperEventsRestriction }
     * 
     */
    public GetHyperEventsRestriction createGetHyperEventsRestriction() {
        return new GetHyperEventsRestriction();
    }

    /**
     * Create an instance of {@link HyperGroup }
     * 
     */
    public HyperGroup createHyperGroup() {
        return new HyperGroup();
    }

    /**
     * Create an instance of {@link GetHyperEventsRestrictionResponse }
     * 
     */
    public GetHyperEventsRestrictionResponse createGetHyperEventsRestrictionResponse() {
        return new GetHyperEventsRestrictionResponse();
    }

    /**
     * Create an instance of {@link HyperException }
     * 
     */
    public HyperException createHyperException() {
        return new HyperException();
    }

    /**
     * Create an instance of {@link HyperEvent }
     * 
     */
    public HyperEvent createHyperEvent() {
        return new HyperEvent();
    }

    /**
     * Create an instance of {@link HyperPromo }
     * 
     */
    public HyperPromo createHyperPromo() {
        return new HyperPromo();
    }

    /**
     * Create an instance of {@link GetHyperEvents }
     * 
     */
    public GetHyperEvents createGetHyperEvents() {
        return new GetHyperEvents();
    }

    /**
     * Create an instance of {@link GetHyperEventsResponse }
     * 
     */
    public GetHyperEventsResponse createGetHyperEventsResponse() {
        return new GetHyperEventsResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHyperEvents }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", name = "getHyperEvents")
    public JAXBElement<GetHyperEvents> createGetHyperEvents(GetHyperEvents value) {
        return new JAXBElement<GetHyperEvents>(_GetHyperEvents_QNAME, GetHyperEvents.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHyperEventsRestrictionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", name = "getHyperEventsRestrictionResponse")
    public JAXBElement<GetHyperEventsRestrictionResponse> createGetHyperEventsRestrictionResponse(GetHyperEventsRestrictionResponse value) {
        return new JAXBElement<GetHyperEventsRestrictionResponse>(_GetHyperEventsRestrictionResponse_QNAME, GetHyperEventsRestrictionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHyperEventsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", name = "getHyperEventsResponse")
    public JAXBElement<GetHyperEventsResponse> createGetHyperEventsResponse(GetHyperEventsResponse value) {
        return new JAXBElement<GetHyperEventsResponse>(_GetHyperEventsResponse_QNAME, GetHyperEventsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link HyperException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", name = "HyperException")
    public JAXBElement<HyperException> createHyperException(HyperException value) {
        return new JAXBElement<HyperException>(_HyperException_QNAME, HyperException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetHyperEventsRestriction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin", name = "getHyperEventsRestriction")
    public JAXBElement<GetHyperEventsRestriction> createGetHyperEventsRestriction(GetHyperEventsRestriction value) {
        return new JAXBElement<GetHyperEventsRestriction>(_GetHyperEventsRestriction_QNAME, GetHyperEventsRestriction.class, null, value);
    }

}
