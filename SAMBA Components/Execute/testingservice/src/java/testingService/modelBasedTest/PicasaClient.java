/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.modelBasedTest;

import org.apache.commons.ssl.Java13;

/**
 *
 * @author Lucas Leal
 */
public class PicasaClient {

    boolean result = false;

    public boolean performOperation(String operation, ImageScraperVariables var) {

        if (operation.equals("getFolksonomyContent")) {

            try { // Call Web Service Operation
                fr.unice.i3s.modalis.jseduite.technical.image.picasa.PicasaWrapperService service = new fr.unice.i3s.modalis.jseduite.technical.image.picasa.PicasaWrapperService();
                fr.unice.i3s.modalis.jseduite.technical.image.picasa.PicasaWrapper port = service.getPicasaWrapperPort();
                // TODO initialize WS operation arguments here

                //verificando se variaveis ja foram carregadas
                
                if (var.getLimit() != null && var.getTags() != null) {
                    java.lang.String query = var.getTags();
                    int count = var.getLimit();
                    // TODO process result here
                    //java.util.List<java.lang.String> result = port.getFolksonomyContent(query, count);
                    java.util.List<java.lang.String> list = new java.util.LinkedList();
                    list.add("one");
                    list.add("two");
                    var.setPicasa_star(list);
                    return true;
                } else {
                    return false;
                }
                

            } catch (Exception ex) {
                return false;
            }
        }
        return result;
    }
}
