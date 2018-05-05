
package com.unicamp.lucas.testcasegen;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de ExecuteTest complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="ExecuteTest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orchestrationFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExecuteTest", propOrder = {
    "orchestrationFileName"
})
public class ExecuteTest {

    protected String orchestrationFileName;

    /**
     * Obtém o valor da propriedade orchestrationFileName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrchestrationFileName() {
        return orchestrationFileName;
    }

    /**
     * Define o valor da propriedade orchestrationFileName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrchestrationFileName(String value) {
        this.orchestrationFileName = value;
    }

}
