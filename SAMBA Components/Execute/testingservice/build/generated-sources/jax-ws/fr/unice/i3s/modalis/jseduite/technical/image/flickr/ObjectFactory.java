
package fr.unice.i3s.modalis.jseduite.technical.image.flickr;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.i3s.modalis.jseduite.technical.image.flickr package. 
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

    private final static QName _GetAlbumContent_QNAME = new QName("http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", "getAlbumContent");
    private final static QName _GetFolksonomyContentResponse_QNAME = new QName("http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", "getFolksonomyContentResponse");
    private final static QName _FlickrWrapperException_QNAME = new QName("http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", "FlickrWrapperException");
    private final static QName _GetFolksonomyContent_QNAME = new QName("http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", "getFolksonomyContent");
    private final static QName _GetAlbumContentResponse_QNAME = new QName("http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", "getAlbumContentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.i3s.modalis.jseduite.technical.image.flickr
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAlbumContentResponse }
     * 
     */
    public GetAlbumContentResponse createGetAlbumContentResponse() {
        return new GetAlbumContentResponse();
    }

    /**
     * Create an instance of {@link GetAlbumContent }
     * 
     */
    public GetAlbumContent createGetAlbumContent() {
        return new GetAlbumContent();
    }

    /**
     * Create an instance of {@link GetFolksonomyContentResponse }
     * 
     */
    public GetFolksonomyContentResponse createGetFolksonomyContentResponse() {
        return new GetFolksonomyContentResponse();
    }

    /**
     * Create an instance of {@link FlickrWrapperException }
     * 
     */
    public FlickrWrapperException createFlickrWrapperException() {
        return new FlickrWrapperException();
    }

    /**
     * Create an instance of {@link GetFolksonomyContent }
     * 
     */
    public GetFolksonomyContent createGetFolksonomyContent() {
        return new GetFolksonomyContent();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "getAlbumContent")
    public JAXBElement<GetAlbumContent> createGetAlbumContent(GetAlbumContent value) {
        return new JAXBElement<GetAlbumContent>(_GetAlbumContent_QNAME, GetAlbumContent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFolksonomyContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "getFolksonomyContentResponse")
    public JAXBElement<GetFolksonomyContentResponse> createGetFolksonomyContentResponse(GetFolksonomyContentResponse value) {
        return new JAXBElement<GetFolksonomyContentResponse>(_GetFolksonomyContentResponse_QNAME, GetFolksonomyContentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FlickrWrapperException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "FlickrWrapperException")
    public JAXBElement<FlickrWrapperException> createFlickrWrapperException(FlickrWrapperException value) {
        return new JAXBElement<FlickrWrapperException>(_FlickrWrapperException_QNAME, FlickrWrapperException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFolksonomyContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "getFolksonomyContent")
    public JAXBElement<GetFolksonomyContent> createGetFolksonomyContent(GetFolksonomyContent value) {
        return new JAXBElement<GetFolksonomyContent>(_GetFolksonomyContent_QNAME, GetFolksonomyContent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://flickr.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "getAlbumContentResponse")
    public JAXBElement<GetAlbumContentResponse> createGetAlbumContentResponse(GetAlbumContentResponse value) {
        return new JAXBElement<GetAlbumContentResponse>(_GetAlbumContentResponse_QNAME, GetAlbumContentResponse.class, null, value);
    }

}
