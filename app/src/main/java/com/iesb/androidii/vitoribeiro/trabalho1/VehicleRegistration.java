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

public class VehicleRegistration extends AppCompatActivity {

    EditText etModelo, etMarca, etPlaca, etAno, etCor, etProprietario, etObsevação;
    Button btAddVeiculo;

    private DatabaseReference databaseReference;

    private final String DATABASEREFENCE = "vehicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_registration);

        etModelo = findViewById(R.id.etEditModel);
        etMarca = findViewById(R.id.etEditBrand);
        etPlaca = findViewById(R.id.etEditPlate);
        etAno = findViewById(R.id.etEditYear);
        etCor = findViewById(R.id.etEditColor);
        etProprietario = findViewById(R.id.etEditOwnerName);
        etObsevação = findViewById(R.id.etEditObservation);
        btAddVeiculo = findViewById(R.id.btAddVehicle);


        databaseReference = FirebaseDatabase.getInstance().getReference(DATABASEREFENCE);

        btAddVeiculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String modelo = etModelo.getText().toString();
                String marca = etMarca.getText().toString();
                String placa = etPlaca.getText().toString();
                String ano = etAno.getText().toString();
                String cor = etCor.getText().toString();
                String proprietario = etProprietario.getText().toString();
                String observacao = etObsevação.getText().toString();

                infoOk(modelo, marca, placa, ano, cor, proprietario, observacao);
                String idVehicle = databaseReference.push().getKey();
                Vehicle vehicle = new Vehicle();
                vehicle.setModel(modelo);
                vehicle.setBrand(marca);
                vehicle.setYear(ano);
                vehicle.setPlate(placa);
                vehicle.setColor(cor);
                vehicle.setOwnerName(proprietario);
                vehicle.setObservation(observacao);

                databaseReference.child(idVehicle).setValue(vehicle);
                alerta("Sucesso!");


            }
        });

    }

    private Boolean infoOk(String s1, String s2, String s3, String s4, String s5, String s6, String s7) {
        return ((!TextUtils.isEmpty(s1)) && (!TextUtils.isEmpty(s2)) && (!TextUtils.isEmpty(s3)) && (!TextUtils.isEmpty(s4)) && (!TextUtils.isEmpty(s5)) && (!TextUtils.isEmpty(s6)) && (!TextUtils.isEmpty(s7)));
    }

    private void alerta(String menssagem) {

        Toast.makeText(this, menssagem, Toast.LENGTH_SHORT).show();
    }
}