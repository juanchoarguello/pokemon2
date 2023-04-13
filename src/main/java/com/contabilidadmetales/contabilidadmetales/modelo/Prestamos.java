/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.modelo;

/**
 *
 * @author narut
 */
public class Prestamos {

    int idPrestamos, Id_Persona;
    Double Valor;
    String descripcion;
    String fecha;
    int tipopersona;
    Double abono;

    public Double getAbono() {
        return abono;
    }

    public void setAbono(Double abono) {
        this.abono = abono;
    }


    public int getTipopersona() {
        return tipopersona;
    }

    public void setTipopersona(int tipopersona) {
        this.tipopersona = tipopersona;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Prestamos(int Id_Persona, Double Valor, String descripcion) {
        this.Id_Persona = Id_Persona;
        this.Valor = Valor;
        this.descripcion = descripcion;
    }

    public Prestamos(int Id_Persona, Double Valor) {
        this.Id_Persona = Id_Persona;
        this.Valor = Valor;
    }

    public Prestamos() {
    }

    @Override
    public String toString() {
        return "Prestamos{" + "idPrestamos=" + idPrestamos + ", Id_Persona=" + Id_Persona + ", Valor=" + Valor + ", descripcion=" + descripcion + '}';
    }

    public int getIdPrestamos() {
        return idPrestamos;
    }

    public void setIdPrestamos(int idPrestamos) {
        this.idPrestamos = idPrestamos;
    }

    public int getId_Persona() {
        return Id_Persona;
    }

    public void setId_Persona(int Id_Persona) {
        this.Id_Persona = Id_Persona;
    }

    public Double getValor() {
        return this.Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
