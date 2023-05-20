/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.modelo;

public class TipoMovimiento {

    private int id;
    private String descripcion;
    private boolean esGasto;

    public TipoMovimiento(String descripcion, boolean esGasto) {
        this.descripcion = descripcion;
        this.esGasto = esGasto;
    }

    // Constructor
    public TipoMovimiento(int id, String descripcion, boolean esGasto) {
        this.id = id;
        this.descripcion = descripcion;
        this.esGasto = esGasto;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean esGasto() {
        return esGasto;
    }

    public void setEsGasto(boolean esGasto) {
        this.esGasto = esGasto;
    }
}
