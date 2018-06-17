package jossehblanco.com.gamenews3.API;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.os.AsyncTask;
import android.util.Log;

import java.util.List;

import javax.inject.Singleton;

import jossehblanco.com.gamenews3.DB.DAO.NewDAO;
import jossehblanco.com.gamenews3.DB.DAO.PlayerDAO;
import jossehblanco.com.gamenews3.DB.DAO.UserDAO;
import jossehblanco.com.gamenews3.DB.RoomGameNewsDB;
import jossehblanco.com.gamenews3.models.FavouriteResponse;
import jossehblanco.com.gamenews3.models.New;
import jossehblanco.com.gamenews3.models.Player;
import jossehblanco.com.gamenews3.models.Token;
import jossehblanco.com.gamenews3.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by UCA on 16/6/2018.
 */

@Singleton
public class GameNewsRepo {
    private final NewDAO noticiaDao;
    private final UserDAO userDao;
    private final PlayerDAO playerDao;
    private ServiceNews gameNewsApi;
    private LiveData<List<New>> Noticias;
    private LiveData<User> User;
    private LiveData<Player> players;
    private final String BASE_URL = "http://gamenewsuca.herokuapp.com/";


    public GameNewsRepo(Application application) {
        RoomGameNewsDB db = RoomGameNewsDB.getDatabase(application);
        userDao = db.userDAO();
        noticiaDao = db.newDAO();
        playerDao = db.playerDAO();
        initAPI();
        Noticias = noticiaDao.getAllNoticias();
        User = userDao.getUserDetail();
    }

    private void initAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gameNewsApi = retrofit.create(ServiceNews.class);
    }

    public LiveData<String> login(String user, String password) {
        final MutableLiveData<String> token = new MutableLiveData<>();
        Call<Token> call = gameNewsApi.login(user, password);
        call.enqueue(new Callback<Token>() {
            @Override
            public void onResponse(Call<Token> call, retrofit2.Response<Token> response) {
                if (response.isSuccessful()) {
                    Log.d("Token", response.body().getToken());
                    token.setValue(response.body().getToken());
                } else {
                    Log.d("No pudo", "obtener token");
                }

            }

            @Override
            public void onFailure(Call<Token> call, Throwable t) {
                Log.d("Error", t.getMessage());
                Log.d("Error", "error");
            }
        });
        return token;
    }

    public LiveData<User> getUserDetail(String token) {
        final MutableLiveData<User> data = new MutableLiveData<>();
        final User user = new User();
        Call<User> call = gameNewsApi.getUserDetail("Bearer " + token);

        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                Log.d("USER ID USERDETAIL    ", response.body().get_id());
                user.setUser(response.body().getUser());
                user.set_id(response.body().get_id());
                user.setPassword(response.body().getPassword());
                user.setFavoriteNews(response.body().getFavoriteNews());
                data.setValue(user);
                new insertUAsyncTask(userDao).execute(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("Failure", t.getMessage());
                Log.d("call", call.request().toString());
            }
        });
        return data;
    }

    public LiveData<User> getUser() {
        return User;
    }
