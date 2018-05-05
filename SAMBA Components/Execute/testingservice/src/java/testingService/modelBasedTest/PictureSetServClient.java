/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.modelBasedTest;

/**
 *
 * @author Lucas Leal
 */
public class PictureSetServClient {

    boolean result = false;

    public boolean performOperation(String operation, ImageScraperVariables var) {

        if (operation.equals("shuffle")) {

            try { // Call Web Service Operation
                fr.unice.i3s.modalis.jseduite.technical.image.helper.PictureSetService service = new fr.unice.i3s.modalis.jseduite.technical.image.helper.PictureSetService();
                fr.unice.i3s.modalis.jseduite.technical.image.helper.PictureSet port = service.getPictureSetPort();
                // TODO initialize WS operation arguments here
                
                
                //verificnaod variaveis em caso de teste controlado pelo TS
                if (var.getPicasa_star() != null || var.getFlickr_star() != null) {

                    if (var.getPicasa_star() != null) {
                        java.util.List<java.lang.String> set = var.getPicasa_star();
                        // TODO process result here
                        //java.util.List<java.lang.String> result = port.shuffle(set);
                        var.setPicasa_star(set);
                    }

                    if (var.getFlickr_star() != null) {
                        java.util.List<java.lang.String> set = var.getFlickr_star();
                        // TODO process result here
                        //java.util.List<java.lang.String> result = port.shuffle(set);
                        var.setFlickr_star(set);
                    }

                    return true;
                } else {
                    return false;
                }
                

            } catch (Exception ex) {
                return false;
            }

        }
        if (operation.equals("merge")) {
        //if (operation.equals("mix")) {
            try { // Call Web Service Operation
                fr.unice.i3s.modalis.jseduite.technical.image.helper.PictureSetService service = new fr.unice.i3s.modalis.jseduite.technical.image.helper.PictureSetService();
                fr.unice.i3s.modalis.jseduite.technical.image.helper.PictureSet port = service.getPictureSetPort();
                // TODO initialize WS operation arguments here
                
                
                //verificando variaveis
                //verificnaod variaveis em caso de teste controlado pelo TS
                
                if (var.getFlickr_star() != null || var.getPicasa_star() != null) {

                    java.util.List<java.lang.String> first = var.getFlickr_star();
                    java.util.List<java.lang.String> second = var.getPicasa_star();
                    // TODO process result here
                    //java.util.List<java.lang.String> result = port.merge(first, second);
                    java.util.List<java.lang.String> list = new java.util.LinkedList();
                    list.add("picasa");
                    list.add("flickr");
                    var.setResult(list);
                    return true;
                } else {
                    return false;
                }


            } catch (Exception ex) {
                return false;
            }

        }
        if (operation.equals("truncate")) {

            try { // Call Web Service Operation
                fr.unice.i3s.modalis.jseduite.technical.image.helper.PictureSetService service = new fr.unice.i3s.modalis.jseduite.technical.image.helper.PictureSetService();
                fr.unice.i3s.modalis.jseduite.technical.image.helper.PictureSet port = service.getPictureSetPort();
                // TODO initialize WS operation arguments here

                
                if (var.getLimit() != null && var.getResult() != null) {
                    java.util.List<java.lang.String> set = var.getResult();
                    int count = var.getLimit();
                    // TODO process result here
                    //java.util.List<java.lang.String> result = port.truncate(set, count);
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
