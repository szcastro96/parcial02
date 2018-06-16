package jossehblanco.com.gamenews3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import jossehblanco.com.gamenews3.API.RetrofitClient;
import jossehblanco.com.gamenews3.API.ServiceNews;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        user = findViewById(R.id.user);
        pass = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        retrofitClient = new RetrofitClient();
        retro = retrofitClient.getClient("http://gamenewsuca.herokuapp.com");
        serviceNews = retro.create(ServiceNews.class);
        intent = new Intent(this, MainActivity.class);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users = user.getText().toString();
                passs = pass.getText().toString();
                serviceNews.login(users, passs).enqueue(new Callback<Token>() {
                    @Override
                    public void onResponse(Call<Token> call, Response<Token> response) {
                        if(response.isSuccessful()){
                            ella = response.body().getToken();
                            if(ella != null) {
                                intent.putExtra("usrToken", ella);
                                startActivity(intent);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<Token> call, Throwable t) {
                    }
                });
            }
        });
    }

}

