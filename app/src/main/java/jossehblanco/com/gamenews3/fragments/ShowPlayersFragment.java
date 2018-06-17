package jossehblanco.com.gamenews3.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
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
import jossehblanco.com.gamenews3.VM.PlayerVM;
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
    private PlayerVM playerVM;
    String token, category;
    protected RecyclerView snrv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        token = getArguments().getString("token");
        category = getArguments().getString("category");
        playerVM = ViewModelProviders.of(this).get(PlayerVM.class);
        return inflater.inflate(R.layout.fragment_show_players, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        System.out.println("antes de rv");
        snrv = view.findViewById(R.id.RVplayer);
        System.out.println("Despues del rv");
        playerVM.updatePlayerDB(token, category);
        playerVM.getPlayerByGame(category).observe(this, new Observer<List<Player>>() {
            @Override
            public void onChanged(@Nullable List<Player> playersList) {
                players = playersList;
                for(int i = 0 ; i < playersList.size(); i++){
                    System.out.println(playersList.get(i).getName());
                }
                LinearLayoutManager llm = new LinearLayoutManager(getActivity());
                snrv.setLayoutManager(llm);
                ShowPlayersAdapter adapter = new ShowPlayersAdapter(players, token);
                snrv.setAdapter(adapter);
            }
        });
        super.onViewCreated(view, savedInstanceState);
    }
}
