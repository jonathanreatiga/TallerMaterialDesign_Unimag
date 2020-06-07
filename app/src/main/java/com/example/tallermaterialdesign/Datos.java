package com.example.tallermaterialdesign;

import java.util.ArrayList;

public class Datos {
    private static ArrayList<Vehiculo> vehiculos = new ArrayList();

    public static void guardar(Vehiculo p){
        vehiculos.add(p);
    }

    public static ArrayList<Vehiculo> obtener(){
        return vehiculos;
    }

    public static void eliminar(Vehiculo p){
        for (int i = 0; i < vehiculos.size(); i++) {
            if(vehiculos.get(i).getPlaca().equals(p.getPlaca())){
                vehiculos.remove(i);
                break;
            }
        }
    }
}
