package jossehblanco.com.gamenews3.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jossehblanco.com.gamenews3.API.RetrofitClient;
import jossehblanco.com.gamenews3.API.ServiceNews;
import jossehblanco.com.gamenews3.R;
import jossehblanco.com.gamenews3.models.New;
import jossehblanco.com.gamenews3.models.Player;
import retrofit2.Retrofit;

/**
 * Created by UCA on 13/6/2018.
 */

public class ShowPlayersAdapter extends RecyclerView.Adapter<ShowPlayersAdapter.showPlayersViewHolder> {
    List<Player> players;
    Context mCtx;
    String token;
    RetrofitClient retrofitClient;
    Retrofit retro;
    ServiceNews serviceNews;
    View snackBarView;
    public ShowPlayersAdapter(List<Player> players, String token){
        this.players = players; this.token = token;
        retrofitClient = new RetrofitClient();
        retro = retrofitClient.getClient("http://gamenewsuca.herokuapp.com/");
        serviceNews = retro.create(ServiceNews.class);
    }
    @Override
    public showPlayersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mCtx = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_players, parent, false);
        showPlayersViewHolder snvh = new showPlayersViewHolder(v);
        return snvh;
    }

    @Override
    public void onBindViewHolder(showPlayersViewHolder holder, final int position) {
        Picasso.with(mCtx)
                .load(players.get(position).getAvatar())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.iv);
        holder.title.setText(players.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class showPlayersViewHolder extends  RecyclerView.ViewHolder{
        ImageView iv;
        TextView title;
        public showPlayersViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.itemShowPlayersCoverImage);
            title = itemView.findViewById(R.id.itemShowPlayersName);
            //eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI1YjBmZmMxZWM4NDcxYTAwMjAxNGMzMDEiLCJpYXQiOjE1Mjg5NTU0MDIsImV4cCI6MTUyODk1OTAwMn0.i-Omg2sThOO3nI72BYDSpwtnHxY8PS_oG5HnYvkJwBw
            //5b0f2555fb295f74c7a0b46c
        }
    }
}
