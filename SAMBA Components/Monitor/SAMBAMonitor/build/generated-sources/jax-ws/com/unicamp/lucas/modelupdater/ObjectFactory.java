
package com.unicamp.lucas.modelupdater;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.unicamp.lucas.modelupdater package. 
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

    private final static QName _UpdateModel_QNAME = new QName("http://modelupdater.lucas.unicamp.com/", "UpdateModel");
    private final static QName _WsdlUpLoadResponse_QNAME = new QName("http://modelupdater.lucas.unicamp.com/", "wsdlUpLoadResponse");
    private final static QName _WsdlUpLoad_QNAME = new QName("http://modelupdater.lucas.unicamp.com/", "wsdlUpLoad");
    private final static QName _IOException_QNAME = new QName("http://modelupdater.lucas.unicamp.com/", "IOException");
    private final static QName _UpdateModelResponse_QNAME = new QName("http://modelupdater.lucas.unicamp.com/", "UpdateModelResponse");
    private final static QName _WsdlUpLoadFileData_QNAME = new QName("", "fileData");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.unicamp.lucas.modelupdater
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link WsdlUpLoad }
     * 
     */
    public WsdlUpLoad createWsdlUpLoad() {
        return new WsdlUpLoad();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link WsdlUpLoadResponse }
     * 
     */
    public WsdlUpLoadResponse createWsdlUpLoadResponse() {
        return new WsdlUpLoadResponse();
    }

    /**
     * Create an instance of {@link UpdateModel }
     * 
     */
    public UpdateModel createUpdateModel() {
        return new UpdateModel();
    }

    /**
     * Create an instance of {@link UpdateModelResponse }
     * 
     */
    public UpdateModelResponse createUpdateModelResponse() {
        return new UpdateModelResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateModel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelupdater.lucas.unicamp.com/", name = "UpdateModel")
    public JAXBElement<UpdateModel> createUpdateModel(UpdateModel value) {
        return new JAXBElement<UpdateModel>(_UpdateModel_QNAME, UpdateModel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WsdlUpLoadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelupdater.lucas.unicamp.com/", name = "wsdlUpLoadResponse")
    public JAXBElement<WsdlUpLoadResponse> createWsdlUpLoadResponse(WsdlUpLoadResponse value) {
        return new JAXBElement<WsdlUpLoadResponse>(_WsdlUpLoadResponse_QNAME, WsdlUpLoadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link WsdlUpLoad }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelupdater.lucas.unicamp.com/", name = "wsdlUpLoad")
    public JAXBElement<WsdlUpLoad> createWsdlUpLoad(WsdlUpLoad value) {
        return new JAXBElement<WsdlUpLoad>(_WsdlUpLoad_QNAME, WsdlUpLoad.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelupdater.lucas.unicamp.com/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateModelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://modelupdater.lucas.unicamp.com/", name = "UpdateModelResponse")
    public JAXBElement<UpdateModelResponse> createUpdateModelResponse(UpdateModelResponse value) {
        return new JAXBElement<UpdateModelResponse>(_UpdateModelResponse_QNAME, UpdateModelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "fileData", scope = WsdlUpLoad.class)
    public JAXBElement<byte[]> createWsdlUpLoadFileData(byte[] value) {
        return new JAXBElement<byte[]>(_WsdlUpLoadFileData_QNAME, byte[].class, WsdlUpLoad.class, ((byte[]) value));
    }

}
