
package testingservice.networkcommunication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de getDependenciesPathForBPELName complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
 * 
 * <pre>
 * &lt;complexType name="getDependenciesPathForBPELName">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="bpelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getDependenciesPathForBPELName", propOrder = {
    "bpelName"
})
public class GetDependenciesPathForBPELName {

    protected String bpelName;

    /**
     * Obtém o valor da propriedade bpelName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBpelName() {
        return bpelName;
    }

    /**
     * Define o valor da propriedade bpelName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBpelName(String value) {
        this.bpelName = value;
    }

}
