package jossehblanco.com.gamenews3.models;

/**
 * Created by UCA on 13/6/2018.
 */

public class New {
    private String _id, title, description, coverImage, body, game,  created_date;

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

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCoverImage(String coverimage) {
        this.coverImage = coverimage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreated_date(String created_date) {
        this.created_date = created_date;
    }
}
