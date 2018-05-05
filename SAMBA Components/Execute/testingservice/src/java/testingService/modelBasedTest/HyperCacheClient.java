/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.modelBasedTest;

/**
 *
 * @author andrea
 */
public class HyperCacheClient {

    boolean result = false;

    public boolean performOperation(String operation, HyperTimeTableVariables var) {

        if (operation.equals("isValid")) {

            //try { // Call Web Service Operation
            //    fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromoManagerService service = new fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromoManagerService();
            //    fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromoManager port = service.getHyperPromoManagerPort();
            // TODO initialize WS operation arguments here
            // TODO process result here
            //    fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperCacheStatus result = port.isValid(promoId);
            //    System.out.println("Result = " + result);
            //} catch (Exception ex) {
            // TODO handle custom exceptions here
            //}

            //verificnaod variaveis em caso de teste controlado pelo TS
            if (var.getPromo() != null) {
                //java.lang.String promoId = var.getPromo();
                // TODO process result here
                //java.util.List<java.lang.String> result = port.shuffle(set);
                var.setStatus(var.getPromo());
                return true;
            } else {
                return false;
            }


        }
        if (operation.equals("synchronize")) {

            /*try { // Call Web Service Operation
            fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromoManagerService service = new fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromoManagerService();
            fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromoManager port = service.getHyperPromoManagerPort();
            // TODO initialize WS operation arguments here
            java.lang.String promoId = "";
            port.synchronize(promoId);
            } catch (Exception ex) {
            // TODO handle custom exceptions here
            }*/

            //verificnaod variaveis em caso de teste controlado pelo TS
            if (var.getPromo() != null) {
                return true;
            } else {
                return false;
            }


        }
        if (operation.equals("get")) {

            /*try { // Call Web Service Operation
            fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromoManagerService service = new fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromoManagerService();
            fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromoManager port = service.getHyperPromoManagerPort();
            // TODO initialize WS operation arguments here
            java.lang.String promoId = "";
            // TODO process result here
            fr.unice.i3s.modalis.jseduite.ws.technical.hypermachin.HyperPromo result = port.get(promoId);
            System.out.println("Result = " + result);
            } catch (Exception ex) {
            // TODO handle custom exceptions here
            }*/

            //verificnaod variaveis em caso de teste controlado pelo TS
            if (var.getPromo() != null) {
                var.setHyperpromo(var.getPromo());
                return true;
            } else {
                return false;
            }



        }

        return result;

    }
}
