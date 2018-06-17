package jossehblanco.com.gamenews3.fragments;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import jossehblanco.com.gamenews3.API.RetrofitClient;
import jossehblanco.com.gamenews3.API.ServiceNews;
import jossehblanco.com.gamenews3.R;
import jossehblanco.com.gamenews3.VM.NewVM;
import jossehblanco.com.gamenews3.adapters.ShowNewsAdapter;
import jossehblanco.com.gamenews3.models.New;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Salomon Castro on 16/6/2018.
 */

public class TabbedChildFragment extends Fragment {

    private String category;
    private List<New> nuevos;

    private NewVM newVM;
    String token;

    protected RecyclerView snRV;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        category = getArguments().getString("category");
        token = getArguments().getString("token");
        newVM = ViewModelProviders.of(this).get(NewVM.class);
        return inflater.inflate(R.layout.fragment_tabbed_child, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        System.out.println("antes de rv");
        snRV = view.findViewById(R.id.childFragmentRV);
        System.out.println("Despues del rv");
        newVM.getNewByGameDB(category).observe(this, new Observer<List<New>>() {
            @Override
            public void onChanged(@Nullable List<New> newsList) {

                nuevos = newsList;
                GridLayoutManager glm = new GridLayoutManager(getActivity(), 2, GridLayoutManager.VERTICAL, false);

                glm.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        if(position%3 == 0) return 2;
                        else return 1;
                    }
                });

                snRV.setLayoutManager(glm);
                ShowNewsAdapter adapter = new ShowNewsAdapter(nuevos, token);
                snRV.setAdapter(adapter);

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
