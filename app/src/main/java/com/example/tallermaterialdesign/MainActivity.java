package com.example.tallermaterialdesign;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdaptadorVehiculo.OnVehiculoClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab;
        RecyclerView lstVehiculos;
        ArrayList<Vehiculo> vehiculos;
        LinearLayoutManager llm;
        AdaptadorVehiculo adapter;

        lstVehiculos = findViewById(R.id.lstVehiculo);
        vehiculos = Datos.obtener();
        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorVehiculo(vehiculos, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstVehiculos.setLayoutManager(llm);
        lstVehiculos.setAdapter(adapter);

        fab = findViewById(R.id.btnAgregar);
    }

    public void agregar(View v){
        Intent i;
        i = new Intent(MainActivity.this, AgregarVehiculo.class);
        startActivity(i);
        //finish();
    }

    @Override
    public void onVehiculoClick(Vehiculo vh) {
        Intent intent;
        Bundle bundle;

        bundle = new Bundle();
        bundle.putString("placa", vh.getPlaca());
        bundle.putString("marca", vh.getMarca());
        bundle.putString("linea", vh.getLinea());
        bundle.putInt("foto", vh.getFoto());

        intent = new Intent(MainActivity.this, DetalleVehiculo.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);
        //finish();
    }
}
