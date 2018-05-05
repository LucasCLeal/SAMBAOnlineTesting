
package testingservice.networkcommunication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getDependenciesPathForBPELName complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
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
     * Gets the value of the bpelName property.
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
     * Sets the value of the bpelName property.
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
