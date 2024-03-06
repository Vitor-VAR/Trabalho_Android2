package com.iesb.androidii.vitoribeiro.trabalho1;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.NameViewHolder>{

    private List<Vehicle> vehicles;

    public VehicleAdapter(List<Vehicle> vehicles){

        this.vehicles = vehicles;
    }


    @NonNull
    @Override
    public VehicleAdapter.NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull VehicleAdapter.NameViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class NameViewHolder extends RecyclerView.ViewHolder{


        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
