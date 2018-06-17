package jossehblanco.com.gamenews3;

import android.app.Activity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jossehblanco.com.gamenews3.API.RetrofitClient;
import jossehblanco.com.gamenews3.API.ServiceNews;
import jossehblanco.com.gamenews3.VM.UserVM;
import jossehblanco.com.gamenews3.models.Token;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by UCA on 15/6/2018.
 */

public class LoginActivity extends AppCompatActivity {
    EditText user, pass;
    Button loginButton;
    static String users, passs;
    String ella;
    private RetrofitClient retrofitClient;
    private Retrofit retro;
    private ServiceNews serviceNews;
    private Intent intent;
    private UserVM userVM;
    private AppCompatActivity appCompatActivity;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        preferences = getSharedPreferences("usrInfo", Context.MODE_PRIVATE);
        if(preferences.contains("usrToken")){
            Intent inte = new Intent(this, MainActivity.class);
            startActivity(inte);
        }
        appCompatActivity = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userVM = ViewModelProviders.of(this).get(UserVM.class);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        /*retrofitClient = new RetrofitClient();
        retro = retrofitClient.getClient("http://gamenewsuca.herokuapp.com");
        serviceNews = retro.create(ServiceNews.class);
        */
        intent = new Intent(this, MainActivity.class);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users = user.getText().toString();
                passs = pass.getText().toString();
                userVM.initToken(users, passs);
                userVM.getToken().observe(appCompatActivity, new Observer<String>() {
                    @Override
                    public void onChanged(@Nullable String s) {
                        intent = new Intent(appCompatActivity, MainActivity.class);
                        intent.putExtra("usrToken", s);
                        preferences.edit().putString("usrToken", s).apply();
                        startActivity(intent);
                    }
                });
            }
        });
    }

}

