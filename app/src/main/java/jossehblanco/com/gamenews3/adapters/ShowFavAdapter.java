package jossehblanco.com.gamenews3.adapters;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jossehblanco.com.gamenews3.API.RetrofitClient;
import jossehblanco.com.gamenews3.API.ServiceNews;
import jossehblanco.com.gamenews3.R;
import jossehblanco.com.gamenews3.models.FavouriteResponse;
import jossehblanco.com.gamenews3.models.New;
import jossehblanco.com.gamenews3.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by UCA on 15/6/2018.
 */

public class ShowFavAdapter extends RecyclerView.Adapter<ShowFavAdapter.ShowFavViewHolder> {
    List<String> newsId;
    List<New> news;
    Context mCtx;
    String token;
    RetrofitClient retrofitClient;
    Retrofit retro;
    ServiceNews serviceNews;
    View snackBarView;
    public ShowFavAdapter(List<String> newsId, String token){
        this.newsId = newsId; this.token = token;
        retrofitClient = new RetrofitClient();
        retro = retrofitClient.getClient("http://gamenewsuca.herokuapp.com/");
        serviceNews = retro.create(ServiceNews.class);
    }
    @Override
    public ShowFavAdapter.ShowFavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        news = new ArrayList<>();
        mCtx = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_fav, parent, false);
        ShowFavAdapter.ShowFavViewHolder snvh = new ShowFavAdapter.ShowFavViewHolder(v);
        return snvh;
    }

    @Override
    public void onBindViewHolder(final ShowFavAdapter.ShowFavViewHolder holder, final int position) {
        serviceNews.getNewDetail("Bearer "+token, newsId.get(position)).enqueue(new Callback<New>() {
            @Override
            public void onResponse(Call<New> call, Response<New> response) {
                if(response.isSuccessful()){
                    news.add(response.body());
                    holder.title.setText(response.body().getTitle());
                    holder.desc.setText(response.body().getDescription());
                    Picasso.with(mCtx)
                            .load(response.body().getCoverImage())
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .into(holder.iv);
                }

            }

            @Override
            public void onFailure(Call<New> call, Throwable t) {

            }
        });


        /*Picasso.with(mCtx)
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
        });*/
    }


    @Override
    public int getItemCount() {
        return newsId.size();
    }

    public static class ShowFavViewHolder extends  RecyclerView.ViewHolder{
        CardView cv;
        ImageView iv;
        TextView title;
        TextView desc;
        Button ib;
        public ShowFavViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.itemShowFavCV);
            title = itemView.findViewById(R.id.itemShowFavTitle);
            desc = itemView.findViewById(R.id.itemShowFavDescription);
            iv = itemView.findViewById(R.id.itemShowFavCoverImage);
            //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1YjBmZmMxZWM4NDcxYTAwMjAxNGMzMDEiLCJpYXQiOjE1Mjg5NTU0MDIsImV4cCI6MTUyODk1OTAwMn0.i-Omg2sThOO3nI72BYDSpwtnHxY8PS_oG5HnYvkJwBw
            //5b0f2555fb295f74c7a0b46c
        }
    }
}

