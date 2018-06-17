package jossehblanco.com.gamenews3.DB.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import jossehblanco.com.gamenews3.models.User;

/**
 * Created by UCA on 16/6/2018.
 */
@Dao
public interface UserDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("DELETE FROM user")
    void deleteAll();

    @Query("SELECT * from user")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * from user")
    LiveData<User> getUserDetail();

    @Query("UPDATE user SET favorite_news = :favs WHERE id = :id")
    void updateUserFavs (String favs,String id);
}
