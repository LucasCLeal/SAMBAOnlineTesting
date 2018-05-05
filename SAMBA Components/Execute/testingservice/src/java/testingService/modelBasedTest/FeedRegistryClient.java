/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testingService.modelBasedTest;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import javax.naming.spi.DirStateFactory.Result;

/**
 *
 * @author andrea
 */
public class FeedRegistryClient {

    boolean result = false;

    public boolean performOperation(String operation, FeedReaderVariables var) {

        if (operation.equals("getURL")) {

            /*try { // Call Web Service Operation
                fr.unice.i3s.modalis.jseduite.technical.registry.FeedRegistryService service = new fr.unice.i3s.modalis.jseduite.technical.registry.FeedRegistryService();
                fr.unice.i3s.modalis.jseduite.technical.registry.FeedRegistry port = service.getFeedRegistryPort();
                // TODO initialize WS operation arguments here
                java.lang.String name = "";
                // TODO process result here
                java.lang.String result = port.getURL(name);
                System.out.println("Result = "+result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }*/



            if (var.getFeedName() != null) {
                //java.lang.String promoId = var.getPromo();
                // TODO process result here
                //java.util.List<java.lang.String> result = port.shuffle(set);
                var.setUrl(var.getFeedName());
                return true;
            } else {
                return false;
            }

        }
        return result;
    }


}
