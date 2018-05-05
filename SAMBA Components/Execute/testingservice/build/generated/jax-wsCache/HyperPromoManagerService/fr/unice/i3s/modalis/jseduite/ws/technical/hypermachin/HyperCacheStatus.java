
package fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for hyperCacheStatus.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="hyperCacheStatus">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="VALID"/>
 *     &lt;enumeration value="OLD"/>
 *     &lt;enumeration value="SYNC"/>
 *     &lt;enumeration value="EMPTY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "hyperCacheStatus")
@XmlEnum
public enum HyperCacheStatus {

    VALID,
    OLD,
    SYNC,
    EMPTY;

    public String value() {
        return name();
    }

    public static HyperCacheStatus fromValue(String v) {
        return valueOf(v);
    }

}
