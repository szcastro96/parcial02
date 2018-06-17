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
import jossehblanco.com.gamenews3.models.Player;
import retrofit2.Retrofit;

/**
 * Created by Salomon Castro on 13/6/2018.
 */

public class ShowPlayersAdapter extends RecyclerView.Adapter<ShowPlayersAdapter.showPlayersViewHolder> {
    List<Player> jugadores;
    Context mContext;
    String token;
    RetrofitClient retrofitClient;
    Retrofit retroF;
    ServiceNews servicioN;

    public ShowPlayersAdapter(List<Player> players, String token){

        this.jugadores = players; this.token = token;
        retrofitClient = new RetrofitClient();
        retroF = retrofitClient.getClient("http://gamenewsuca.herokuapp.com/");
        servicioN = retroF.create(ServiceNews.class);
    }

    @Override
    public showPlayersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        mContext = parent.getContext();
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show_players, parent, false);
        showPlayersViewHolder snvh = new showPlayersViewHolder(v);
        return snvh;
    }

    @Override
    public void onBindViewHolder(showPlayersViewHolder holder, final int position) {
        Picasso.with(mContext)
                .load(jugadores.get(position).getAvatar())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.iv);
        holder.title.setText(jugadores.get(position).getName());
    }


    @Override
    public int getItemCount() {
        return jugadores.size();
    }

    public static class showPlayersViewHolder extends  RecyclerView.ViewHolder{

        ImageView iv;
        TextView title;
        public showPlayersViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.itemJugadorIMG);
            title = itemView.findViewById(R.id.itemNombreJugador);

        }
    }
}
