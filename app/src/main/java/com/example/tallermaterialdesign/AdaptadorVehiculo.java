package com.example.tallermaterialdesign;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AdaptadorVehiculo extends RecyclerView.Adapter<AdaptadorVehiculo.PersonaViewHolder>{
    private ArrayList<Vehiculo> vehiculos;

    public AdaptadorVehiculo(ArrayList<Vehiculo> vehiculos){
        this.vehiculos = vehiculos;
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehiculo, parent,false);
        return new PersonaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Vehiculo v = vehiculos.get(position);
        holder.foto.setImageResource(v.getFoto());
        holder.placa.setText(v.getPlaca());
        holder.marca.setText(v.getMarca());
        holder.linea.setText(v.getLinea());
    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder{
        private ImageView foto;
        private TextView placa;
        private TextView marca;
        private TextView linea;
        private View v;

        public PersonaViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFoto);
            placa = v.findViewById(R.id.lblPlaca);
            marca = v.findViewById(R.id.lblMarca);
            linea = v.findViewById(R.id.lblLinea);
        }
    }
}
