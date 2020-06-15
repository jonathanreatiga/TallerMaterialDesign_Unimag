package com.example.tallermaterialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.util.ArrayList;
import java.util.Random;

public class AgregarVehiculo extends AppCompatActivity {
    private Spinner cmb_USOS;
    private String[] USO;
    private ArrayAdapter<String> ADAPTER;

    private ArrayList<Integer> fotos;
    private EditText placa, marca, linea, modelo;
    private StorageReference storageReference;
    private  Uri uri;
    private ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vehiculo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // USOS
        cmb_USOS = findViewById(R.id.cmbUso);
        USO = getResources().getStringArray(R.array.Uso);
        ADAPTER = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, USO);
        cmb_USOS.setAdapter(ADAPTER);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        placa = findViewById(R.id.txtPlaca);
        marca = findViewById(R.id.txtMarca);
        linea = findViewById(R.id.txtLinea);
        modelo = findViewById(R.id.txtModelo);

        foto = findViewById(R.id.imgFotoSeleccionada);
        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);

        storageReference = FirebaseStorage.getInstance().getReference();
    }

    public void guardar(View v){
        String _placa, _marca, _linea, _modelo, _id;
        String _uso = cmb_USOS.getSelectedItem().toString();

        int foto;
        Vehiculo vehiculo;
        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        _placa = placa.getText().toString();
        _marca = marca.getText().toString();
        _linea = linea.getText().toString();
        _modelo = modelo.getText().toString();
        foto = foto_aleatoria();
        _id = Datos.getId();
        vehiculo = new Vehiculo(_placa, _marca, _linea, _modelo, _uso, foto, _id);

        if (validar(_placa)){
            vehiculo.guardar();
            subir_foto(_id);
            limpiar();
            imp.hideSoftInputFromWindow(placa.getWindowToken(),0);
            Snackbar.make(v, getString(R.string.mensaje_guardado_correcto),Snackbar.LENGTH_LONG).show();
        }
    }

    private boolean validar(String _placa){
        String error_PlacaDuplicada = getResources().getString(R.string.placa_duplicada);
        String error_CampoVacio = getResources().getString(R.string.error_CampoVacio);

        if(placa.getText().toString().isEmpty()){
            placa.setError(error_CampoVacio);
            placa.requestFocus();
            return false;
        }

        if(marca.getText().toString().isEmpty()){
            marca.setError(error_CampoVacio);
            marca.requestFocus();
            return false;
        }

        if(linea.getText().toString().isEmpty()){
            linea.setError(error_CampoVacio);
            linea.requestFocus();
            return false;
        }

        if(modelo.getText().toString().isEmpty()){
            modelo.setError(error_CampoVacio);
            modelo.requestFocus();
            return false;
        }

        ArrayList<Vehiculo> lista_vehiculos = AdaptadorVehiculo.ObtenerListaVehiculos();
        for (Vehiculo vehiculo:lista_vehiculos) {
            if(_placa.equals(vehiculo.getPlaca())){
                placa.setError(error_PlacaDuplicada);
                placa.requestFocus();
                return false;
            }
        }
        return true;
    }

    public void subir_foto(String id){
        StorageReference child = storageReference.child(id);
        //Uri uri = Uri.parse("android.resource://"+R.class.getPackage().getName()+"/"+foto);
        UploadTask uploadTask = child.putFile(uri);

    }

    public int foto_aleatoria(){
        int foto_seleccionada;
        Random r = new Random();
        foto_seleccionada = r.nextInt(this.fotos.size());
        return fotos.get(foto_seleccionada);
    }

    public void limpiar(View v){
        limpiar();
    }

    public void limpiar(){
        placa.setText("");
        marca.setText("");
        linea.setText("");
        modelo.setText("");
        placa.requestFocus();
        foto.setImageResource(android.R.drawable.ic_menu_gallery);
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarVehiculo.this, MainActivity.class);
        startActivity(i);
    }

    public void seleccionar_foto(View v){
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(i,getString(R.string.titulo_ventana_seleccionar_foto)),1);
    }

    protected void onActivityResult(int requesCode, int resultCode, Intent data) {

        super.onActivityResult(requesCode, resultCode, data);

        if(requesCode == 1){
            uri = data.getData();
            if(uri != null){
                foto.setImageURI(uri);
            }
        }
    }
}
