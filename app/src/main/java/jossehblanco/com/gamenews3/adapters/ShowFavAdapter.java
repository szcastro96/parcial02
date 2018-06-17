package jossehblanco.com.gamenews3.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import jossehblanco.com.gamenews3.API.RetrofitClient;
import jossehblanco.com.gamenews3.API.ServiceNews;
import jossehblanco.com.gamenews3.R;
import jossehblanco.com.gamenews3.models.New;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Salomon Castro on 15/6/2018.
 */

public class ShowFavAdapter extends RecyclerView.Adapter<ShowFavAdapter.ShowFavViewHolder> {
    List<String> nuevosId;
    List<New> nuevos;
    Context mContext;
    String token;
    RetrofitClient retrofitClient;
    Retrofit retroF;
    ServiceNews serviciosNuevos;


        public ShowFavAdapter(List<String> newsId, String token){

        this.nuevosId = newsId; this.token = token;
        retrofitClient = new RetrofitClient();
        retroF = retrofitClient.getClient("http://gamenewsuca.herokuapp.com/");
        serviciosNuevos = retroF.create(ServiceNews.class);
    }

    @Override
    public ShowFavAdapter.ShowFavViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        nuevos = new ArrayList<>();
        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_fav, parent, false);
        ShowFavAdapter.ShowFavViewHolder snvh = new ShowFavAdapter.ShowFavViewHolder(v);
        return snvh;
    }

    @Override
    public void onBindViewHolder(final ShowFavAdapter.ShowFavViewHolder holder, final int position) {

        serviciosNuevos.getNewDetail("Bearer "+token, nuevosId.get(position)).enqueue(new Callback<New>() {

            @Override
            public void onResponse(Call<New> call, Response<New> response) {
                if(response.isSuccessful()){
                    nuevos.add(response.body());
                    holder.titleView.setText(response.body().getTitle());
                    holder.descView.setText(response.body().getDescription());
                    Picasso.with(mContext)
                            .load(response.body().getCoverImage())
                            .placeholder(R.drawable.ic_launcher_background)
                            .error(R.drawable.ic_launcher_background)
                            .into(holder.ivView);
                }

            }

            @Override
            public void onFailure(Call<New> call, Throwable t) {

            }
        });


    }


    @Override
    public int getItemCount() {
        return nuevosId.size();
    }

    public static class ShowFavViewHolder extends  RecyclerView.ViewHolder{
        CardView cview;
        ImageView ivView;
        TextView titleView;
        TextView descView;

        public ShowFavViewHolder(View itemView) {

            super(itemView);

            cview = itemView.findViewById(R.id.itemShowFavCV);
            titleView = itemView.findViewById(R.id.itemFavTitulo);
            descView = itemView.findViewById(R.id.itemFavDescripcion);
            ivView = itemView.findViewById(R.id.itemFavIMG);

        }
    }
}

