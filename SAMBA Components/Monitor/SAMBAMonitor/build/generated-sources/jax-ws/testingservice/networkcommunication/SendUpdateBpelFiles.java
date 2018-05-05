
package testingservice.networkcommunication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendUpdateBpelFiles complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendUpdateBpelFiles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="machineIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameBpelServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="bpelFile" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendUpdateBpelFiles", propOrder = {
    "machineIP",
    "nameBpelServer",
    "bpelFile"
})
public class SendUpdateBpelFiles {

    protected String machineIP;
    protected String nameBpelServer;
    protected String bpelFile;

    /**
     * Gets the value of the machineIP property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMachineIP() {
        return machineIP;
    }

    /**
     * Sets the value of the machineIP property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMachineIP(String value) {
        this.machineIP = value;
    }

    /**
     * Gets the value of the nameBpelServer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNameBpelServer() {
        return nameBpelServer;
    }

    /**
     * Sets the value of the nameBpelServer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNameBpelServer(String value) {
        this.nameBpelServer = value;
    }

    /**
     * Gets the value of the bpelFile property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBpelFile() {
        return bpelFile;
    }

    /**
     * Sets the value of the bpelFile property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBpelFile(String value) {
        this.bpelFile = value;
    }

}
