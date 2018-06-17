package jossehblanco.com.gamenews3.VM;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import jossehblanco.com.gamenews3.API.GameNewsRepo;
import jossehblanco.com.gamenews3.models.Player;

/**
 * Created by UCA on 16/6/2018.
 */

public class PlayerVM extends AndroidViewModel {
    private GameNewsRepo userRepository;
    public PlayerVM(@NonNull Application application) {
        super(application);
        userRepository = new GameNewsRepo(application);
    }




    public LiveData<List<Player>> getPlayerByGame(String game){
        return userRepository.getPlayersByGame(game);
    }

    public void updatePlayerDB(String token, String game){
        userRepository.getAllPlayersAndInsert(token, game);
    }

    public void deleteAll(){
        userRepository.deletePlayers();
    }

}
