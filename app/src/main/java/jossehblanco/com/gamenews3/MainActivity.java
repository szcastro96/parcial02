package jossehblanco.com.gamenews3;


import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.SharedPreferences;
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

import jossehblanco.com.gamenews3.VM.PlayerVM;
import jossehblanco.com.gamenews3.fragments.ShowFavFragment;
import jossehblanco.com.gamenews3.fragments.ShowNewsFragment;
import jossehblanco.com.gamenews3.fragments.TabbedFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    //Views y Token de la activity
    String token;

    private SharedPreferences preferencia;
    private PlayerVM playerViewM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playerViewM = ViewModelProviders.of(this).get(PlayerVM.class);
        preferencia = getSharedPreferences("usrInfo", Context.MODE_PRIVATE);

        if(preferencia.contains("usrToken")){

            token = preferencia.getString("usrToken", "nullstr");

        }else{

            token = getIntent().getExtras().getString("usrToken");

        }

        loadNavigationMenu();

        //Cargar los players

        playerViewM.updatePlayerDB(token,"lol");

        playerViewM.updatePlayerDB(token,"overwatch");

        playerViewM.updatePlayerDB(token,"csgo");

        //Cargar el fragmento por primera vez

        Fragment showNewsFragment = new ShowNewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("token", token);
        showNewsFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment_container, showNewsFragment).commit();

        //Termina de cargar el fragmento completo

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
            bundle.putInt("categoryId", 0);
            tabbedFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_container, tabbedFragment).commit();

        } else if (id == R.id.ownews) {
            TabbedFragment tabbedFragment = new TabbedFragment();
            Bundle bundle = new Bundle();
            bundle.putString("token", token);
            bundle.putInt("categoryId", 1);
            tabbedFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_container, tabbedFragment).commit();
        } else if(id == R.id.csgonews){
            TabbedFragment tabbedFragment = new TabbedFragment();
            Bundle bundle = new Bundle();
            bundle.putString("token", token);
            bundle.putInt("categoryId", 2);
            tabbedFragment.setArguments(bundle);
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_fragment_container, tabbedFragment).commit();

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
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolBAR);
    setSupportActionBar(toolbar);

    DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
    ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
            this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
    drawer.addDrawerListener(toggle);
    toggle.syncState();

    NavigationView navigationView = (NavigationView) findViewById(R.id.NAV_view);
    navigationView.setNavigationItemSelectedListener(this);

    }

}