package jossehblanco.com.gamenews3.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by Salomon Castro on 16/6/2018.
 */
@Entity(tableName = "player")
public class Player {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    private String _id;
    @ColumnInfo(name = "avater")
    private String avatar;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "bio")
    private String biografia;
    @ColumnInfo(name = "game")
    private String game;

    public Player(String _id, String avatar, String name, String biografia, String game) {
        this._id = _id;
        this.avatar = avatar;
        this.name = name;
        this.biografia = biografia;
        this.game = game;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
