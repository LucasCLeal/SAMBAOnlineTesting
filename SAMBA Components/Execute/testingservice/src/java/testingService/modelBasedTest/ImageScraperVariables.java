/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.modelBasedTest;

/**
 *
 * @author andrea
 */
public class ImageScraperVariables {

    //variables used in in the business process
    private String key = null;
    private java.util.List<java.lang.String> flickr_star = null;
    private java.util.List<java.lang.String> picasa_star = null;
    private java.util.List<java.lang.String> result = null;
    private Integer limit = null;
    private String tags = null;

    public void resetVariables() {
        key = null;
        flickr_star = null;
        picasa_star = null;
        result = null;
        limit = null;
        tags = null;
    }

    public void bpelStartAssingVariables() {
        tags = "tags";
        limit = 10;
    }

    //getters
    public String getKey() {
        return key;
    }

    public java.util.List<java.lang.String> getFlickr_star() {
        return flickr_star;
    }

    public java.util.List<java.lang.String> getPicasa_star() {
        return picasa_star;
    }

    public java.util.List<java.lang.String> getResult() {
        return result;
    }

    public Integer getLimit() {
        return limit;
    }

    public String getTags() {
        return tags;
    }

    //setters
    public void setKey(String value) {
        key = value;
    }

    public void setFlickr_star(java.util.List<java.lang.String> value) {
        flickr_star = value;
    }

    public void setPicasa_star(java.util.List<java.lang.String> value) {
        picasa_star = value;
    }

    public void setResult(java.util.List<java.lang.String> value) {
        result = value;
    }

    public void setLimit(Integer value) {
        limit = value;
    }

    public void setTags(String value) {
        tags = value;
    }
}
