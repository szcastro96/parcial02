package jossehblanco.com.gamenews3.DB.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import jossehblanco.com.gamenews3.models.Player;

/**
 * Created by Salomon Castro on 16/6/2018.
 */

@Dao
public interface PlayerDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Player... players);

    @Query("DELETE FROM player")
    void deleteAll();

    @Query("SELECT * FROM player WHERE game = :game")
    List<Player> getAllPlayersByGame(String game);
}