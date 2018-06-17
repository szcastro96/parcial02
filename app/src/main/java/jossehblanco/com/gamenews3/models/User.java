package jossehblanco.com.gamenews3.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by UCA on 15/6/2018.
 */
@Entity(tableName = "user")
public class User {
    @ColumnInfo(name="favorite_news")
    private String favoriteNews;
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String _id;
    @NonNull
    @ColumnInfo(name = "user_name")
    private String user;
    @NonNull
    @ColumnInfo(name = "user_password")
    private String password;
    @NonNull
    @ColumnInfo(name = "created_date")
    private String created_date;
    public User(){

    }

    public User(String favoriteNews, String _id, String user, String password, String created_date) {
        this.favoriteNews = favoriteNews;
        this._id = _id;
        this.user = user;
        this.password = password;
        this.created_date = created_date;
    }

    public String getFavoriteNews() {
        return favoriteNews;
    }

    public void setFavoriteNews(String favoriteNews) {
        this.favoriteNews = favoriteNews;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCreated_date() {
        return created_date;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
