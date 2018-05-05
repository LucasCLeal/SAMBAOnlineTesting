
package testingservice.networkcommunication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de sendUpdateNumberOfBpelFiles complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
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
     * Obtém o valor da propriedade machineIP.
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
     * Define o valor da propriedade machineIP.
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
     * Obtém o valor da propriedade nameBpelServer.
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
     * Define o valor da propriedade nameBpelServer.
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
     * Obtém o valor da propriedade numberOfFile.
     * 
     */
    public int getNumberOfFile() {
        return numberOfFile;
    }

    /**
     * Define o valor da propriedade numberOfFile.
     * 
     */
    public void setNumberOfFile(int value) {
        this.numberOfFile = value;
    }

}
