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
    private GameNewsRepo userRepository;
    private LiveData<String> token;


    public void insert(User user){ userRepository.insertU(user);}

    public void initToken(String user,String password){
        if (this.token != null) {
            // ViewModel is created per Fragment so
            // we know the userId won't change
            return;
        }
        token = userRepository.login(user,password);
    }

    public LiveData<String> getToken() {
        return token;
    }

    public LiveData<User> getUserDetail(String token) {
        return userRepository.getUserDetail(token);
    }

    public LiveData<User> getUser(){
        return userRepository.getUser();
    }

    public void deleteAll(){
        userRepository.deleteUser();
    }


    public UserVM(@NonNull Application application) {
        super(application);
        userRepository = new GameNewsRepo(application);
    }

}
