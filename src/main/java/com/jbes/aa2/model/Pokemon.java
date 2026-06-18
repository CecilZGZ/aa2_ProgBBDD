package com.jbes.aa2.model;

public class Pokemon {

    private int id;
    private int numeroPokedex;
    private String nombre;
    private String primerTipo;
    private String segundoTipo;
    private String generacion;
    private boolean tieneEvolucion;
    private String descripcion;
    private double altura;
    private double peso;
    private int idRegion;
    private int idEvolucionaDe;

    public Pokemon() {
    }

    public Pokemon(int idEvolucionaDe, int idRegion, double pesa, double altura, String descripcion, boolean tieneEvolucion, String generacion, String segundoTipo, String primerTipo, String nombre, int numeroPokedex, int id) {
        this.idEvolucionaDe = idEvolucionaDe;
        this.idRegion = idRegion;
        this.peso = peso;
        this.altura = altura;
        this.descripcion = descripcion;
        this.tieneEvolucion = tieneEvolucion;
        this.generacion = generacion;
        this.segundoTipo = segundoTipo;
        this.primerTipo = primerTipo;
        this.nombre = nombre;
        this.numeroPokedex = numeroPokedex;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumeroPokedex() {
        return numeroPokedex;
    }

    public void setNumeroPokedex(int numeroPokedex) {
        this.numeroPokedex = numeroPokedex;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerTipo() {
        return primerTipo;
    }

    public void setPrimerTipo(String primerTipo) {
        this.primerTipo = primerTipo;
    }

    public String getSegundoTipo() {
        return segundoTipo;
    }

    public void setSegundoTipo(String segundoTipo) {
        this.segundoTipo = segundoTipo;
    }

    public String getGeneracion() {
        return generacion;
    }

    public void setGeneracion(String generacion) {
        this.generacion = generacion;
    }

    public boolean isTieneEvolucion() {
        return tieneEvolucion;
    }

    public void setTieneEvolucion(boolean tieneEvolucion) {
        this.tieneEvolucion = tieneEvolucion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getAltura() {
        return altura;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public int getIdRegion() {
        return idRegion;
    }

    public void setIdRegion(int idRegion) {
        this.idRegion = idRegion;
    }

    public int getIdEvolucionaDe() {
        return idEvolucionaDe;
    }

    public void setIdEvolucionaDe(int idEvolucionaDe) {
        this.idEvolucionaDe = idEvolucionaDe;
    }

}
