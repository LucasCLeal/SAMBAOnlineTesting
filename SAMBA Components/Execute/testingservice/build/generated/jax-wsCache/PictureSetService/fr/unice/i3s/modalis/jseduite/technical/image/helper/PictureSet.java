
package fr.unice.i3s.modalis.jseduite.technical.image.helper;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3.3-hudson-757-SNAPSHOT
 * Generated source version: 2.1
 * 
 */
@WebService(name = "PictureSet", targetNamespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PictureSet {


    /**
     * 
     * @param set
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "shuffle", targetNamespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.image.helper.Shuffle")
    @ResponseWrapper(localName = "shuffleResponse", targetNamespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.image.helper.ShuffleResponse")
    public List<String> shuffle(
        @WebParam(name = "set", targetNamespace = "")
        List<String> set);

    /**
     * 
     * @param second
     * @param first
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "merge", targetNamespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.image.helper.Merge")
    @ResponseWrapper(localName = "mergeResponse", targetNamespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.image.helper.MergeResponse")
    public List<String> merge(
        @WebParam(name = "first", targetNamespace = "")
        List<String> first,
        @WebParam(name = "second", targetNamespace = "")
        List<String> second);

    /**
     * 
     * @param count
     * @param set
     * @return
     *     returns java.util.List<java.lang.String>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "truncate", targetNamespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.image.helper.Truncate")
    @ResponseWrapper(localName = "truncateResponse", targetNamespace = "http://helper.image.technical.jSeduite.modalis.i3s.unice.fr/", className = "fr.unice.i3s.modalis.jseduite.technical.image.helper.TruncateResponse")
    public List<String> truncate(
        @WebParam(name = "set", targetNamespace = "")
        List<String> set,
        @WebParam(name = "count", targetNamespace = "")
        int count);

}
