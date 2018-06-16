package jossehblanco.com.gamenews3.API;

import java.util.List;

import jossehblanco.com.gamenews3.models.FavouriteResponse;
import jossehblanco.com.gamenews3.models.New;
import jossehblanco.com.gamenews3.models.Player;
import jossehblanco.com.gamenews3.models.Token;
import jossehblanco.com.gamenews3.models.User;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HEAD;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by UCA on 4/6/2018.
 */

public interface ServiceNews {
    //Metodo para crear el login
    @FormUrlEncoded
    @POST("/login")
    Call<Token> login(@Field("user") String user, @Field("password") String password);

    //metodo para obtener la lista de noticias del API
    @GET("/news")
    Call<List<New>> getNewsAll(@Header("Authorization") String codigo);

    //Noticias por categoria
    @GET("/news/type/{game}")
    Call<List<New>> getNewsTyped(@Header("Authorization") String codigo, @Path(value="game") String game);

    //Metodo para agregar noticia a favoritos
    @POST("/user/fav/{idNew}")
    Call<FavouriteResponse> addFavourite(@Header("Authorization") String codigo, @Path(value="idNew") String idNew);

    //Metodo para obtener informacion de un usuario

    @GET("/users/detail")
    Call<User> getUserDetail(@Header("Authorization") String codigo);

    @GET("/news/{id}")
    Call<New> getNewDetail(@Header("Authorization") String codigo, @Path(value="id") String id);

    @GET("/players/type/{game}")
    Call<List<Player>> getPlayerByGame(@Header("Authorization") String codigo, @Path(value="game") String game);
}
