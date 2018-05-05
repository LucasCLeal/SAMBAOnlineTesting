
package com.unicamp.lucas.testcasegen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de abortTestCaseGen complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="abortTestCaseGen">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="testeExeData" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "abortTestCaseGen", propOrder = {
    "testeExeData"
})
public class AbortTestCaseGen {

    protected String testeExeData;

    /**
     * Obtém o valor da propriedade testeExeData.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTesteExeData() {
        return testeExeData;
    }

    /**
     * Define o valor da propriedade testeExeData.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTesteExeData(String value) {
        this.testeExeData = value;
    }

}
