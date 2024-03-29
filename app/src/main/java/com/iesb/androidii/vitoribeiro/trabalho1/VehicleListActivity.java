package com.iesb.androidii.vitoribeiro.trabalho1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VehicleListActivity extends AppCompatActivity implements VehicleAdapter.OnDeleteClickListener, VehicleAdapter.OnUpdateCLickListener {

    private List<Vehicle> vehicleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VehicleAdapter vehicleAdapter;

    private Button btAddVehicleList;

    private DatabaseReference databaseReference;

    private final String DATABASEREFENCE = "vehicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        btAddVehicleList = findViewById(R.id.btAddVehicleList);

        recyclerView = findViewById(R.id.recyclerView);
        vehicleAdapter = new VehicleAdapter(this,vehicleList, this,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(vehicleAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference(DATABASEREFENCE);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                vehicleList.clear();
                for (DataSnapshot snapshot1: snapshot.getChildren()){
                    Vehicle vehicle = snapshot1.getValue(Vehicle.class);
                    vehicle.setIdVehicle(snapshot1.getKey());
                    vehicleList.add(vehicle);
                }
                vehicleAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                alerta("Erro ao ler o banco.");

            }
        });

//        vehicleAdapter.notifyItemInserted(vehicleList.size()-1);
//        System.out.println("Veículos" + vehicleList.size());
        btAddVehicleList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(VehicleListActivity.this, VehicleRegistration.class));
            }
        });

    }
    private void alerta(String menssagem){

        Toast.makeText(this, menssagem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDeleteClick(int position) {
        System.out.println("Position" + position);
        deleteVehicle(position);
    }
    private void deleteVehicle(int position){
        databaseReference.child(vehicleList.get(position).getIdVehicle()).removeValue();

    }

    @Override
    public void onUpdateClick(int position) {
        openUpdate(position);

    }
    public void openUpdate(int position){
        String idVehicle = vehicleList.get(position).getIdVehicle();
        String vehicleModel = vehicleList.get(position).getModel();
        String vehicleBrand = vehicleList.get(position).getBrand();
        String vehiclePlate = vehicleList.get(position).getPlate();
        String vehicleYear = vehicleList.get(position).getYear();
        String vehicleColor = vehicleList.get(position).getColor();
        String vehicleOwnerName = vehicleList.get(position).getOwnerName();
        String vehicleObservation = vehicleList.get(position).getObservation();

        Intent intent = new Intent(this, VehicleEditing.class);

        intent.putExtra("VEHICLE_ID", idVehicle);
        intent.putExtra("VEHICLE_MODEL", vehicleModel);
        intent.putExtra("VEHICLE_BRAND", vehicleBrand);
        intent.putExtra("VEHICLE_PLATE", vehiclePlate);
        intent.putExtra("VEHICLE_YEAR", vehicleYear);
        intent.putExtra("VEHICLE_COLOR", vehicleColor);
        intent.putExtra("VEHICLE_OWNER", vehicleOwnerName);
        intent.putExtra("VEHICLE_OBSERVATION", vehicleObservation);
        startActivity(intent);

    }
}