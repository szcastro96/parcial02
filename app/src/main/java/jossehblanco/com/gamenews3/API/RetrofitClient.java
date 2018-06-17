package jossehblanco.com.gamenews3.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Salomon Castro on 12/6/2018.
 */

public class RetrofitClient {

    private static Retrofit retrofit = null;
    public static Retrofit getClient(String baseurl){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().
                    baseUrl(baseurl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit;
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