/*
    public void updateUser(String token, String userid, String pass){
        Call<User> call = gameNewsApi.modifyUser("Bearer "+token, userid, pass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, retrofit2.Response<User> response) {
                if (response.isSuccessful()){
                    System.out.println(response.isSuccessful());
                }
                else{
                    System.out.println("CALL  "+call.request().toString());
                    System.out.println("RESPONSE  "+response.message());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("ON FAILURE UPDATE" + t.getMessage());
            }
        });
    }*/

    private static class updateUserFavsBD extends AsyncTask<String, Void, Void> {

        UserDAO userDao;

        updateUserFavsBD(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            userDao.updateUserFavs(strings[0], strings[1]);
            return null;
        }
    }

    private static class deleteUser extends AsyncTask<Void, Void, Void> {

        UserDAO userDao;

        deleteUser(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            userDao.deleteAll();
            return null;
        }
    }

    public void deleteUser() {
        new deleteUser(userDao).execute();
    }


    private static class insertUAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDAO userDao;

        insertUAsyncTask(UserDAO userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(User... users) {
            userDao.insert(users[0]);
            Log.d("INSERT ", " SUCCESFUL");
            return null;
        }
    }

    public void insertU(User user) {
        new insertUAsyncTask(userDao).execute(user);
    }

    /*
    *
    *
    *NOTICIAS ACCESS
    *
    *
     */

    private static class insertNAsyncTask extends AsyncTask<New, Void, Void> {

        private NewDAO noticiaDao;

        insertNAsyncTask(NewDAO noticiaDao) {
            this.noticiaDao = noticiaDao;
        }

        @Override
        protected Void doInBackground(New... noticias) {
            noticiaDao.insert(noticias);
            return null;
        }
    }

    private static class FavDB extends AsyncTask<String, Void, Void> {
        NewDAO noticiaDao;

        FavDB(NewDAO noticiaDao) {
            this.noticiaDao = noticiaDao;
        }

        @Override
        protected Void doInBackground(String... strings) {
            noticiaDao.setNoticiaFav(Boolean.parseBoolean(strings[0]), strings[1]);
            return null;
        }
    }

    private static class getNewByGameDB extends AsyncTask<String, Void, List<New>> {
        NewDAO noticiaDao;
        MutableLiveData<List<New>> data;

        getNewByGameDB(NewDAO noticiaDao, MutableLiveData<List<New>> data) {
            this.noticiaDao = noticiaDao;
            this.data = data;
        }

        @Override
        protected List<New> doInBackground(String... strings) {
            return noticiaDao.getNoticiaByGame(strings[0]);
        }

        @Override
        protected void onPostExecute(List<New> listLiveData) {
            super.onPostExecute(listLiveData);
            data.setValue(listLiveData);
        }
    }

    private static class deleteNoticias extends AsyncTask<Void, Void, Void> {
        NewDAO noticiaDao;

        deleteNoticias(NewDAO noticiaDao) {
            this.noticiaDao = noticiaDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noticiaDao.deleteAll();
            return null;
        }
    }

    private static class getNewDetail extends AsyncTask<String, Void, New> {
        NewDAO noticiaDao;
        LiveData<New> noti;
        MutableLiveData<New> data;

        getNewDetail(NewDAO noticiaDao, MutableLiveData<New> noticiaLiveData) {
            this.noticiaDao = noticiaDao;
            data = noticiaLiveData;
        }

        @Override
        protected New doInBackground(String... strings) {
            Log.d("NOTICIA ID", strings[0]);
            return noticiaDao.getNoticia(strings[0]);
        }

        @Override
        protected void onPostExecute(New noticiaLiveData) {
            super.onPostExecute(noticiaLiveData);
            Log.d("SE EJECUTA", "ON POST");
            data.setValue(noticiaLiveData);
        }
    }


    public LiveData<List<New>> getAllNewsAndInsert(String token) {
        Call<List<New>> call = gameNewsApi.getNewsAll("Bearer " + token);
        call.enqueue(new Callback<List<New>>() {
            @Override
            public void onResponse(Call<List<New>> call, retrofit2.Response<List<New>> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        new insertNAsyncTask(noticiaDao).execute(response.body().toArray(new New[response.body().size()]));
                    }
                    //noticiaDao.insert(Noticias.toArray(new Noticia[Noticias.size()]));
                } else {
                    Log.d("Error", "no succesful");
                }
            }

            @Override
            public void onFailure(Call<List<New>> call, Throwable t) {

            }
        });
        return Noticias;
    }

    public void setFavoritos(String token, final String userId, final String noticiaId, final String favoritos) {
        Call<FavouriteResponse> call = gameNewsApi.addFavourite("Bearer " + token, noticiaId);
        call.enqueue(new Callback<FavouriteResponse>() {
            @Override
            public void onResponse(Call<FavouriteResponse> call, retrofit2.Response<FavouriteResponse> response) {
                if (response.isSuccessful()) {
                    new FavDB(noticiaDao).execute("true", noticiaId);
                    System.out.println("FAVORITOS: " + favoritos);
                    String newfavs;
                    if (!favoritos.equals("")) {
                        newfavs = favoritos + "," + noticiaId;
                    } else {
                        newfavs = noticiaId;
                    }
                    new updateUserFavsBD(userDao).execute(newfavs, userId);
                    Log.d("Success", response.body().getSuccess());
                } else {
                    Log.d("call", call.request().toString());
                    Log.d("response", response.message());
                    Log.d("Error", "Not Succesful");
                }
            }

            @Override
            public void onFailure(Call<FavouriteResponse> call, Throwable t) {
                Log.d("On Failure", t.getMessage());
            }
        });
        return;
    }
