/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.modelo;
import java.sql.*;
import java.util.ArrayList;
public class Inventario {
    private int id;
    private double peso;
    private String descripcion;
    private int idMaterial;
    private double valor;
    private Timestamp fecha;

    public Inventario(int id, double peso, String descripcion, int idMaterial, double valor, Timestamp fecha) {
        this.id = id;
        this.peso = peso;
        this.descripcion = descripcion;
        this.idMaterial = idMaterial;
        this.valor = valor;
        this.fecha = fecha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdMaterial() {
        return idMaterial;
    }

    public void setIdMaterial(int idMaterial) {
        this.idMaterial = idMaterial;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Timestamp getFecha() {
        return fecha;
    }

    public void setFecha(Timestamp fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Inventario{" +
                "id=" + id +
                ", peso=" + peso +
                ", descripcion='" + descripcion + '\'' +
                ", idMaterial=" + idMaterial +
                ", valor=" + valor +
                ", fecha=" + fecha +
                '}';
    }
}

