package com.iesb.androidii.vitoribeiro.trabalho1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class VehicleList extends AppCompatActivity implements VehicleAdapter.OnDeleteClickListener, VehicleAdapter.OnUpdateCLickListener {

    private List<Vehicle> vehicleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VehicleAdapter vehicleAdapter;

    private DatabaseReference databaseReference;

    private final String DATABASEREFENCE = "vehicle";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

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

    }
}