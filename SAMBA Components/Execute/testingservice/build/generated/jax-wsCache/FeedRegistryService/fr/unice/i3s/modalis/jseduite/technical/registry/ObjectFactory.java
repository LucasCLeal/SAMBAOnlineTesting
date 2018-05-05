
package fr.unice.i3s.modalis.jseduite.technical.registry;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.i3s.modalis.jseduite.technical.registry package. 
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

    private final static QName _GetCategories_QNAME = new QName("http://registry.technical.jSeduite.modalis.i3s.unice.fr/", "getCategories");
    private final static QName _GetNicknamesResponse_QNAME = new QName("http://registry.technical.jSeduite.modalis.i3s.unice.fr/", "getNicknamesResponse");
    private final static QName _GetURLResponse_QNAME = new QName("http://registry.technical.jSeduite.modalis.i3s.unice.fr/", "getURLResponse");
    private final static QName _FeedRegistryException_QNAME = new QName("http://registry.technical.jSeduite.modalis.i3s.unice.fr/", "FeedRegistryException");
    private final static QName _GetNicknames_QNAME = new QName("http://registry.technical.jSeduite.modalis.i3s.unice.fr/", "getNicknames");
    private final static QName _GetCategoriesResponse_QNAME = new QName("http://registry.technical.jSeduite.modalis.i3s.unice.fr/", "getCategoriesResponse");
    private final static QName _GetURL_QNAME = new QName("http://registry.technical.jSeduite.modalis.i3s.unice.fr/", "getURL");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.i3s.modalis.jseduite.technical.registry
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetNicknames }
     * 
     */
    public GetNicknames createGetNicknames() {
        return new GetNicknames();
    }

    /**
     * Create an instance of {@link GetCategories }
     * 
     */
    public GetCategories createGetCategories() {
        return new GetCategories();
    }

    /**
     * Create an instance of {@link GetURLResponse }
     * 
     */
    public GetURLResponse createGetURLResponse() {
        return new GetURLResponse();
    }

    /**
     * Create an instance of {@link FeedRegistryException }
     * 
     */
    public FeedRegistryException createFeedRegistryException() {
        return new FeedRegistryException();
    }

    /**
     * Create an instance of {@link GetURL }
     * 
     */
    public GetURL createGetURL() {
        return new GetURL();
    }

    /**
     * Create an instance of {@link GetNicknamesResponse }
     * 
     */
    public GetNicknamesResponse createGetNicknamesResponse() {
        return new GetNicknamesResponse();
    }

    /**
     * Create an instance of {@link GetCategoriesResponse }
     * 
     */
    public GetCategoriesResponse createGetCategoriesResponse() {
        return new GetCategoriesResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCategories }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "getCategories")
    public JAXBElement<GetCategories> createGetCategories(GetCategories value) {
        return new JAXBElement<GetCategories>(_GetCategories_QNAME, GetCategories.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNicknamesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "getNicknamesResponse")
    public JAXBElement<GetNicknamesResponse> createGetNicknamesResponse(GetNicknamesResponse value) {
        return new JAXBElement<GetNicknamesResponse>(_GetNicknamesResponse_QNAME, GetNicknamesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetURLResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "getURLResponse")
    public JAXBElement<GetURLResponse> createGetURLResponse(GetURLResponse value) {
        return new JAXBElement<GetURLResponse>(_GetURLResponse_QNAME, GetURLResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeedRegistryException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "FeedRegistryException")
    public JAXBElement<FeedRegistryException> createFeedRegistryException(FeedRegistryException value) {
        return new JAXBElement<FeedRegistryException>(_FeedRegistryException_QNAME, FeedRegistryException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNicknames }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "getNicknames")
    public JAXBElement<GetNicknames> createGetNicknames(GetNicknames value) {
        return new JAXBElement<GetNicknames>(_GetNicknames_QNAME, GetNicknames.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCategoriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "getCategoriesResponse")
    public JAXBElement<GetCategoriesResponse> createGetCategoriesResponse(GetCategoriesResponse value) {
        return new JAXBElement<GetCategoriesResponse>(_GetCategoriesResponse_QNAME, GetCategoriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetURL }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://registry.technical.jSeduite.modalis.i3s.unice.fr/", name = "getURL")
    public JAXBElement<GetURL> createGetURL(GetURL value) {
        return new JAXBElement<GetURL>(_GetURL_QNAME, GetURL.class, null, value);
    }

}
