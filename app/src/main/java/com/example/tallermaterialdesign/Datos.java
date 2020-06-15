package com.example.tallermaterialdesign;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;

public class Datos {
    private static String db= "Vehiculos";
    private static DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private static StorageReference storageReference = FirebaseStorage.getInstance().getReference();
    private static ArrayList<Vehiculo> vehiculos = new ArrayList();

    public static String getId(){
        return databaseReference.push().getKey();
    }

    public static void guardar(Vehiculo p){
        vehiculos.add(p);
        databaseReference.child(db).child(p.getId()).setValue(p);
    }

    public static ArrayList<Vehiculo> obtener(){
        return vehiculos;
    }

    public static void setVehiculos(ArrayList<Vehiculo> vehiculos){
        vehiculos = vehiculos;
    }

    public static void eliminar(Vehiculo p){
        /*for (int i = 0; i < vehiculos.size(); i++) {
            if(vehiculos.get(i).getPlaca().equals(p.getPlaca())){
                vehiculos.remove(i);
                break;
            }
        }*/
        databaseReference.child(db).child(p.getId()).removeValue();
        storageReference.child(p.getId()).delete();
    }
}
