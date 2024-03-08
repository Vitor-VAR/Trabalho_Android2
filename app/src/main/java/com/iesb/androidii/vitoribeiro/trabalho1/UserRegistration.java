package com.iesb.androidii.vitoribeiro.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRegistration extends AppCompatActivity {

private EditText etName, etEmail, etPass;

private Button btAdd;

private DatabaseReference databaseReference;

private final String DATABASEREFENCE = "user" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        btAdd = findViewById(R.id.btAdd);


        databaseReference = FirebaseDatabase.getInstance().getReference(DATABASEREFENCE);

        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String email = etEmail.getText().toString();
                String password = etPass.getText().toString();


                if ((!TextUtils.isEmpty(name)) && (!TextUtils.isEmpty(email)) && (!TextUtils.isEmpty(password))){
                    String useId = databaseReference.push().getKey();
                    User user = new User();
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(password);
                    databaseReference.child(useId).setValue(user);

                    alerta("Sucesso!");


                }
            }
        });


    }

    private void alerta(String menssagem){

            Toast.makeText(this, menssagem, Toast.LENGTH_SHORT).show();
    }
}