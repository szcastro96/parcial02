package jossehblanco.com.gamenews3.DB.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import jossehblanco.com.gamenews3.models.New;

/**
 * Created by UCA on 16/6/2018.
 */
@Dao
public interface NewDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(New... news);

    @Query("DELETE FROM new")
    void deleteAll();

    @Query("SELECT * FROM new")
    LiveData<List<New>> getAllNoticias();

    @Query("SELECT * FROM new WHERE game = :game")
    List<New> getNoticiaByGame(String game);

    @Query("UPDATE new SET fav = :isfav WHERE id = :id")
    void setNoticiaFav(Boolean isfav, String id);

    @Query("SELECT * FROM new WHERE id = :id")
    New getNoticia(String id);

}
