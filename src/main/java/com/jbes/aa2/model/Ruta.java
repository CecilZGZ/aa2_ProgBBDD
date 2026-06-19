package com.jbes.aa2.model;

public class Ruta {
    private int id;
    private String nombre;
    private boolean tieneAgua;
    private String climaPrimario;
    private int cantidadEntrenadores;
    private String entorno;
    private int nivelMin;
    private int nivelMax;
    private int idRegion;

    public Ruta() {}

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

    public boolean isTieneAgua() {
        return tieneAgua;
    }

    public void setTieneAgua(boolean tieneAgua) {
        this.tieneAgua = tieneAgua;
    }

    public String getClimaPrimario() {
        return climaPrimario;
    }

    public void setClimaPrimario(String climaPrimario) {
        this.climaPrimario = climaPrimario;
    }

    public int getCantidadEntrenadores() {
        return cantidadEntrenadores;
    }

    public void setCantidadEntrenadores(int cantidadEntrenadores) {
        this.cantidadEntrenadores = cantidadEntrenadores;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
    }

    public int getNivelMin() {
        return nivelMin;
    }

    public void setNivelMin(int nivelMin) {
        this.nivelMin = nivelMin;
    }

    public int getNivelMax() {
        return nivelMax;
    }

    public void setNivelMax(int nivelMax) {
        this.nivelMax = nivelMax;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }
}
