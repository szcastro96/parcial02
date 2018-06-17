package jossehblanco.com.gamenews3.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Salomon Castro on 13/6/2018.
 */
@Entity(tableName = "new")
public class New {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String _id;
    @NonNull
    @ColumnInfo(name = "title")
    private String title;
    @NonNull
    @ColumnInfo(name = "description")
    private String description;
    @ColumnInfo(name = "coverImage")
    private String coverImage;
    @ColumnInfo(name = "body")
    private String body;
    @ColumnInfo(name = "game")
    private String game;
    @ColumnInfo(name = "created_date")
    private String created_date;
    @ColumnInfo(name = "fav")
    private Boolean fav = false;

    public Boolean getFav() {
        return fav;
    }

    public void setFav(Boolean fav) {
        this.fav = fav;
    }

    public New(String _id, String title, String description, String coverImage, String body, String game, String created_date){
        this._id = _id;
        this.title = title;
        this.description = description;
        this.coverImage = coverImage;
        this.body = body;
        this.game = game;
        this.created_date = created_date;
    }

    public String get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_date() {
        return created_date;
    }



    public void setTitle(String title) {
        this.title = title;
    }



    public String getBody() {
        return body;
    }

    

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
