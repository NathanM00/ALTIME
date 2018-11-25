package com.example.altime.altime;

public class Evento {

    public String nombre;
    public String descripcion;
    public String fechacreacion;
    public String diasrestantes;
    public String uid;

    public Evento() {

    }

    public Evento(String nombre, String descripcion, String fechacreacion, String diasrestantes, String uid) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechacreacion = fechacreacion;
        this.diasrestantes = diasrestantes;
        this.uid = uid;
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

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getDiasrestantes() {
        return diasrestantes;
    }

    public void setDiasrestantes(String diasrestantes) {
        this.diasrestantes = diasrestantes;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
