
package testingservice.networkcommunication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java de controlServiceHasChanged complex type.
 * 
 * <p>O seguinte fragmento do esquema especifica o conteúdo esperado contido dentro desta classe.
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
     * Obtém o valor da propriedade contrService.
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
     * Define o valor da propriedade contrService.
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
     * Obtém o valor da propriedade listaServizi.
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
     * Define o valor da propriedade listaServizi.
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
