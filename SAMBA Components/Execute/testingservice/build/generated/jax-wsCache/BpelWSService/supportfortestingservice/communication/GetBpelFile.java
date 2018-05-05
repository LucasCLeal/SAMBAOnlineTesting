
package supportfortestingservice.communication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getBpelFile complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getBpelFile">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="requiredFileNumber" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getBpelFile", propOrder = {
    "requiredFileNumber"
})
public class GetBpelFile {

    protected int requiredFileNumber;

    /**
     * Gets the value of the requiredFileNumber property.
     * 
     */
    public int getRequiredFileNumber() {
        return requiredFileNumber;
    }

    /**
     * Sets the value of the requiredFileNumber property.
     * 
     */
    public void setRequiredFileNumber(int value) {
        this.requiredFileNumber = value;
    }

}
