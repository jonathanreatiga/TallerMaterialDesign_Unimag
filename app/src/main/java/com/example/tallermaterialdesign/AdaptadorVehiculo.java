package com.example.tallermaterialdesign;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorVehiculo extends RecyclerView.Adapter<AdaptadorVehiculo.PersonaViewHolder>{
    private ArrayList<Vehiculo> vehiculos;
    private OnVehiculoClickListener clickListener;

    public AdaptadorVehiculo(ArrayList<Vehiculo> vehiculos, OnVehiculoClickListener clickListener){
        this.vehiculos = vehiculos;
        this.clickListener = clickListener;
    }

    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vehiculo, parent,false);
        return new PersonaViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final PersonaViewHolder holder, int position) {
        final Vehiculo vh = vehiculos.get(position);

        StorageReference storageReference;
        storageReference = FirebaseStorage.getInstance().getReference();

        storageReference.child(vh.getId()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(holder.foto);
            }
        });


        //holder.foto.setImageResource(vh.getFoto());
        holder.placa.setText(vh.getPlaca());
        holder.marca.setText(vh.getMarca());
        holder.linea.setText(vh.getLinea());

        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onVehiculoClick(vh);
            }
        });
    }

    @Override
    public int getItemCount() {
        return vehiculos.size();
    }

    public static class PersonaViewHolder extends RecyclerView.ViewHolder{
        private CircleImageView foto;
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

    public interface OnVehiculoClickListener{
        void onVehiculoClick(Vehiculo p);
    }
}
