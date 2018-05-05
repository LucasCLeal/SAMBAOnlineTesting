
package com.unicamp.lucas.testcasegen;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.unicamp.lucas.testcasegen package. 
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

    private final static QName _FinishtestCaseGeneration_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "finishtestCaseGeneration");
    private final static QName _AbortTestCaseGen_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "abortTestCaseGen");
    private final static QName _ExecuteTestResponse_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "ExecuteTestResponse");
    private final static QName _StartTestCaseGenForBpelModelNamedResponse_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "startTestCaseGenForBpelModelNamedResponse");
    private final static QName _AddExeResulttoTestReport_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "addExeResulttoTestReport");
    private final static QName _ExecuteTest_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "ExecuteTest");
    private final static QName _AddExeResulttoTestReportResponse_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "addExeResulttoTestReportResponse");
    private final static QName _IOException_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "IOException");
    private final static QName _ExecuteAllTestsResponse_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "executeAllTestsResponse");
    private final static QName _AbortTestCaseGenResponse_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "abortTestCaseGenResponse");
    private final static QName _ExecuteAllTests_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "executeAllTests");
    private final static QName _StartTestCaseGenForBpelModelNamed_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "startTestCaseGenForBpelModelNamed");
    private final static QName _GetNextStepForBpelModelNamedResponse_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "getNextStepForBpelModelNamedResponse");
    private final static QName _FinishtestCaseGenerationResponse_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "finishtestCaseGenerationResponse");
    private final static QName _GetNextStepForBpelModelNamed_QNAME = new QName("http://testCaseGen.lucas.unicamp.com/", "getNextStepForBpelModelNamed");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.unicamp.lucas.testcasegen
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ExecuteTest }
     * 
     */
    public ExecuteTest createExecuteTest() {
        return new ExecuteTest();
    }

    /**
     * Create an instance of {@link ExecuteTestResponse }
     * 
     */
    public ExecuteTestResponse createExecuteTestResponse() {
        return new ExecuteTestResponse();
    }

    /**
     * Create an instance of {@link StartTestCaseGenForBpelModelNamedResponse }
     * 
     */
    public StartTestCaseGenForBpelModelNamedResponse createStartTestCaseGenForBpelModelNamedResponse() {
        return new StartTestCaseGenForBpelModelNamedResponse();
    }

    /**
     * Create an instance of {@link AddExeResulttoTestReport }
     * 
     */
    public AddExeResulttoTestReport createAddExeResulttoTestReport() {
        return new AddExeResulttoTestReport();
    }

    /**
     * Create an instance of {@link AbortTestCaseGen }
     * 
     */
    public AbortTestCaseGen createAbortTestCaseGen() {
        return new AbortTestCaseGen();
    }

    /**
     * Create an instance of {@link FinishtestCaseGeneration }
     * 
     */
    public FinishtestCaseGeneration createFinishtestCaseGeneration() {
        return new FinishtestCaseGeneration();
    }

    /**
     * Create an instance of {@link ExecuteAllTestsResponse }
     * 
     */
    public ExecuteAllTestsResponse createExecuteAllTestsResponse() {
        return new ExecuteAllTestsResponse();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link AddExeResulttoTestReportResponse }
     * 
     */
    public AddExeResulttoTestReportResponse createAddExeResulttoTestReportResponse() {
        return new AddExeResulttoTestReportResponse();
    }

    /**
     * Create an instance of {@link ExecuteAllTests }
     * 
     */
    public ExecuteAllTests createExecuteAllTests() {
        return new ExecuteAllTests();
    }

    /**
     * Create an instance of {@link AbortTestCaseGenResponse }
     * 
     */
    public AbortTestCaseGenResponse createAbortTestCaseGenResponse() {
        return new AbortTestCaseGenResponse();
    }

    /**
     * Create an instance of {@link GetNextStepForBpelModelNamed }
     * 
     */
    public GetNextStepForBpelModelNamed createGetNextStepForBpelModelNamed() {
        return new GetNextStepForBpelModelNamed();
    }

    /**
     * Create an instance of {@link GetNextStepForBpelModelNamedResponse }
     * 
     */
    public GetNextStepForBpelModelNamedResponse createGetNextStepForBpelModelNamedResponse() {
        return new GetNextStepForBpelModelNamedResponse();
    }

    /**
     * Create an instance of {@link FinishtestCaseGenerationResponse }
     * 
     */
    public FinishtestCaseGenerationResponse createFinishtestCaseGenerationResponse() {
        return new FinishtestCaseGenerationResponse();
    }

    /**
     * Create an instance of {@link StartTestCaseGenForBpelModelNamed }
     * 
     */
    public StartTestCaseGenForBpelModelNamed createStartTestCaseGenForBpelModelNamed() {
        return new StartTestCaseGenForBpelModelNamed();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishtestCaseGeneration }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "finishtestCaseGeneration")
    public JAXBElement<FinishtestCaseGeneration> createFinishtestCaseGeneration(FinishtestCaseGeneration value) {
        return new JAXBElement<FinishtestCaseGeneration>(_FinishtestCaseGeneration_QNAME, FinishtestCaseGeneration.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbortTestCaseGen }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "abortTestCaseGen")
    public JAXBElement<AbortTestCaseGen> createAbortTestCaseGen(AbortTestCaseGen value) {
        return new JAXBElement<AbortTestCaseGen>(_AbortTestCaseGen_QNAME, AbortTestCaseGen.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteTestResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "ExecuteTestResponse")
    public JAXBElement<ExecuteTestResponse> createExecuteTestResponse(ExecuteTestResponse value) {
        return new JAXBElement<ExecuteTestResponse>(_ExecuteTestResponse_QNAME, ExecuteTestResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTestCaseGenForBpelModelNamedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "startTestCaseGenForBpelModelNamedResponse")
    public JAXBElement<StartTestCaseGenForBpelModelNamedResponse> createStartTestCaseGenForBpelModelNamedResponse(StartTestCaseGenForBpelModelNamedResponse value) {
        return new JAXBElement<StartTestCaseGenForBpelModelNamedResponse>(_StartTestCaseGenForBpelModelNamedResponse_QNAME, StartTestCaseGenForBpelModelNamedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddExeResulttoTestReport }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "addExeResulttoTestReport")
    public JAXBElement<AddExeResulttoTestReport> createAddExeResulttoTestReport(AddExeResulttoTestReport value) {
        return new JAXBElement<AddExeResulttoTestReport>(_AddExeResulttoTestReport_QNAME, AddExeResulttoTestReport.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteTest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "ExecuteTest")
    public JAXBElement<ExecuteTest> createExecuteTest(ExecuteTest value) {
        return new JAXBElement<ExecuteTest>(_ExecuteTest_QNAME, ExecuteTest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddExeResulttoTestReportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "addExeResulttoTestReportResponse")
    public JAXBElement<AddExeResulttoTestReportResponse> createAddExeResulttoTestReportResponse(AddExeResulttoTestReportResponse value) {
        return new JAXBElement<AddExeResulttoTestReportResponse>(_AddExeResulttoTestReportResponse_QNAME, AddExeResulttoTestReportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteAllTestsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "executeAllTestsResponse")
    public JAXBElement<ExecuteAllTestsResponse> createExecuteAllTestsResponse(ExecuteAllTestsResponse value) {
        return new JAXBElement<ExecuteAllTestsResponse>(_ExecuteAllTestsResponse_QNAME, ExecuteAllTestsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AbortTestCaseGenResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "abortTestCaseGenResponse")
    public JAXBElement<AbortTestCaseGenResponse> createAbortTestCaseGenResponse(AbortTestCaseGenResponse value) {
        return new JAXBElement<AbortTestCaseGenResponse>(_AbortTestCaseGenResponse_QNAME, AbortTestCaseGenResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ExecuteAllTests }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "executeAllTests")
    public JAXBElement<ExecuteAllTests> createExecuteAllTests(ExecuteAllTests value) {
        return new JAXBElement<ExecuteAllTests>(_ExecuteAllTests_QNAME, ExecuteAllTests.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link StartTestCaseGenForBpelModelNamed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "startTestCaseGenForBpelModelNamed")
    public JAXBElement<StartTestCaseGenForBpelModelNamed> createStartTestCaseGenForBpelModelNamed(StartTestCaseGenForBpelModelNamed value) {
        return new JAXBElement<StartTestCaseGenForBpelModelNamed>(_StartTestCaseGenForBpelModelNamed_QNAME, StartTestCaseGenForBpelModelNamed.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNextStepForBpelModelNamedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "getNextStepForBpelModelNamedResponse")
    public JAXBElement<GetNextStepForBpelModelNamedResponse> createGetNextStepForBpelModelNamedResponse(GetNextStepForBpelModelNamedResponse value) {
        return new JAXBElement<GetNextStepForBpelModelNamedResponse>(_GetNextStepForBpelModelNamedResponse_QNAME, GetNextStepForBpelModelNamedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FinishtestCaseGenerationResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "finishtestCaseGenerationResponse")
    public JAXBElement<FinishtestCaseGenerationResponse> createFinishtestCaseGenerationResponse(FinishtestCaseGenerationResponse value) {
        return new JAXBElement<FinishtestCaseGenerationResponse>(_FinishtestCaseGenerationResponse_QNAME, FinishtestCaseGenerationResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetNextStepForBpelModelNamed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://testCaseGen.lucas.unicamp.com/", name = "getNextStepForBpelModelNamed")
    public JAXBElement<GetNextStepForBpelModelNamed> createGetNextStepForBpelModelNamed(GetNextStepForBpelModelNamed value) {
        return new JAXBElement<GetNextStepForBpelModelNamed>(_GetNextStepForBpelModelNamed_QNAME, GetNextStepForBpelModelNamed.class, null, value);
    }

}
