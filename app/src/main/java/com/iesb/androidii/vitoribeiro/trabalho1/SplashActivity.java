package com.iesb.androidii.vitoribeiro.trabalho1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends Activity {

    private FirebaseAuth firebaseAuth;
    private  static final int SPLASH_DISPLAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        firebaseAuth = FirebaseAuthSingleton.getInstance();


    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if (firebaseUser != null){
            startActivity(new Intent(SplashActivity.this, VehicleListActivity.class));
        }else {
            System.out.println("Firebase::: Usuário não Logado");
            navigateToScream();
        }
    }

    private void navigateToScream(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }, SPLASH_DISPLAY_TIME);
    }
}