package com.example.tallermaterialdesign;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Random;

public class AgregarVehiculo extends AppCompatActivity {
    private Spinner cmb_USOS;
    private String[] USO;
    private ArrayAdapter<String> ADAPTER;

    private ArrayList<Integer> fotos;
    private EditText placa, marca, linea, modelo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_vehiculo);

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

        fotos = new ArrayList<>();
        fotos.add(R.drawable.images);
        fotos.add(R.drawable.images2);
        fotos.add(R.drawable.images3);
    }

    public void guardar(View v){
        String _placa, _marca, _linea, _modelo;
        String _uso = cmb_USOS.getSelectedItem().toString();

        int foto;
        Vehiculo vehiculo;
        InputMethodManager imp = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        _placa = placa.getText().toString();
        _marca = marca.getText().toString();
        _linea = linea.getText().toString();
        _modelo = modelo.getText().toString();
        _linea = linea.getText().toString();
        foto = foto_aleatoria();

        vehiculo = new Vehiculo(_placa, _marca, _linea, _modelo, _uso,foto);
        vehiculo.guardar();
        limpiar();
        imp.hideSoftInputFromWindow(placa.getWindowToken(),0);
        Snackbar.make(v, getString(R.string.mensaje_guardado_correcto),Snackbar.LENGTH_LONG).show();
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
    }

    public void onBackPressed(){
        finish();
        Intent i = new Intent(AgregarVehiculo.this, MainActivity.class);
        startActivity(i);
    }
}
