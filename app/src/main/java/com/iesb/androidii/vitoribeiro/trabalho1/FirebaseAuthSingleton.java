package com.iesb.androidii.vitoribeiro.trabalho1;

import com.google.firebase.auth.FirebaseAuth;

public class FirebaseAuthSingleton {

    private static FirebaseAuth firebaseAuth;

    private FirebaseAuthSingleton(){
        firebaseAuth = FirebaseAuth.getInstance();
    }

    public static FirebaseAuth getInstance(){
        if (firebaseAuth == null){

            synchronized (FirebaseAuthSingleton.class){
                if(firebaseAuth == null){

                    new FirebaseAuthSingleton();
                }

            }
        }
        return firebaseAuth;
    }
}
