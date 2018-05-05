/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package testingService.modelBasedTest;

/**
 *
 * @author andrea
 */
public class RssReaderClient {

    boolean result = false;

    public boolean performOperation(String operation, FeedReaderVariables var) {

        if (operation.equals("read")) {


            /*try { // Call Web Service Operation
                fr.unice.i3s.modalis.jseduite.technical.feeds.RssReaderService service = new fr.unice.i3s.modalis.jseduite.technical.feeds.RssReaderService();
                fr.unice.i3s.modalis.jseduite.technical.feeds.RssReader port = service.getRssReaderPort();
                // TODO initialize WS operation arguments here
                java.lang.String url = "";
                // TODO process result here
                fr.unice.i3s.modalis.jseduite.technical.feeds.FeedContent result = port.read(url);
                System.out.println("Result = "+result);
            } catch (Exception ex) {
                // TODO handle custom exceptions here
            }*/

            
            if (var.getUrl() != null) {
                //java.lang.String promoId = var.getPromo();
                // TODO process result here
                //java.util.List<java.lang.String> result = port.shuffle(set);
                var.setResult(var.getUrl());
                return true;
            } else {
                return false;
            }

        }
        return result;
    }


}