/*
    public void deleteFavoritos(String token, final String userId, final String noticiaId, final String favoritos) {
        new FavDB(noticiaDao).execute("false", noticiaId);
        ArrayList<String> newFavs = new ArrayList<>(Arrays.asList(favoritos.split(",")));
        if (newFavs.contains(noticiaId)) {
            newFavs.remove(noticiaId);
        }
        StringBuilder favs = new StringBuilder();
        for (int i = 0; i < newFavs.size(); i++) {
            if (i == newFavs.size() - 1) {
                favs.append(newFavs.get(i));
            } else {
                favs.append(newFavs.get(i));
                favs.append(",");
            }
        }
        new updateUserFavsBD(userDao).execute(favs.toString(), userId);
        Log.d("NEW FAVORITOS ", favs.toString());
        final Call<ResponseBody> call = gameNewsApi.deleteFav("Bearer " + token, userId, noticiaId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Log.d("Success", response.body().toString());
                } else {
                    Log.d("Call", call.request().toString());
                    Log.d("REsponse", response.message());
                    Log.d("Error", "no succesful");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("On Failure", t.getMessage());
            }
        });
    }
*/
    public LiveData<New> getNewDetailDB(String token, final String noticiaId) {
        final MutableLiveData<New> data = new MutableLiveData<>();
        new getNewDetail(noticiaDao, data).execute(noticiaId);
        Log.d("GET NOTICIA", "ENTRA");
        return data;
    }
/*
    public LiveData<String[]> getCategorias(String token) {
        final MutableLiveData<String[]> data = new MutableLiveData<>();
        Call<String[]> call = gameNewsApi.getCategorias("Bearer " + token);
        call.enqueue(new Callback<String[]>() {
            @Override
            public void onResponse(Call<String[]> call, retrofit2.Response<String[]> response) {
                if (response.isSuccessful()) {
                    data.setValue(response.body());
                } else {
                    Log.d("Call", call.request().toString());
                    Log.d("Response", response.message());
                    Log.d("Categorias", "No successful");
                }
            }

            @Override
            public void onFailure(Call<String[]> call, Throwable t) {
                Log.d("Categorias", "On failure");
                Log.d("Error", t.getMessage());
            }
        });
        return data;
    }
*/
    public LiveData<List<New>> getNewByGameFromDB(String game) {
        MutableLiveData<List<New>> data = new MutableLiveData<>();
        new getNewByGameDB(noticiaDao, data).execute(game);
        return data;
    }

    public void deleteAllNoticias() {
        new deleteNoticias(noticiaDao).execute();
    }

    /*
    *
    *
    *TOP PLAYERS
    *
    *
     */

    public LiveData<List<Player>> getPlayersByGame(String game) {
        final MutableLiveData<List<Player>> data = new MutableLiveData<>();
        new getPlayersBygame(playerDao, data).execute(game);
        return data;
    }

    public void deletePlayers() {
        new deletePlayers(playerDao).execute();
    }

    public void getAllPlayersAndInsert(String token, String game) {
        Call<List<Player>> call = gameNewsApi.getPlayerByGame("Bearer " + token, game);
        call.enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if (response.isSuccessful()) {
                    Log.d("GET ALL PLAYERS ", "SUCCESFUL");
                    new insertPlayersAsync(playerDao).execute(response.body().toArray(new Player[response.body().size()]));
                } else {
                    Log.d("GET ALL PLAYERS", "NO SUCCESFUL");
                    Log.d("RESPONSE", response.message());
                }
            }


            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {
                Log.d("ERROR GET ALL PLAYERS", t.getMessage());
            }
        });
    }

    private static class insertPlayersAsync extends AsyncTask<Player, Void, Void> {
        PlayerDAO playerDao;

        insertPlayersAsync(PlayerDAO playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Player... players) {
            playerDao.insert(players);
            return null;
        }
    }

    private static class getPlayersBygame extends AsyncTask<String, Void, List<Player>> {
        PlayerDAO playerDao;
        MutableLiveData<List<Player>> data;

        getPlayersBygame(PlayerDAO playerDao, MutableLiveData<List<Player>> data) {
            this.playerDao = playerDao;
            this.data = data;
        }

        @Override
        protected List<Player> doInBackground(String... strings) {
            Log.d("BACKGROUND ", "TOP PLAYERS");
            return playerDao.getAllPlayersByGame(strings[0]);
        }

        @Override
        protected void onPostExecute(List<Player> topPlayers) {
            super.onPostExecute(topPlayers);
            Log.d("ON POST EXECUTE", "TOP PLAYERS");
            data.setValue(topPlayers);
        }
    }

    public static class deletePlayers extends AsyncTask<Void, Void, Void> {
        PlayerDAO playerDao;

        deletePlayers(PlayerDAO playerDao) {
            this.playerDao = playerDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            playerDao.deleteAll();
            return null;
        }
    }


}