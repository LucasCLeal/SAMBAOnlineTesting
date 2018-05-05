
package testingservice.networkcommunication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de sendUpdateBpelFiles complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
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
     * Obtém o valor da propriedade bpelFile.
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
     * Define o valor da propriedade bpelFile.
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
