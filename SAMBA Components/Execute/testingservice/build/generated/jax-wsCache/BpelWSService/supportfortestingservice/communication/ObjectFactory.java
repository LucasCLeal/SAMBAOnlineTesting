
package supportfortestingservice.communication;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the supportfortestingservice.communication package. 
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

    private final static QName _GetBpelFileNumber_QNAME = new QName("http://communication.supportForTestingService/", "getBpelFileNumber");
    private final static QName _StopVirtualCopyResponse_QNAME = new QName("http://communication.supportForTestingService/", "stopVirtualCopyResponse");
    private final static QName _GetBpelFileResponse_QNAME = new QName("http://communication.supportForTestingService/", "getBpelFileResponse");
    private final static QName _StopVirtualCopy_QNAME = new QName("http://communication.supportForTestingService/", "stopVirtualCopy");
    private final static QName _StartVirtualCopy_QNAME = new QName("http://communication.supportForTestingService/", "startVirtualCopy");
    private final static QName _GetSoaStatus_QNAME = new QName("http://communication.supportForTestingService/", "getSoaStatus");
    private final static QName _StartTestingServiceResponse_QNAME = new QName("http://communication.supportForTestingService/", "startTestingServiceResponse");
    private final static QName _GetAWsdlFilesResponse_QNAME = new QName("http://communication.supportForTestingService/", "getAWsdlFilesResponse");
    private final static QName _SetSoaStatusResponse_QNAME = new QName("http://communication.supportForTestingService/", "setSoaStatusResponse");
    private final static QName _GetBpelFile_QNAME = new QName("http://communication.supportForTestingService/", "getBpelFile");
    private final static QName _GetSoaStatusResponse_QNAME = new QName("http://communication.supportForTestingService/", "getSoaStatusResponse");
    private final static QName _SetSoaStatus_QNAME = new QName("http://communication.supportForTestingService/", "setSoaStatus");
    private final static QName _StartVirtualCopyResponse_QNAME = new QName("http://communication.supportForTestingService/", "startVirtualCopyResponse");
    private final static QName _GetAWsdlFiles_QNAME = new QName("http://communication.supportForTestingService/", "getAWsdlFiles");
    private final static QName _GetBpelFileNumberResponse_QNAME = new QName("http://communication.supportForTestingService/", "getBpelFileNumberResponse");
    private final static QName _StartTestingService_QNAME = new QName("http://communication.supportForTestingService/", "startTestingService");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: supportfortestingservice.communication
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link SetSoaStatusResponse }
     * 
     */
    public SetSoaStatusResponse createSetSoaStatusResponse() {
        return new SetSoaStatusResponse();
    }

    /**
     * Create an instance of {@link GetBpelFileResponse }
     * 
     */
    public GetBpelFileResponse createGetBpelFileResponse() {
        return new GetBpelFileResponse();
    }

    /**
     * Create an instance of {@link GetBpelFile }
     * 
     */
    public GetBpelFile createGetBpelFile() {
        return new GetBpelFile();
    }

    /**
     * Create an instance of {@link SetSoaStatus }
     * 
     */
    public SetSoaStatus createSetSoaStatus() {
        return new SetSoaStatus();
    }

    /**
     * Create an instance of {@link GetBpelFileNumberResponse }
     * 
     */
    public GetBpelFileNumberResponse createGetBpelFileNumberResponse() {
        return new GetBpelFileNumberResponse();
    }

    /**
     * Create an instance of {@link StopVirtualCopy }
     * 
     */
    public StopVirtualCopy createStopVirtualCopy() {
        return new StopVirtualCopy();
    }

    /**
     * Create an instance of {@link GetAWsdlFilesResponse }
     * 
     */
    public GetAWsdlFilesResponse createGetAWsdlFilesResponse() {
        return new GetAWsdlFilesResponse();
    }

    /**
     * Create an instance of {@link GetSoaStatusResponse }
     * 
     */
    public GetSoaStatusResponse createGetSoaStatusResponse() {
        return new GetSoaStatusResponse();
    }

    /**
     * Create an instance of {@link GetAWsdlFiles }
     * 
     */
    public GetAWsdlFiles createGetAWsdlFiles() {
        return new GetAWsdlFiles();
    }

    /**
     * Create an instance of {@link StartTestingServiceResponse }
     * 
     */
    public StartTestingServiceResponse createStartTestingServiceResponse() {
        return new StartTestingServiceResponse();
    }

    /**
     * Create an instance of {@link GetSoaStatus }
     * 
     */
    public GetSoaStatus createGetSoaStatus() {
        return new GetSoaStatus();
    }

    /**
     * Create an instance of {@link StartVirtualCopyResponse }
     * 
     */
    public StartVirtualCopyResponse createStartVirtualCopyResponse() {
        return new StartVirtualCopyResponse();
    }

    /**
     * Create an instance of {@link StartVirtualCopy }
     * 
     */
    public StartVirtualCopy createStartVirtualCopy() {
        return new StartVirtualCopy();
    }

    /**
     * Create an instance of {@link GetBpelFileNumber }
     * 
     */
    public GetBpelFileNumber createGetBpelFileNumber() {
        return new GetBpelFileNumber();
    }

    /**
     * Create an instance of {@link StopVirtualCopyResponse }
     * 
     */
    public StopVirtualCopyResponse createStopVirtualCopyResponse() {
        return new StopVirtualCopyResponse();
    }

    /**
     * Create an instance of {@link StartTestingService }
     * 
     */
    public StartTestingService createStartTestingService() {
        return new StartTestingService();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBpelFileNumber }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "getBpelFileNumber")
    public JAXBElement<GetBpelFileNumber> createGetBpelFileNumber(GetBpelFileNumber value) {
        return new JAXBElement<GetBpelFileNumber>(_GetBpelFileNumber_QNAME, GetBpelFileNumber.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopVirtualCopyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "stopVirtualCopyResponse")
    public JAXBElement<StopVirtualCopyResponse> createStopVirtualCopyResponse(StopVirtualCopyResponse value) {
        return new JAXBElement<StopVirtualCopyResponse>(_StopVirtualCopyResponse_QNAME, StopVirtualCopyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBpelFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "getBpelFileResponse")
    public JAXBElement<GetBpelFileResponse> createGetBpelFileResponse(GetBpelFileResponse value) {
        return new JAXBElement<GetBpelFileResponse>(_GetBpelFileResponse_QNAME, GetBpelFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StopVirtualCopy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "stopVirtualCopy")
    public JAXBElement<StopVirtualCopy> createStopVirtualCopy(StopVirtualCopy value) {
        return new JAXBElement<StopVirtualCopy>(_StopVirtualCopy_QNAME, StopVirtualCopy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartVirtualCopy }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "startVirtualCopy")
    public JAXBElement<StartVirtualCopy> createStartVirtualCopy(StartVirtualCopy value) {
        return new JAXBElement<StartVirtualCopy>(_StartVirtualCopy_QNAME, StartVirtualCopy.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSoaStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "getSoaStatus")
    public JAXBElement<GetSoaStatus> createGetSoaStatus(GetSoaStatus value) {
        return new JAXBElement<GetSoaStatus>(_GetSoaStatus_QNAME, GetSoaStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTestingServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "startTestingServiceResponse")
    public JAXBElement<StartTestingServiceResponse> createStartTestingServiceResponse(StartTestingServiceResponse value) {
        return new JAXBElement<StartTestingServiceResponse>(_StartTestingServiceResponse_QNAME, StartTestingServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAWsdlFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "getAWsdlFilesResponse")
    public JAXBElement<GetAWsdlFilesResponse> createGetAWsdlFilesResponse(GetAWsdlFilesResponse value) {
        return new JAXBElement<GetAWsdlFilesResponse>(_GetAWsdlFilesResponse_QNAME, GetAWsdlFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetSoaStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "setSoaStatusResponse")
    public JAXBElement<SetSoaStatusResponse> createSetSoaStatusResponse(SetSoaStatusResponse value) {
        return new JAXBElement<SetSoaStatusResponse>(_SetSoaStatusResponse_QNAME, SetSoaStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBpelFile }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "getBpelFile")
    public JAXBElement<GetBpelFile> createGetBpelFile(GetBpelFile value) {
        return new JAXBElement<GetBpelFile>(_GetBpelFile_QNAME, GetBpelFile.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetSoaStatusResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "getSoaStatusResponse")
    public JAXBElement<GetSoaStatusResponse> createGetSoaStatusResponse(GetSoaStatusResponse value) {
        return new JAXBElement<GetSoaStatusResponse>(_GetSoaStatusResponse_QNAME, GetSoaStatusResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SetSoaStatus }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "setSoaStatus")
    public JAXBElement<SetSoaStatus> createSetSoaStatus(SetSoaStatus value) {
        return new JAXBElement<SetSoaStatus>(_SetSoaStatus_QNAME, SetSoaStatus.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartVirtualCopyResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "startVirtualCopyResponse")
    public JAXBElement<StartVirtualCopyResponse> createStartVirtualCopyResponse(StartVirtualCopyResponse value) {
        return new JAXBElement<StartVirtualCopyResponse>(_StartVirtualCopyResponse_QNAME, StartVirtualCopyResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAWsdlFiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "getAWsdlFiles")
    public JAXBElement<GetAWsdlFiles> createGetAWsdlFiles(GetAWsdlFiles value) {
        return new JAXBElement<GetAWsdlFiles>(_GetAWsdlFiles_QNAME, GetAWsdlFiles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBpelFileNumberResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "getBpelFileNumberResponse")
    public JAXBElement<GetBpelFileNumberResponse> createGetBpelFileNumberResponse(GetBpelFileNumberResponse value) {
        return new JAXBElement<GetBpelFileNumberResponse>(_GetBpelFileNumberResponse_QNAME, GetBpelFileNumberResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTestingService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://communication.supportForTestingService/", name = "startTestingService")
    public JAXBElement<StartTestingService> createStartTestingService(StartTestingService value) {
        return new JAXBElement<StartTestingService>(_StartTestingService_QNAME, StartTestingService.class, null, value);
    }

}
