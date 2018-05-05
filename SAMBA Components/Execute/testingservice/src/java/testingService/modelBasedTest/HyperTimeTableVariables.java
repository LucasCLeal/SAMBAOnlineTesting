/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package testingService.modelBasedTest;

/**
 *
 * @author andrea
 */
public class HyperTimeTableVariables {

    //hyperpromo
    //status
    //result
    //promo
    //variables used in in the business process
    private String promo = null;
    private String hyperpromo = null;
    private String result = null;
    private String status = null;

    void resetVariables() {
        promo = null;
        hyperpromo = null;
        result = null;
        status = null;
    }

    void bpelStartAssingVariables() {
        promo = "test";
    }

    //getters!!!

    public String getPromo(){
        return promo;
    }

    public String getHyperpromo(){
        return hyperpromo;
    }

    public String getResult(){
        return result;
    }

    public String getStatus(){
        return status;
    }

    //SETTERS!!!

    public void setPromo(String value){
        promo = value;
    }
    public void setHyperpromo(String value){
        hyperpromo = value;
    }
    public void setResult(String value){
        result = value;
    }
    public void setStatus(String value){
        status = value;
    }


}
