
package testingservice.networkcommunication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for controlServiceHasChanged complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="controlServiceHasChanged">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="contrService" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="listaServizi" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "controlServiceHasChanged", propOrder = {
    "contrService",
    "listaServizi"
})
public class ControlServiceHasChanged {

    protected String contrService;
    protected String listaServizi;

    /**
     * Gets the value of the contrService property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContrService() {
        return contrService;
    }

    /**
     * Sets the value of the contrService property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContrService(String value) {
        this.contrService = value;
    }

    /**
     * Gets the value of the listaServizi property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getListaServizi() {
        return listaServizi;
    }

    /**
     * Sets the value of the listaServizi property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setListaServizi(String value) {
        this.listaServizi = value;
    }

}
