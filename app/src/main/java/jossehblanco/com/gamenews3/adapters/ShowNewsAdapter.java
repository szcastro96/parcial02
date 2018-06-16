package jossehblanco.com.gamenews3.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jossehblanco.com.gamenews3.API.RetrofitClient;
import jossehblanco.com.gamenews3.API.ServiceNews;
import jossehblanco.com.gamenews3.R;
import jossehblanco.com.gamenews3.models.FavouriteResponse;
import jossehblanco.com.gamenews3.models.New;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by UCA on 13/6/2018.
 */

public class ShowNewsAdapter extends RecyclerView.Adapter<ShowNewsAdapter.ShowNewsViewHolder> {
    List<New> news;
    Context mCtx;
    String token;
    RetrofitClient retrofitClient;
    Retrofit retro;
    ServiceNews serviceNews;
    View snackBarView;
    public ShowNewsAdapter(List<New> news, String token){
        this.news = news; this.token = token;
        retrofitClient = new RetrofitClient();
        retro = retrofitClient.getClient("http://gamenewsuca.herokuapp.com/");
        serviceNews = retro.create(ServiceNews.class);
    }
    @Override
    public ShowNewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mCtx = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_news, parent, false);
        ShowNewsViewHolder snvh = new ShowNewsViewHolder(v);
        return snvh;
    }

    @Override
    public void onBindViewHolder(ShowNewsViewHolder holder, final int position) {
        Picasso.with(mCtx)
                .load(news.get(position).getCoverImage())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.iv);
        holder.title.setText(news.get(position).getTitle());
        holder.desc.setText(news.get(position).getDescription());
        holder.ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackBarView = v;
                System.out.println("Click al boton");
                serviceNews.addFavourite("Bearer "+ token, news.get(position).get_id()).enqueue(new Callback<FavouriteResponse>() {
                    @Override
                    public void onResponse(Call<FavouriteResponse> call, Response<FavouriteResponse> response) {
                        if(response.body().getSuccess().equals("true")){
                            Snackbar.make(snackBarView, "La noticia se añadió a favoritos!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }else{
                            Snackbar.make(snackBarView, "Error al añadir favorito!", Snackbar.LENGTH_LONG)
                                    .setAction("Action", null).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<FavouriteResponse> call, Throwable t) {
                        System.out.println(t.getMessage());
                    }
                });
            }
        });
    }


    @Override
    public int getItemCount() {
        return news.size();
    }

    public static class ShowNewsViewHolder extends  RecyclerView.ViewHolder{
        CardView cv;
        ImageView iv;
        TextView title;
        TextView desc;
        Button ib;
        public ShowNewsViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.itemShowNewsCV);
            iv = itemView.findViewById(R.id.itemShowNewsCoverImage);
            title = itemView.findViewById(R.id.itemShowNewsTitle);
            desc = itemView.findViewById(R.id.itemShowNewsDescription);
            ib = itemView.findViewById(R.id.favButton);
            //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1YjBmZmMxZWM4NDcxYTAwMjAxNGMzMDEiLCJpYXQiOjE1Mjg5NTU0MDIsImV4cCI6MTUyODk1OTAwMn0.i-Omg2sThOO3nI72BYDSpwtnHxY8PS_oG5HnYvkJwBw
            //5b0f2555fb295f74c7a0b46c
        }
    }
}
