package jossehblanco.com.gamenews3;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import jossehblanco.com.gamenews3.API.RetrofitClient;
import jossehblanco.com.gamenews3.API.ServiceNews;
import jossehblanco.com.gamenews3.fragments.ShowFavFragment;
import jossehblanco.com.gamenews3.fragments.ShowNewsFragment;
import jossehblanco.com.gamenews3.fragments.TabbedFragment;
import jossehblanco.com.gamenews3.models.New;

import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Views y Token
    TextView newTitle;
    String token;
    private RetrofitClient retrofitClient;
    private Retrofit retro;
    private ServiceNews serviceNews;
    private Intent intent;
    private Button cargar;
    private List<New> news;
    //
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadNavigationMenu();

        //Cargar Noticias generales
        token = getIntent().getExtras().getString("usrToken");
        //Cargar el fragmento por primera vez
        Fragment showNewsFragment = new ShowNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        showNewsFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment_container, showNewsFragment).commit();
        //Finalizar de cargar el fragmento
        if(savedInstanceState == null){


        }
        //
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.generalNews) {
            Fragment showNewsFragment = new ShowNewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString("token", token);
            showNewsFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_container, showNewsFragment).commit();

        } else if (id == R.id.lolnews) {
            TabbedFragment tabbedFragment = new TabbedFragment();
            Bundle bundle = new Bundle();
            bundle.putString("token", token);
            tabbedFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_container, tabbedFragment).commit();

        } else if (id == R.id.ownews) {

        } else if(id == R.id.csgonews){

        }
        else if (id == R.id.favourites) {
            Fragment showFavFragment = new ShowFavFragment();
            Bundle bundle = new Bundle();
            bundle.putString("token", token);
            showFavFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_container, showFavFragment).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void loadNavigationMenu(){
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    /*
    FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
    fab.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        }
    });*/

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
    navigationView.setNavigationItemSelectedListener(this);
    navigationView.getMenu().addSubMenu("Hola bb").add("holi");
    navigationView.getMenu().getItem(0).setChecked(true);
}

}