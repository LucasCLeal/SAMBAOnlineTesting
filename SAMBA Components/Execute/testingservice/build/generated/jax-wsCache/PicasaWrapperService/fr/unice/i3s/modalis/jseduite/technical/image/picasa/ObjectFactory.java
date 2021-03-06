
package fr.unice.i3s.modalis.jseduite.technical.image.picasa;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.i3s.modalis.jseduite.technical.image.picasa package. 
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

    private final static QName _GetFolksonomyContent_QNAME = new QName("http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", "getFolksonomyContent");
    private final static QName _PicasaWrapperException_QNAME = new QName("http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", "PicasaWrapperException");
    private final static QName _GetAlbumContentResponse_QNAME = new QName("http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", "getAlbumContentResponse");
    private final static QName _GetAlbumContent_QNAME = new QName("http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", "getAlbumContent");
    private final static QName _GetFolksonomyContentResponse_QNAME = new QName("http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", "getFolksonomyContentResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.i3s.modalis.jseduite.technical.image.picasa
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
     * Create an instance of {@link GetFolksonomyContentResponse }
     * 
     */
    public GetFolksonomyContentResponse createGetFolksonomyContentResponse() {
        return new GetFolksonomyContentResponse();
    }

    /**
     * Create an instance of {@link GetAlbumContent }
     * 
     */
    public GetAlbumContent createGetAlbumContent() {
        return new GetAlbumContent();
    }

    /**
     * Create an instance of {@link PicasaWrapperException }
     * 
     */
    public PicasaWrapperException createPicasaWrapperException() {
        return new PicasaWrapperException();
    }

    /**
     * Create an instance of {@link GetFolksonomyContent }
     * 
     */
    public GetFolksonomyContent createGetFolksonomyContent() {
        return new GetFolksonomyContent();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFolksonomyContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "getFolksonomyContent")
    public JAXBElement<GetFolksonomyContent> createGetFolksonomyContent(GetFolksonomyContent value) {
        return new JAXBElement<GetFolksonomyContent>(_GetFolksonomyContent_QNAME, GetFolksonomyContent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PicasaWrapperException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "PicasaWrapperException")
    public JAXBElement<PicasaWrapperException> createPicasaWrapperException(PicasaWrapperException value) {
        return new JAXBElement<PicasaWrapperException>(_PicasaWrapperException_QNAME, PicasaWrapperException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "getAlbumContentResponse")
    public JAXBElement<GetAlbumContentResponse> createGetAlbumContentResponse(GetAlbumContentResponse value) {
        return new JAXBElement<GetAlbumContentResponse>(_GetAlbumContentResponse_QNAME, GetAlbumContentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAlbumContent }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "getAlbumContent")
    public JAXBElement<GetAlbumContent> createGetAlbumContent(GetAlbumContent value) {
        return new JAXBElement<GetAlbumContent>(_GetAlbumContent_QNAME, GetAlbumContent.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFolksonomyContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://picasa.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "getFolksonomyContentResponse")
    public JAXBElement<GetFolksonomyContentResponse> createGetFolksonomyContentResponse(GetFolksonomyContentResponse value) {
        return new JAXBElement<GetFolksonomyContentResponse>(_GetFolksonomyContentResponse_QNAME, GetFolksonomyContentResponse.class, null, value);
    }

}
