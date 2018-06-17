package jossehblanco.com.gamenews3.DB;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import jossehblanco.com.gamenews3.DB.DAO.NewDAO;
import jossehblanco.com.gamenews3.DB.DAO.PlayerDAO;
import jossehblanco.com.gamenews3.DB.DAO.UserDAO;
import jossehblanco.com.gamenews3.models.New;
import jossehblanco.com.gamenews3.models.Player;
import jossehblanco.com.gamenews3.models.User;

/**
 * Created by Salomon Castro on 16/6/2018.
 */
@Database(entities = {User.class, New.class, Player.class}, version = 1, exportSchema = false)
public abstract class RoomGameNewsDB extends RoomDatabase{

    public abstract UserDAO userDAO();
    public abstract NewDAO newDAO();
    public abstract PlayerDAO playerDAO();

    private static RoomGameNewsDB INSTANCE;

    public static RoomGameNewsDB getDatabase(final Context ctx){

        if(INSTANCE == null){
            synchronized (RoomGameNewsDB.class){

                if(INSTANCE == null){

                    INSTANCE = Room.databaseBuilder(ctx.getApplicationContext(),RoomGameNewsDB.class, "gamenews_db").build();
                }
            }
        }

        return INSTANCE;
    }

    /*public LiveData<List<New>> getAllNewsAndInsert(String token) {

        Call<List<New>> call = gameNewsApi.getNewsAll("Bearer " + token);
        call.enqueue(new Callback<List<New>>() {

            @Override
            public void onResponse(Call<List<New>> call, retrofit2.Response<List<New>> response) {

                if (response.isSuccessful()) {

                    if (response.body() != null) {

                        new insertNAsyncTask(noticiaDao).execute(response.body().toArray(new New[response.body().size()]));
                    }

                } else {
                    Log.d("Error", "no succesful");
                }
            }

            @Override
            public void onFailure(Call<List<New>> call, Throwable t) {

            }
        });

        return Noticias;
    }*/

}
