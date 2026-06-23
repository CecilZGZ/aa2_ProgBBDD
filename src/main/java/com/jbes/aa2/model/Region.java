package com.jbes.aa2.model;

import java.sql.Date;

public class Region {

    private int id;
    private String nombre;
    private String iniciales;
    private String villanos;
    private String profesor;
    private String videojuegoOrigen;
    private Date fechaLanzamiento;
    private boolean tieneConcursos;

    public Region() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIniciales() {
        return iniciales;
    }

    public void setIniciales(String iniciales) {
        this.iniciales = iniciales;
    }

    public String getVillanos() {
        return villanos;
    }

    public void setVillanos(String villanos) {
        this.villanos = villanos;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getVideojuegoOrigen() {
        return videojuegoOrigen;
    }

    public void setVideojuegoOrigen(String videojuegoOrigen) {
        this.videojuegoOrigen = videojuegoOrigen;
    }

    public Date getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(Date fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public boolean isTieneConcursos() {
        return tieneConcursos;
    }

    public void setTieneConcursos(boolean tieneConcursos) {
        this.tieneConcursos = tieneConcursos;
    }

}
