package com.iesb.androidii.vitoribeiro.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class VehicleEditing extends AppCompatActivity {

    private EditText etEditModel, etEditBrand, etEditPlate, etEditYear, etEditColor,etEditOwnerName, etEditObservation;
    private Button btAddVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_editing);

        etEditModel = findViewById(R.id.etEditModel);
        etEditBrand = findViewById(R.id.etEditBrand);
        etEditPlate = findViewById(R.id.etEditPlate);
        etEditYear = findViewById(R.id.etEditYear);
        etEditColor = findViewById(R.id.etEditColor);
        etEditOwnerName = findViewById(R.id.etEditOwnerName);
        etEditObservation = findViewById(R.id.etEditObservation);

    }
}