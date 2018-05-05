
package fr.unice.i3s.modalis.jseduite.technical.image.helper;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.unice.i3s.modalis.jseduite.technical.image.helper package. 
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

    private final static QName _ShuffleResponse_QNAME = new QName("http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", "shuffleResponse");
    private final static QName _TruncateResponse_QNAME = new QName("http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", "truncateResponse");
    private final static QName _Shuffle_QNAME = new QName("http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", "shuffle");
    private final static QName _Truncate_QNAME = new QName("http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", "truncate");
    private final static QName _Merge_QNAME = new QName("http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", "merge");
    private final static QName _MergeResponse_QNAME = new QName("http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", "mergeResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.unice.i3s.modalis.jseduite.technical.image.helper
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ShuffleResponse }
     * 
     */
    public ShuffleResponse createShuffleResponse() {
        return new ShuffleResponse();
    }

    /**
     * Create an instance of {@link Shuffle }
     * 
     */
    public Shuffle createShuffle() {
        return new Shuffle();
    }

    /**
     * Create an instance of {@link Merge }
     * 
     */
    public Merge createMerge() {
        return new Merge();
    }

    /**
     * Create an instance of {@link TruncateResponse }
     * 
     */
    public TruncateResponse createTruncateResponse() {
        return new TruncateResponse();
    }

    /**
     * Create an instance of {@link Truncate }
     * 
     */
    public Truncate createTruncate() {
        return new Truncate();
    }

    /**
     * Create an instance of {@link MergeResponse }
     * 
     */
    public MergeResponse createMergeResponse() {
        return new MergeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ShuffleResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "shuffleResponse")
    public JAXBElement<ShuffleResponse> createShuffleResponse(ShuffleResponse value) {
        return new JAXBElement<ShuffleResponse>(_ShuffleResponse_QNAME, ShuffleResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TruncateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "truncateResponse")
    public JAXBElement<TruncateResponse> createTruncateResponse(TruncateResponse value) {
        return new JAXBElement<TruncateResponse>(_TruncateResponse_QNAME, TruncateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Shuffle }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "shuffle")
    public JAXBElement<Shuffle> createShuffle(Shuffle value) {
        return new JAXBElement<Shuffle>(_Shuffle_QNAME, Shuffle.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Truncate }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "truncate")
    public JAXBElement<Truncate> createTruncate(Truncate value) {
        return new JAXBElement<Truncate>(_Truncate_QNAME, Truncate.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Merge }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "merge")
    public JAXBElement<Merge> createMerge(Merge value) {
        return new JAXBElement<Merge>(_Merge_QNAME, Merge.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MergeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", name = "mergeResponse")
    public JAXBElement<MergeResponse> createMergeResponse(MergeResponse value) {
        return new JAXBElement<MergeResponse>(_MergeResponse_QNAME, MergeResponse.class, null, value);
    }

}
