package com.example.tallermaterialdesign;

import android.content.Intent;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
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
        final ArrayList<Vehiculo> vehiculos;
        LinearLayoutManager llm;
        final AdaptadorVehiculo adapter;

        DatabaseReference databaseReference;
        String db = "Vehiculos";
        lstVehiculos = findViewById(R.id.lstVehiculo);
        vehiculos = new ArrayList<>();

        llm = new LinearLayoutManager(this);
        adapter = new AdaptadorVehiculo(vehiculos, this);

        llm.setOrientation(RecyclerView.VERTICAL);
        lstVehiculos.setLayoutManager(llm);
        lstVehiculos.setAdapter(adapter);

        fab = findViewById(R.id.btnAgregar);

        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child(db).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                vehiculos.clear();
                if(dataSnapshot.exists()){
                    for(DataSnapshot snapshot: dataSnapshot.getChildren()){
                        Vehiculo p = snapshot.getValue(Vehiculo.class);
                        vehiculos.add(p);
                    }
                }
                adapter.notifyDataSetChanged();
                Datos.setVehiculos(vehiculos);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

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
        bundle.putString("id", vh.getId());
        bundle.putString("placa", vh.getPlaca());
        bundle.putString("marca", vh.getMarca());
        bundle.putString("linea", vh.getLinea());

        intent = new Intent(MainActivity.this, DetalleVehiculo.class);
        intent.putExtra("datos", bundle);
        startActivity(intent);
    }
}
