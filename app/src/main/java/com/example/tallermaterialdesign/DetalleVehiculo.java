package com.example.tallermaterialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleVehiculo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_vehiculo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView foto;
        TextView placa, marca, linea;
        Bundle bundle;
        Intent intent;

        foto = findViewById(R.id.imgFotoDetalle);
        placa = findViewById(R.id.lblPlacaDetalle);
        marca = findViewById(R.id.lblMarcaDetalle);
        linea = findViewById(R.id.lblLineaDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        foto.setImageResource(bundle.getInt("foto"));
        placa.setText(bundle.getString("placa"));
        marca.setText(bundle.getString("marca"));
        linea.setText(bundle.getString("linea"));
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleVehiculo.this, MainActivity.class);
        startActivity(intent);
    }
}
