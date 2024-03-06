package com.iesb.androidii.vitoribeiro.trabalho1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class VehicleList extends AppCompatActivity {

    private List<Vehicle> vehicleList = new ArrayList<>();
    private RecyclerView recyclerView;
    private VehicleAdapter vehicleAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_list);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        vehicleAdapter = new VehicleAdapter(vehicleList);
        recyclerView.setAdapter(vehicleAdapter);
        vehicleAdapter.notifyItemInserted(vehicleList.size()-1);
        System.out.println("Veículos" + vehicleList.size());

    }
}