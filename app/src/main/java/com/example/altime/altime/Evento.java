package com.example.altime.altime;

public class Evento {

    private String nombre;
    private String descripcion;
    private String fechacreacion;
    private String diasrestantes;

    public Evento() {

    }

    public Evento(String nombre, String fechacreacion, String diasrestantes) {

    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDiasrestantes() {
        return diasrestantes;
    }

    public void setDiasrestantes(String diasrestantes) {
        this.diasrestantes = diasrestantes;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }
}
