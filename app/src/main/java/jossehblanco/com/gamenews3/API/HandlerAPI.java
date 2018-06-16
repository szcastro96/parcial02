package jossehblanco.com.gamenews3.API;

import android.support.annotation.Nullable;

import java.util.List;

import jossehblanco.com.gamenews3.models.FavouriteResponse;
import jossehblanco.com.gamenews3.models.New;
import jossehblanco.com.gamenews3.models.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by UCA on 12/6/2018.
 */

public class HandlerAPI {
    private ServiceNews serviceNews;
    private String BASE_URL = "http://gamenewsuca.herokuapp.com";
    private String loginToken;
    private String favouriteResponse;
    private String BEARER_TOKEN;
    private List<New> news;
    public HandlerAPI(String token){
        serviceNews = new RetrofitClient().getClient(BASE_URL).create(ServiceNews.class);
        this.BEARER_TOKEN = "Bearer " + token;
    }
    public HandlerAPI(){
        serviceNews = new RetrofitClient().getClient(BASE_URL).create(ServiceNews.class);
    }


    public String getLoginToken(String user, String pass){
        serviceNews.login(user, pass).enqueue(new Callback<Token>() {
            public String requestResponse;
            @Override
            public void onResponse(Call<Token> call, Response<Token> response) {
                if(response.isSuccessful()){
                    loginToken = response.body().getToken();
                }
            }
            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                loginToken = "ella";
            }
        }
        );
        System.out.println(user + "," + pass + ""+ loginToken);
        return loginToken;
    }

    public String getFavouriteResponse(String idNew){
        serviceNews.addFavourite(BEARER_TOKEN, idNew).enqueue(new Callback<FavouriteResponse>() {
            @Override
            public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
                favouriteResponse = response.body().getSuccess();
            }

            @Override
            public void onFailure(Call<FavouriteResponse> call, Throwable t) {

            }
        });
        return favouriteResponse;
    }

    public List<New> getGeneralNews(){
        serviceNews.getNewsLoL(BEARER_TOKEN).enqueue(new Callback<List<New>>() {
            @Override
            public void onResponse(Call<List<New>> call, Response<List<New>> response) {
                news = response.body();
            }

            @Override
            public void onFailure(Call<List<New>> call, Throwable t) {

            }
        });
        return news;
    }
}
