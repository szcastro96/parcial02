package jossehblanco.com.gamenews3.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.SharedPreferences;
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
 * Created by Salomon Castro on 13/6/2018.
 */

public class ShowNewsFragment extends Fragment {
    private List<New> nuevos;

    private NewVM nuevoVM;
    String token;
    protected RecyclerView snrv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        token = getArguments().getString("token");
            nuevoVM = ViewModelProviders.of(this).get(NewVM.class);
        return inflater.inflate(R.layout.fragment_show_news, container, false);

    }

    /*@Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        System.out.println("antes de rv");
        snrv = view.findViewById(R.id.RVnew);
        System.out.println("Despues del rv");
        nuevoVM.getAllnoticias(token).observe(this, new Observer<List<New>>() {
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

                System.out.println("antes de layout");
                snrv.setLayoutManager(glm);
                System.out.println("antes del adapter");
                ShowNewsAdapter adapter = new ShowNewsAdapter(nuevos, token);
                snrv.setAdapter(adapter);

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }*/

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        System.out.println("antes de rv");
        snrv = view.findViewById(R.id.RVnew);
        System.out.println("Despues del rv");
        nuevoVM.getAllnoticias(token).observe(this, new Observer<List<New>>() {
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

                System.out.println("antes de layout");
                snrv.setLayoutManager(glm);
                System.out.println("antes del adapter");
                ShowNewsAdapter adapter = new ShowNewsAdapter(nuevos, token);
                snrv.setAdapter(adapter);

            }
        });

        super.onViewCreated(view, savedInstanceState);
    }
}
