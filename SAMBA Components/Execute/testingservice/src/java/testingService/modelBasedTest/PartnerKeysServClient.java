/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testingService.modelBasedTest;

/**
 *
 * @author Lucas Leal
 */
public class PartnerKeysServClient {
    boolean result = false;

    public boolean performOperation(String operation, ImageScraperVariables var){

        if(operation.equals("get")){

            try { // Call Web Service Operation
                fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKeysService service = new fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKeysService();
                fr.unice.i3s.modalis.jseduite.technical.registry.partners.PartnerKeys port = service.getPartnerKeysPort();
                // TODO initialize WS operation arguments here
                //carregando variaveis

                java.lang.String partner = "flickr";
                // TODO process result here
                //java.lang.String result = port.get(partner);
                var.setKey("password");
                return true;
            } catch (Exception ex) {
                // TODO handle custom exceptions here
                return false;
            }

        }
        return result;
    }

}