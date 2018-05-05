/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.modelBasedTest;

/**
 *
 * @author Lucas Leal
 */
public class FlickerClient {

    boolean result = false;

    public boolean performOperation(String operation, ImageScraperVariables var) {

        if (operation.equals("getFolksonomyContent")) {

            try { // Call Web Service Operation
                fr.unice.i3s.modalis.jseduite.technical.image.flickr.FlickrWrapperService service = new fr.unice.i3s.modalis.jseduite.technical.image.flickr.FlickrWrapperService();
                fr.unice.i3s.modalis.jseduite.technical.image.flickr.FlickrWrapper port = service.getFlickrWrapperPort();
                // TODO initialize WS operation arguments here
                //verificando variaveis
                
                if (var.getKey() != null && var.getLimit() != null && var.getTags() != null) {
                    java.lang.String tags = var.getTags();
                    int count = var.getLimit();
                    java.lang.String key = var.getKey();
                    // TODO process result here
                    //java.util.List<java.lang.String> result = port.getFolksonomyContent(tags, count, key);

                    java.util.List<java.lang.String> list = new java.util.LinkedList();
                    list.add("three");
                    list.add("four");
                    var.setFlickr_star(list);

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