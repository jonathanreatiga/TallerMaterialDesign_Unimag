package com.example.tallermaterialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;
import java.io.Console;

public class DetalleVehiculo extends AppCompatActivity {
    private Vehiculo vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_vehiculo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView foto;
        TextView placa, marca, linea, modelo;
        Bundle bundle;
        Intent intent;
        String _placa, _marca, _linea, _modelo;
        int fot;

        foto = findViewById(R.id.imgFotoDetalle);
        placa = findViewById(R.id.lblPlacaDetalle);
        marca = findViewById(R.id.lblMarcaDetalle);
        linea = findViewById(R.id.lblLineaDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        fot = bundle.getInt("foto");
        _placa = bundle.getString("placa");
        _marca = bundle.getString("marca");
        _linea = bundle.getString("linea");

        foto.setImageResource(fot);
        placa.setText(_placa);
        marca.setText(_marca);
        linea.setText(_linea);

        vh = new Vehiculo(_placa, _marca, _linea, "", "", fot);
    }

    public void onBackPressed(){
        finish();
        Intent intent = new Intent(DetalleVehiculo.this, MainActivity.class);
        startActivity(intent);
    }

    public void eliminar(View v){
        String positivo, negativo;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_eliminar_persona);
        builder.setMessage(R.string.pregunta_mensaje_eliminar);
        positivo = getString(R.string.opcion_si);
        negativo = getString(R.string.opcion_no);

        builder.setPositiveButton(positivo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                vh.eliminar();
                onBackPressed();
            }
        });

        builder.setNegativeButton(negativo, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
}
