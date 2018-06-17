package jossehblanco.com.gamenews3.VM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import jossehblanco.com.gamenews3.API.GameNewsRepo;
import jossehblanco.com.gamenews3.models.User;

/**
 * Created by UCA on 16/6/2018.
 */

public class UserVM extends AndroidViewModel {
    private GameNewsRepo usuarioRepositori;
    private LiveData<String> token;


    public void insert(User user){ usuarioRepositori.insertU(user);}


    public void initToken(String user,String password){

        if (this.token != null) {

            return;
        }

        token = usuarioRepositori.login(user,password);
    }

    public LiveData<String> getToken() {
        return token;
    }


    public LiveData<User> getUserDetail(String token) {

        return usuarioRepositori.getUserDetail(token);
    }

    public LiveData<User> getUser(){
        return usuarioRepositori.getUser();
    }


    public void deleteAll(){
        usuarioRepositori.deleteUser();
    }


    public UserVM(@NonNull Application application) {
        super(application);
        usuarioRepositori = new GameNewsRepo(application);
    }

}
