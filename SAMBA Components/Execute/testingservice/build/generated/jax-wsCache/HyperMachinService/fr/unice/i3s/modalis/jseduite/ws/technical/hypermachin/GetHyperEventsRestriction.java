
package fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getHyperEventsRestriction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getHyperEventsRestriction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="promo" type="{http://modalis.i3s.unice.fr/jSeduite/ws/technical/hypermachin}hyperPromo" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getHyperEventsRestriction", propOrder = {
    "promo"
})
public class GetHyperEventsRestriction {

    protected HyperPromo promo;

    /**
     * Gets the value of the promo property.
     * 
     * @return
     *     possible object is
     *     {@link HyperPromo }
     *     
     */
    public HyperPromo getPromo() {
        return promo;
    }

    /**
     * Sets the value of the promo property.
     * 
     * @param value
     *     allowed object is
     *     {@link HyperPromo }
     *     
     */
    public void setPromo(HyperPromo value) {
        this.promo = value;
    }

}
