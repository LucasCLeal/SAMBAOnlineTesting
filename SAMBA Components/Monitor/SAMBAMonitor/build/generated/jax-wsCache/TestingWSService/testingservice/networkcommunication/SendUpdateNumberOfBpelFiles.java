
package testingservice.networkcommunication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sendUpdateNumberOfBpelFiles complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sendUpdateNumberOfBpelFiles">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="machineIP" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="nameBpelServer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberOfFile" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sendUpdateNumberOfBpelFiles", propOrder = {
    "machineIP",
    "nameBpelServer",
    "numberOfFile"
})
public class SendUpdateNumberOfBpelFiles {

    protected String machineIP;
    protected String nameBpelServer;
    protected int numberOfFile;

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
     * Gets the value of the numberOfFile property.
     * 
     */
    public int getNumberOfFile() {
        return numberOfFile;
    }

    /**
     * Sets the value of the numberOfFile property.
     * 
     */
    public void setNumberOfFile(int value) {
        this.numberOfFile = value;
    }

}
