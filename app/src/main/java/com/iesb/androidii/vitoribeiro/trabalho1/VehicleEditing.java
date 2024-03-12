package com.iesb.androidii.vitoribeiro.trabalho1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VehicleEditing extends AppCompatActivity {

    private EditText etEditModel, etEditBrand, etEditPlate, etEditYear, etEditColor,etEditOwnerName, etEditObservation;
    private Button btEditVehicle;

    private DatabaseReference databaseReference;

    private final String DATABASEREFENCE = "vehicle";

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
        btEditVehicle = findViewById(R.id.btEditVehicle);

        btEditVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vehicleUpdate();

            }
        });

        loadData();

    }
    private void vehicleUpdate(){
        Vehicle vehicle = new Vehicle();
        vehicle.setModel(etEditModel.getText().toString());
        vehicle.setBrand(etEditBrand.getText().toString());
        vehicle.setPlate(etEditPlate.getText().toString());
        vehicle.setYear(etEditYear.getText().toString());
        vehicle.setColor(etEditColor.getText().toString());
        vehicle.setOwnerName(etEditOwnerName.getText().toString());
        vehicle.setObservation(etEditObservation.getText().toString());

        String id = getDataVehicle().getIdVehicle();
        databaseReference = FirebaseDatabase.getInstance().getReference(DATABASEREFENCE).child(id);
        databaseReference.setValue(vehicle);
        finish();

    }

    private Vehicle getDataVehicle(){
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("VEHICLE_ID")){
            Vehicle vehicle = new Vehicle();
            vehicle.setIdVehicle(intent.getStringExtra("VEHICLE_ID"));
            vehicle.setModel(intent.getStringExtra("VEHICLE_MODEL"));
            vehicle.setBrand(intent.getStringExtra("VEHICLE_BRAND"));
            vehicle.setPlate(intent.getStringExtra("VEHICLE_PLATE"));
            vehicle.setYear(intent.getStringExtra("VEHICLE_YEAR"));
            vehicle.setColor(intent.getStringExtra("VEHICLE_COLOR"));
            vehicle.setOwnerName(intent.getStringExtra("VEHICLE_OWNER"));
            vehicle.setObservation(intent.getStringExtra("VEHICLE_OBSERVATION"));
            return vehicle;
        }
        return null;
    }

    private void loadData(){
        Vehicle myVehicle = getDataVehicle();
        etEditModel.setText(myVehicle.getModel());
        etEditBrand.setText(myVehicle.getModel());
        etEditPlate.setText(myVehicle.getPlate());
        etEditYear.setText(myVehicle.getYear());
        etEditColor.setText(myVehicle.getColor());
        etEditOwnerName.setText(myVehicle.getOwnerName());
        etEditObservation.setText(myVehicle.getObservation());

}

}