
package fr.unice.i3s.modalis.jseduite.technical.registry.partners;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.i3s.modalis.jseduite.technical.registry.partners package. 
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

    private final static QName _GetResponse_QNAME = new QName("http://partners.registry.technical.jSeduite.modalis.i3s.unice.fr/", "getResponse");
    private final static QName _PartnerKeysException_QNAME = new QName("http://partners.registry.technical.jSeduite.modalis.i3s.unice.fr/", "PartnerKeysException");
    private final static QName _Get_QNAME = new QName("http://partners.registry.technical.jSeduite.modalis.i3s.unice.fr/", "get");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.i3s.modalis.jseduite.technical.registry.partners
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetResponse }
     * 
     */
    public GetResponse createGetResponse() {
        return new GetResponse();
    }

    /**
     * Create an instance of {@link PartnerKeysException }
     * 
     */
    public PartnerKeysException createPartnerKeysException() {
        return new PartnerKeysException();
    }

    /**
     * Create an instance of {@link Get }
     * 
     */
    public Get createGet() {
        return new Get();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://partners.registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "getResponse")
    public JAXBElement<GetResponse> createGetResponse(GetResponse value) {
        return new JAXBElement<GetResponse>(_GetResponse_QNAME, GetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PartnerKeysException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://partners.registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "PartnerKeysException")
    public JAXBElement<PartnerKeysException> createPartnerKeysException(PartnerKeysException value) {
        return new JAXBElement<PartnerKeysException>(_PartnerKeysException_QNAME, PartnerKeysException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Get }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://partners.registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "get")
    public JAXBElement<Get> createGet(Get value) {
        return new JAXBElement<Get>(_Get_QNAME, Get.class, null, value);
    }

}
