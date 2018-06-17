package jossehblanco.com.gamenews3.VM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import jossehblanco.com.gamenews3.API.GameNewsRepo;
import jossehblanco.com.gamenews3.models.New;

/**
 * Created by UCA on 16/6/2018.
 */

public class NewVM extends AndroidViewModel {
    private GameNewsRepo userRepository;

    public LiveData<List<New>> getAllnoticias(String token) {
        return userRepository.getAllNewsAndInsert(token);
    }

    public void setFavoritos(String token,String userId,String noticiaId,String favs){
        userRepository.setFavoritos(token,userId,noticiaId,favs);
    }
    /*
    public void deleteFavoritos(String token,String userId,String noticiaId,String favs){
        userRepository.deleteFavoritos(token,userId,noticiaId,favs);
    }*/

    public LiveData<New> getNewDetail(String token,String idNoticia){
        return userRepository.getNewDetailDB(token, idNoticia);
    }

    public LiveData<List<New>> getNewByGameDB(String game){
        return userRepository.getNewByGameFromDB(game);
    }
/*
    public LiveData<String []> getCategorias(String token){
        System.out.println("NOTICIA VM INGRESA TOKEN: " + token);
        return userRepository.getCategorias(token);
    }*/

    public void deleteAll(){
        userRepository.deleteAllNoticias();
    }

    public NewVM(@NonNull Application application) {
        super(application);
        userRepository = new GameNewsRepo(application);
    }


}
