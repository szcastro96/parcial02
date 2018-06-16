package jossehblanco.com.gamenews3.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import jossehblanco.com.gamenews3.adapters.ShowNewsAdapter;
import jossehblanco.com.gamenews3.adapters.ShowPlayersAdapter;
import jossehblanco.com.gamenews3.models.New;
import jossehblanco.com.gamenews3.models.Player;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by UCA on 13/6/2018.
 */

public class ShowPlayersFragment extends Fragment {
    private List<Player> players;
    private RetrofitClient retrofitClient;
    private Retrofit retro;
    private ServiceNews serviceNews;
    String token, category;
    protected RecyclerView snrv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        token = getArguments().getString("token");
        category = getArguments().getString("category");
        return inflater.inflate(R.layout.fragment_show_players, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        System.out.println("antes de rv");
        snrv = view.findViewById(R.id.RVplayer);
        System.out.println("Despues del rv");

        retrofitClient = new RetrofitClient();
        retro = retrofitClient.getClient("http://gamenewsuca.herokuapp.com/");
        serviceNews = retro.create(ServiceNews.class);
        serviceNews.getPlayerByGame("Bearer " + token, category).enqueue(new Callback<List<Player>>() {
            @Override
            public void onResponse(Call<List<Player>> call, Response<List<Player>> response) {
                if(response.isSuccessful()){
                    players = response.body();
                    LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                    snrv.setLayoutManager(llm);
                    System.out.println("antes del adapter");
                    ShowPlayersAdapter adapter = new ShowPlayersAdapter(players, token);
                    snrv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<Player>> call, Throwable t) {

            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
