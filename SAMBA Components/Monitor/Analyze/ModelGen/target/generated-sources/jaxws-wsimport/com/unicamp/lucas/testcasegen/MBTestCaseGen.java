
package com.unicamp.lucas.testcasegen;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.8
 * Generated source version: 2.2
 * 
 */
@WebService(name = "MBTestCaseGen", targetNamespace = "http://testCaseGen.lucas.unicamp.com/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface MBTestCaseGen {


    /**
     * 
     * @return
     *     returns java.lang.String
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getNextStepForBpelModelNamed", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.GetNextStepForBpelModelNamed")
    @ResponseWrapper(localName = "getNextStepForBpelModelNamedResponse", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.GetNextStepForBpelModelNamedResponse")
    @Action(input = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/getNextStepForBpelModelNamedRequest", output = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/getNextStepForBpelModelNamedResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/getNextStepForBpelModelNamed/Fault/IOException")
    })
    public String getNextStepForBpelModelNamed()
        throws IOException_Exception
    ;

    /**
     * 
     * @param result
     * @param test
     * @throws IOException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "addExeResulttoTestReport", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.AddExeResulttoTestReport")
    @ResponseWrapper(localName = "addExeResulttoTestReportResponse", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.AddExeResulttoTestReportResponse")
    @Action(input = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/addExeResulttoTestReportRequest", output = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/addExeResulttoTestReportResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/addExeResulttoTestReport/Fault/IOException")
    })
    public void addExeResulttoTestReport(
        @WebParam(name = "test", targetNamespace = "")
        String test,
        @WebParam(name = "result", targetNamespace = "")
        boolean result)
        throws IOException_Exception
    ;

    /**
     * 
     * @param testeExeData
     * @throws IOException_Exception
     */
    @WebMethod
    @RequestWrapper(localName = "finishtestCaseGeneration", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.FinishtestCaseGeneration")
    @ResponseWrapper(localName = "finishtestCaseGenerationResponse", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.FinishtestCaseGenerationResponse")
    @Action(input = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/finishtestCaseGenerationRequest", output = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/finishtestCaseGenerationResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/finishtestCaseGeneration/Fault/IOException")
    })
    public void finishtestCaseGeneration(
        @WebParam(name = "testeExeData", targetNamespace = "")
        String testeExeData)
        throws IOException_Exception
    ;

    /**
     * 
     * @param testeExeData
     */
    @WebMethod
    @RequestWrapper(localName = "abortTestCaseGen", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.AbortTestCaseGen")
    @ResponseWrapper(localName = "abortTestCaseGenResponse", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.AbortTestCaseGenResponse")
    @Action(input = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/abortTestCaseGenRequest", output = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/abortTestCaseGenResponse")
    public void abortTestCaseGen(
        @WebParam(name = "testeExeData", targetNamespace = "")
        String testeExeData);

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "executeAllTests", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.ExecuteAllTests")
    @ResponseWrapper(localName = "executeAllTestsResponse", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.ExecuteAllTestsResponse")
    @Action(input = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/executeAllTestsRequest", output = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/executeAllTestsResponse")
    public void executeAllTests();

    /**
     * 
     * @param fileName
     * @return
     *     returns boolean
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "startTestCaseGenForBpelModelNamed", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.StartTestCaseGenForBpelModelNamed")
    @ResponseWrapper(localName = "startTestCaseGenForBpelModelNamedResponse", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.StartTestCaseGenForBpelModelNamedResponse")
    @Action(input = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/startTestCaseGenForBpelModelNamedRequest", output = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/startTestCaseGenForBpelModelNamedResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/startTestCaseGenForBpelModelNamed/Fault/IOException")
    })
    public boolean startTestCaseGenForBpelModelNamed(
        @WebParam(name = "fileName", targetNamespace = "")
        String fileName)
        throws IOException_Exception
    ;

    /**
     * 
     * @param orchestrationFileName
     * @throws IOException_Exception
     */
    @WebMethod(operationName = "ExecuteTest")
    @RequestWrapper(localName = "ExecuteTest", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.ExecuteTest")
    @ResponseWrapper(localName = "ExecuteTestResponse", targetNamespace = "http://testCaseGen.lucas.unicamp.com/", className = "com.unicamp.lucas.testcasegen.ExecuteTestResponse")
    @Action(input = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/ExecuteTestRequest", output = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/ExecuteTestResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://testCaseGen.lucas.unicamp.com/MBTestCaseGen/ExecuteTest/Fault/IOException")
    })
    public void executeTest(
        @WebParam(name = "orchestrationFileName", targetNamespace = "")
        String orchestrationFileName)
        throws IOException_Exception
    ;

}
