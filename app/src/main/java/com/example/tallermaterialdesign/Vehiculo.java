package com.example.tallermaterialdesign;

public class Vehiculo {
    private String placa;
    private String marca;
    private String linea;
    private String modelo;
    private String uso;
    private int foto;

    public Vehiculo(String placa, String marca, String linea, String modelo, String uso, int foto){
        this.placa = placa;
        this.marca = marca;
        this.linea=linea;
        this.modelo=modelo;
        this.uso=uso;
        this.foto = foto;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getUso() {
        return uso;
    }

    public void setUso(String uso) {
        this.uso = uso;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public void guardar(){
        Datos.guardar(this);
    }

    public void eliminar(){
        Datos.eliminar(this);
    }
}
