/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.modelBasedTest;

/**
 *
 * @author andrea
 */
public class HyperReaderClient {

    boolean result = false;

    public boolean performOperation(String operation, HyperTimeTableVariables var) {

        if (operation.equals("getHyperEvents")) {

            /*try { // Call Web Service Operation
            fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperMachinService service = new fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperMachinService();
            fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperMachin port = service.getHyperMachinPort();
            // TODO initialize WS operation arguments here
            fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromo promo = new fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromo();
            // TODO process result here
            java.util.List<fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperEvent> result = port.getHyperEvents(promo);
            System.out.println("Result = "+result);
            } catch (Exception ex) {
            // TODO handle custom exceptions here
            }*/

            //verificnaod variaveis em caso de teste controlado pelo TS
            if (var.getHyperpromo() != null) {
                return true;
            } else {
                return false;
            }

        }

        return result;

    }
}
