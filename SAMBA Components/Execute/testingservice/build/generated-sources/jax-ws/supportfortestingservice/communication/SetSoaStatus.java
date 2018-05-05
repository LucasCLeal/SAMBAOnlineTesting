
package supportfortestingservice.communication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for setSoaStatus complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="setSoaStatus">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="soaStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "setSoaStatus", propOrder = {
    "soaStatus"
})
public class SetSoaStatus {

    protected int soaStatus;

    /**
     * Gets the value of the soaStatus property.
     * 
     */
    public int getSoaStatus() {
        return soaStatus;
    }

    /**
     * Sets the value of the soaStatus property.
     * 
     */
    public void setSoaStatus(int value) {
        this.soaStatus = value;
    }

}
