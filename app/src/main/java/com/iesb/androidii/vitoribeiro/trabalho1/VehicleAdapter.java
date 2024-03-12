package com.iesb.androidii.vitoribeiro.trabalho1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class VehicleAdapter extends RecyclerView.Adapter<VehicleAdapter.ViewHolder>{

    private List<Vehicle> vehicles;
    private Context context;

    private OnDeleteClickListener onDeleteClickListener;
    private OnUpdateCLickListener onUpdateCLickListener;

    public VehicleAdapter( Context context, List<Vehicle> vehicles, OnDeleteClickListener onDeleteClickListener, OnUpdateCLickListener onUpdateCLickListener){

        this.vehicles = vehicles;
        this.context = context;
        this.onDeleteClickListener = onDeleteClickListener;
        this.onUpdateCLickListener = onUpdateCLickListener;
    }

    public VehicleAdapter(List<Vehicle> vehicles){

        this.vehicles = vehicles;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehicle, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Vehicle vehicle = this.vehicles.get(position);
        holder.tvModel.setText(vehicle.getModel());
        holder.tvBrand.setText(vehicle.getBrand());
        holder.tvYear.setText(vehicle.getYear());
        holder.tvPlate.setText(vehicle.getPlate());
        holder.tvColor.setText(vehicle.getColor());
        holder.tvOwnerName.setText(vehicle.getOwnerName());
        holder.tvObservation.setText(vehicle.getObservation());

        holder.btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onUpdateCLickListener != null){
                    onUpdateCLickListener.onUpdateClick(holder.getAbsoluteAdapterPosition());
                }

            }
        });
        holder.btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onDeleteClickListener != null){
                    onDeleteClickListener.onDeleteClick(holder.getAbsoluteAdapterPosition());
                }
            }
        });

    }

    @Override
    public int getItemCount() {

        return this.vehicles.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvModel, tvBrand, tvYear, tvPlate, tvColor, tvOwnerName, tvObservation;
        Button btDelete, btEdit;

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvModel = itemView.findViewById(R.id.tvModel);
            tvBrand = itemView.findViewById(R.id.tvBrand);
            tvYear = itemView.findViewById(R.id.tvYear);
            tvPlate = itemView.findViewById(R.id.tvPlate);
            tvColor = itemView.findViewById(R.id.tvColor);
            tvOwnerName = itemView.findViewById(R.id.tvOwnerName);
            tvObservation = itemView.findViewById(R.id.tvObservation);
            btDelete = itemView.findViewById(R.id.btDelete);
            btEdit = itemView.findViewById(R.id.btEdit);


        }

        }

    public interface OnDeleteClickListener{
        void onDeleteClick(int position);

    }

    public interface OnUpdateCLickListener{
        void onUpdateClick(int position);
    }


    }


