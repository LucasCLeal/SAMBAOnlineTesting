
package testingservice.networkcommunication;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the testingservice.networkcommunication package. 
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

    private final static QName _GetDependenciesPathForBPELNameResponse_QNAME = new QName("http://networkCommunication.testingService/", "getDependenciesPathForBPELNameResponse");
    private final static QName _SendUpdateNumberOfBpelFiles_QNAME = new QName("http://networkCommunication.testingService/", "sendUpdateNumberOfBpelFiles");
    private final static QName _ControlServiceHasChangedResponse_QNAME = new QName("http://networkCommunication.testingService/", "controlServiceHasChangedResponse");
    private final static QName _ResetTestVariablesResponse_QNAME = new QName("http://networkCommunication.testingService/", "resetTestVariablesResponse");
    private final static QName _ExeTestInstruction_QNAME = new QName("http://networkCommunication.testingService/", "exeTestInstruction");
    private final static QName _SendUpdateBpelFiles_QNAME = new QName("http://networkCommunication.testingService/", "sendUpdateBpelFiles");
    private final static QName _StartOnlineModelBasedTestforBpel_QNAME = new QName("http://networkCommunication.testingService/", "startOnlineModelBasedTestforBpel");
    private final static QName _ExeTestInstructionResponse_QNAME = new QName("http://networkCommunication.testingService/", "exeTestInstructionResponse");
    private final static QName _FetchDataFromFilePath_QNAME = new QName("http://networkCommunication.testingService/", "fetchDataFromFilePath");
    private final static QName _SendUpdateBpelFilesResponse_QNAME = new QName("http://networkCommunication.testingService/", "sendUpdateBpelFilesResponse");
    private final static QName _Stop_QNAME = new QName("http://networkCommunication.testingService/", "stop");
    private final static QName _IOException_QNAME = new QName("http://networkCommunication.testingService/", "IOException");
    private final static QName _SendUpdateNumberOfBpelFilesResponse_QNAME = new QName("http://networkCommunication.testingService/", "sendUpdateNumberOfBpelFilesResponse");
    private final static QName _FileNotFoundException_QNAME = new QName("http://networkCommunication.testingService/", "FileNotFoundException");
    private final static QName _ControlServiceHasChanged_QNAME = new QName("http://networkCommunication.testingService/", "controlServiceHasChanged");
    private final static QName _FetchDataFromFilePathResponse_QNAME = new QName("http://networkCommunication.testingService/", "fetchDataFromFilePathResponse");
    private final static QName _StartTestingServiceResponse_QNAME = new QName("http://networkCommunication.testingService/", "startTestingServiceResponse");
    private final static QName _StartTestingService_QNAME = new QName("http://networkCommunication.testingService/", "startTestingService");
    private final static QName _ResetTestVariables_QNAME = new QName("http://networkCommunication.testingService/", "resetTestVariables");
    private final static QName _StartOnlineModelBasedTestforBpelResponse_QNAME = new QName("http://networkCommunication.testingService/", "startOnlineModelBasedTestforBpelResponse");
    private final static QName _GetDependenciesPathForBPELName_QNAME = new QName("http://networkCommunication.testingService/", "getDependenciesPathForBPELName");
    private final static QName _FetchDataFromFilePathResponseReturn_QNAME = new QName("", "return");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: testingservice.networkcommunication
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StartTestingService }
     * 
     */
    public StartTestingService createStartTestingService() {
        return new StartTestingService();
    }

    /**
     * Create an instance of {@link GetDependenciesPathForBPELName }
     * 
     */
    public GetDependenciesPathForBPELName createGetDependenciesPathForBPELName() {
        return new GetDependenciesPathForBPELName();
    }

    /**
     * Create an instance of {@link ResetTestVariables }
     * 
     */
    public ResetTestVariables createResetTestVariables() {
        return new ResetTestVariables();
    }

    /**
     * Create an instance of {@link StartOnlineModelBasedTestforBpelResponse }
     * 
     */
    public StartOnlineModelBasedTestforBpelResponse createStartOnlineModelBasedTestforBpelResponse() {
        return new StartOnlineModelBasedTestforBpelResponse();
    }

    /**
     * Create an instance of {@link FileNotFoundException }
     * 
     */
    public FileNotFoundException createFileNotFoundException() {
        return new FileNotFoundException();
    }

    /**
     * Create an instance of {@link ControlServiceHasChanged }
     * 
     */
    public ControlServiceHasChanged createControlServiceHasChanged() {
        return new ControlServiceHasChanged();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link SendUpdateNumberOfBpelFilesResponse }
     * 
     */
    public SendUpdateNumberOfBpelFilesResponse createSendUpdateNumberOfBpelFilesResponse() {
        return new SendUpdateNumberOfBpelFilesResponse();
    }

    /**
     * Create an instance of {@link StartTestingServiceResponse }
     * 
     */
    public StartTestingServiceResponse createStartTestingServiceResponse() {
        return new StartTestingServiceResponse();
    }

    /**
     * Create an instance of {@link FetchDataFromFilePathResponse }
     * 
     */
    public FetchDataFromFilePathResponse createFetchDataFromFilePathResponse() {
        return new FetchDataFromFilePathResponse();
    }

    /**
     * Create an instance of {@link ExeTestInstructionResponse }
     * 
     */
    public ExeTestInstructionResponse createExeTestInstructionResponse() {
        return new ExeTestInstructionResponse();
    }

    /**
     * Create an instance of {@link SendUpdateBpelFiles }
     * 
     */
    public SendUpdateBpelFiles createSendUpdateBpelFiles() {
        return new SendUpdateBpelFiles();
    }

    /**
     * Create an instance of {@link StartOnlineModelBasedTestforBpel }
     * 
     */
    public StartOnlineModelBasedTestforBpel createStartOnlineModelBasedTestforBpel() {
        return new StartOnlineModelBasedTestforBpel();
    }

    /**
     * Create an instance of {@link SendUpdateBpelFilesResponse }
     * 
     */
    public SendUpdateBpelFilesResponse createSendUpdateBpelFilesResponse() {
        return new SendUpdateBpelFilesResponse();
    }

    /**
     * Create an instance of {@link Stop }
     * 
     */
    public Stop createStop() {
        return new Stop();
    }

    /**
     * Create an instance of {@link FetchDataFromFilePath }
     * 
     */
    public FetchDataFromFilePath createFetchDataFromFilePath() {
        return new FetchDataFromFilePath();
    }

    /**
     * Create an instance of {@link ControlServiceHasChangedResponse }
     * 
     */
    public ControlServiceHasChangedResponse createControlServiceHasChangedResponse() {
        return new ControlServiceHasChangedResponse();
    }

    /**
     * Create an instance of {@link ResetTestVariablesResponse }
     * 
     */
    public ResetTestVariablesResponse createResetTestVariablesResponse() {
        return new ResetTestVariablesResponse();
    }

    /**
     * Create an instance of {@link GetDependenciesPathForBPELNameResponse }
     * 
     */
    public GetDependenciesPathForBPELNameResponse createGetDependenciesPathForBPELNameResponse() {
        return new GetDependenciesPathForBPELNameResponse();
    }

    /**
     * Create an instance of {@link SendUpdateNumberOfBpelFiles }
     * 
     */
    public SendUpdateNumberOfBpelFiles createSendUpdateNumberOfBpelFiles() {
        return new SendUpdateNumberOfBpelFiles();
    }

    /**
     * Create an instance of {@link ExeTestInstruction }
     * 
     */
    public ExeTestInstruction createExeTestInstruction() {
        return new ExeTestInstruction();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDependenciesPathForBPELNameResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "getDependenciesPathForBPELNameResponse")
    public JAXBElement<GetDependenciesPathForBPELNameResponse> createGetDependenciesPathForBPELNameResponse(GetDependenciesPathForBPELNameResponse value) {
        return new JAXBElement<GetDependenciesPathForBPELNameResponse>(_GetDependenciesPathForBPELNameResponse_QNAME, GetDependenciesPathForBPELNameResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUpdateNumberOfBpelFiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "sendUpdateNumberOfBpelFiles")
    public JAXBElement<SendUpdateNumberOfBpelFiles> createSendUpdateNumberOfBpelFiles(SendUpdateNumberOfBpelFiles value) {
        return new JAXBElement<SendUpdateNumberOfBpelFiles>(_SendUpdateNumberOfBpelFiles_QNAME, SendUpdateNumberOfBpelFiles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ControlServiceHasChangedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "controlServiceHasChangedResponse")
    public JAXBElement<ControlServiceHasChangedResponse> createControlServiceHasChangedResponse(ControlServiceHasChangedResponse value) {
        return new JAXBElement<ControlServiceHasChangedResponse>(_ControlServiceHasChangedResponse_QNAME, ControlServiceHasChangedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetTestVariablesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "resetTestVariablesResponse")
    public JAXBElement<ResetTestVariablesResponse> createResetTestVariablesResponse(ResetTestVariablesResponse value) {
        return new JAXBElement<ResetTestVariablesResponse>(_ResetTestVariablesResponse_QNAME, ResetTestVariablesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExeTestInstruction }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "exeTestInstruction")
    public JAXBElement<ExeTestInstruction> createExeTestInstruction(ExeTestInstruction value) {
        return new JAXBElement<ExeTestInstruction>(_ExeTestInstruction_QNAME, ExeTestInstruction.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUpdateBpelFiles }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "sendUpdateBpelFiles")
    public JAXBElement<SendUpdateBpelFiles> createSendUpdateBpelFiles(SendUpdateBpelFiles value) {
        return new JAXBElement<SendUpdateBpelFiles>(_SendUpdateBpelFiles_QNAME, SendUpdateBpelFiles.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartOnlineModelBasedTestforBpel }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "startOnlineModelBasedTestforBpel")
    public JAXBElement<StartOnlineModelBasedTestforBpel> createStartOnlineModelBasedTestforBpel(StartOnlineModelBasedTestforBpel value) {
        return new JAXBElement<StartOnlineModelBasedTestforBpel>(_StartOnlineModelBasedTestforBpel_QNAME, StartOnlineModelBasedTestforBpel.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExeTestInstructionResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "exeTestInstructionResponse")
    public JAXBElement<ExeTestInstructionResponse> createExeTestInstructionResponse(ExeTestInstructionResponse value) {
        return new JAXBElement<ExeTestInstructionResponse>(_ExeTestInstructionResponse_QNAME, ExeTestInstructionResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchDataFromFilePath }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "fetchDataFromFilePath")
    public JAXBElement<FetchDataFromFilePath> createFetchDataFromFilePath(FetchDataFromFilePath value) {
        return new JAXBElement<FetchDataFromFilePath>(_FetchDataFromFilePath_QNAME, FetchDataFromFilePath.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUpdateBpelFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "sendUpdateBpelFilesResponse")
    public JAXBElement<SendUpdateBpelFilesResponse> createSendUpdateBpelFilesResponse(SendUpdateBpelFilesResponse value) {
        return new JAXBElement<SendUpdateBpelFilesResponse>(_SendUpdateBpelFilesResponse_QNAME, SendUpdateBpelFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Stop }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "stop")
    public JAXBElement<Stop> createStop(Stop value) {
        return new JAXBElement<Stop>(_Stop_QNAME, Stop.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SendUpdateNumberOfBpelFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "sendUpdateNumberOfBpelFilesResponse")
    public JAXBElement<SendUpdateNumberOfBpelFilesResponse> createSendUpdateNumberOfBpelFilesResponse(SendUpdateNumberOfBpelFilesResponse value) {
        return new JAXBElement<SendUpdateNumberOfBpelFilesResponse>(_SendUpdateNumberOfBpelFilesResponse_QNAME, SendUpdateNumberOfBpelFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileNotFoundException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "FileNotFoundException")
    public JAXBElement<FileNotFoundException> createFileNotFoundException(FileNotFoundException value) {
        return new JAXBElement<FileNotFoundException>(_FileNotFoundException_QNAME, FileNotFoundException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ControlServiceHasChanged }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "controlServiceHasChanged")
    public JAXBElement<ControlServiceHasChanged> createControlServiceHasChanged(ControlServiceHasChanged value) {
        return new JAXBElement<ControlServiceHasChanged>(_ControlServiceHasChanged_QNAME, ControlServiceHasChanged.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FetchDataFromFilePathResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "fetchDataFromFilePathResponse")
    public JAXBElement<FetchDataFromFilePathResponse> createFetchDataFromFilePathResponse(FetchDataFromFilePathResponse value) {
        return new JAXBElement<FetchDataFromFilePathResponse>(_FetchDataFromFilePathResponse_QNAME, FetchDataFromFilePathResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTestingServiceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "startTestingServiceResponse")
    public JAXBElement<StartTestingServiceResponse> createStartTestingServiceResponse(StartTestingServiceResponse value) {
        return new JAXBElement<StartTestingServiceResponse>(_StartTestingServiceResponse_QNAME, StartTestingServiceResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTestingService }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "startTestingService")
    public JAXBElement<StartTestingService> createStartTestingService(StartTestingService value) {
        return new JAXBElement<StartTestingService>(_StartTestingService_QNAME, StartTestingService.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ResetTestVariables }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "resetTestVariables")
    public JAXBElement<ResetTestVariables> createResetTestVariables(ResetTestVariables value) {
        return new JAXBElement<ResetTestVariables>(_ResetTestVariables_QNAME, ResetTestVariables.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartOnlineModelBasedTestforBpelResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "startOnlineModelBasedTestforBpelResponse")
    public JAXBElement<StartOnlineModelBasedTestforBpelResponse> createStartOnlineModelBasedTestforBpelResponse(StartOnlineModelBasedTestforBpelResponse value) {
        return new JAXBElement<StartOnlineModelBasedTestforBpelResponse>(_StartOnlineModelBasedTestforBpelResponse_QNAME, StartOnlineModelBasedTestforBpelResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetDependenciesPathForBPELName }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://networkCommunication.testingService/", name = "getDependenciesPathForBPELName")
    public JAXBElement<GetDependenciesPathForBPELName> createGetDependenciesPathForBPELName(GetDependenciesPathForBPELName value) {
        return new JAXBElement<GetDependenciesPathForBPELName>(_GetDependenciesPathForBPELName_QNAME, GetDependenciesPathForBPELName.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "return", scope = FetchDataFromFilePathResponse.class)
    public JAXBElement<byte[]> createFetchDataFromFilePathResponseReturn(byte[] value) {
        return new JAXBElement<byte[]>(_FetchDataFromFilePathResponseReturn_QNAME, byte[].class, FetchDataFromFilePathResponse.class, ((byte[]) value));
    }

}
