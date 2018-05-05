/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.modelBasedTest;

/**
 *
 * @author andrea
 */
public class FeedReaderVariables {

    private String url = null;
    private String result = null;
    private String feedName = null;

    void resetVariables() {
        url = null;
        result = null;
        feedName = null;
    }

    void bpelStartAssingVariables() {
        feedName = "test";
    }

    //GETTERS
    public String getFeedName() {
        return feedName;
    }

    public String getUrl() {
        return url;
    }

    public String getResult() {
        return result;
    }
    //SETTERS

    public void setFeedname(String value) {
        feedName = value;
    }

    public void setUrl(String value) {
        url = value;
    }

    public void setResult(String value) {
        result = value;
    }
}
