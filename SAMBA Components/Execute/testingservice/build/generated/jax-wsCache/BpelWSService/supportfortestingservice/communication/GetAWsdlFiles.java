
package supportfortestingservice.communication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getAWsdlFiles complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getAWsdlFiles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="wsdlFileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAWsdlFiles", propOrder = {
    "wsdlFileName"
})
public class GetAWsdlFiles {

    protected String wsdlFileName;

    /**
     * Gets the value of the wsdlFileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWsdlFileName() {
        return wsdlFileName;
    }

    /**
     * Sets the value of the wsdlFileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWsdlFileName(String value) {
        this.wsdlFileName = value;
    }

}
