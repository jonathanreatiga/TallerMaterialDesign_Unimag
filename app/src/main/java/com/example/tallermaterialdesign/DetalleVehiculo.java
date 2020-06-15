package com.example.tallermaterialdesign;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class DetalleVehiculo extends AppCompatActivity {
    private Vehiculo vh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_vehiculo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ImageView foto;
        TextView placa, marca, linea, modelo, uso;
        Bundle bundle;
        Intent intent;
        String _id, _placa, _marca, _linea, _modelo, _uso;
        StorageReference storageReference;

        foto = findViewById(R.id.imgFotoDetalle);
        placa = findViewById(R.id.lblPlacaDetalle);
        marca = findViewById(R.id.lblMarcaDetalle);
        linea = findViewById(R.id.lblLineaDetalle);
        modelo = findViewById(R.id.lblModeloDetalle);
        uso = findViewById(R.id.lblUsoDetalle);

        intent = getIntent();
        bundle = intent.getBundleExtra("datos");

        _id = bundle.getString("id");
        _placa = bundle.getString("placa");
        _marca = bundle.getString("marca");
        _linea = bundle.getString("linea");
        _modelo = bundle.getString("modelo");
        _uso = bundle.getString("uso");

        ArrayList<Vehiculo> lista_vehiculos = AdaptadorVehiculo.ObtenerListaVehiculos();
        for (Vehiculo vehiculo:lista_vehiculos) {
            if(_placa.equals(vehiculo.getPlaca())){
                _id = vehiculo.getId();
                _placa = vehiculo.getPlaca();
                _marca = vehiculo.getMarca();
                _linea = vehiculo.getLinea();
                _modelo = vehiculo.getModelo();
                _uso = vehiculo.getUso();
            }
        }

        storageReference = FirebaseStorage.getInstance().getReference();
        storageReference.child(_id).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.get().load(uri).into(foto);
            }
        });
        placa.setText(_placa);
        marca.setText(_marca);
        linea.setText(_linea);
        modelo.setText(_modelo);
        uso.setText(_uso);

        vh = new Vehiculo(_placa, _marca, _linea, _modelo, _uso, 0, _id);
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
