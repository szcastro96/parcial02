package jossehblanco.com.gamenews3;

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

import jossehblanco.com.gamenews3.VM.UserVM;

/**
 * Created by Salomon Castro on 15/6/2018.
 */

public class LoginActivity extends AppCompatActivity {

    EditText usuario, contra;
    Button cargarBoton;
    static String usuarios, contras;

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
        usuario = findViewById(R.id.usuario);
        contra = findViewById(R.id.contra);
        cargarBoton = findViewById(R.id.loginBTN);


        intent = new Intent(this, MainActivity.class);
        cargarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuarios = usuario.getText().toString();
                contras = contra.getText().toString();
                userVM.initToken(usuarios, contras);
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

