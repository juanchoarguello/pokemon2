/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.modelo;

import javax.swing.JButton;

/**
 *
 * @author admin
 */

public class Material {

    private int idMateriales;
    private String nombre;
    private String descripcion;
    private double valor;

    public Material() {
    }

    public Material(int idMateriales, String nombre, String descripcion, double valor) {
        this.idMateriales = idMateriales;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
    }

    public int getIdMateriales() {
        return idMateriales;
    }

    public void setIdMateriales(int idMateriales) {
        this.idMateriales = idMateriales;
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

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
