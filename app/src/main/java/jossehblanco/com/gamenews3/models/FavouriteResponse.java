package jossehblanco.com.gamenews3.models;

/**
 * Created by UCA on 15/6/2018.
 */

public class FavouriteResponse {
    private String success;
    public FavouriteResponse(String success){
        this.success = success;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }
}
