
package fr.unice.i3s.modalis.jseduite.technical.registry.partners;

import javax.xml.ws.WebFault;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.3.3-hudson-757-SNAPSHOT
 * Generated source version: 2.1
 * 
 */
@WebFault(name = "PartnerKeysException", targetNamespace = "http://partners.registry.technical.jSeduite.modalis.i3s.unice.fr/")
public class PartnerKeysException_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private PartnerKeysException faultInfo;

    /**
     * 
     * @param message
     * @param faultInfo
     */
    public PartnerKeysException_Exception(String message, PartnerKeysException faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param message
     * @param faultInfo
     * @param cause
     */
    public PartnerKeysException_Exception(String message, PartnerKeysException faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKeysException
     */
    public PartnerKeysException getFaultInfo() {
        return faultInfo;
    }

}
