package jossehblanco.com.gamenews3.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jossehblanco.com.gamenews3.API.RetrofitClient;
import jossehblanco.com.gamenews3.API.ServiceNews;
import jossehblanco.com.gamenews3.R;
import jossehblanco.com.gamenews3.adapters.ShowFavAdapter;
import jossehblanco.com.gamenews3.adapters.ShowNewsAdapter;
import jossehblanco.com.gamenews3.models.New;
import jossehblanco.com.gamenews3.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by UCA on 15/6/2018.
 */

public class ShowFavFragment extends Fragment {
    private List<String> news;
    private RetrofitClient retrofitClient;
    private Retrofit retro;
    private ServiceNews serviceNews;
    private Intent intent;
    String token;
    protected RecyclerView sfrv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        token = getArguments().getString("token");
        return inflater.inflate(R.layout.fragment_show_fav, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        sfrv = view.findViewById(R.id.RVfav);

//
        retrofitClient = new RetrofitClient();
        retro = retrofitClient.getClient("http://gamenewsuca.herokuapp.com/");
        serviceNews = retro.create(ServiceNews.class);
        serviceNews.getUserDetail("Bearer "+token.toString()).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    news = response.body().getFavoriteNews();
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    System.out.println("antes de layout");
                    sfrv.setLayoutManager(llm);
                    System.out.println("antes del adapter");
                    ShowFavAdapter adapter = new ShowFavAdapter(news, token);
                    sfrv.setAdapter(adapter);
                }else{
                    System.out.println("La request fallo");
                }

            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}