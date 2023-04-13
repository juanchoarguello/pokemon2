/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.modelo;

/**
 *
 * @author narut
 */
public class persona {

    private String nombre, Celular, identificacion, TipoDocumento_nombre,descripcion;

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    private int id;
    private String ArchivoLista;

    public persona(String nombre, String Celular, String identificacion, String TipoDocumento_nombre, String ArchivoLista,String descripcion) {
        this.nombre = nombre;
        this.Celular = Celular;
        this.identificacion = identificacion;
        this.TipoDocumento_nombre = TipoDocumento_nombre;
        this.ArchivoLista = ArchivoLista;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Provedor{" + "nombre=" + nombre + ", Celular=" + Celular + ", identificacion=" + identificacion + ", TipoDocumento_nombre=" + TipoDocumento_nombre + ", id=" + id + '}';
    }

    public persona() {
    }

    public persona(String nombre, String Celular, String identificacion, String TipoDocumento_nombre) {
        this.nombre = nombre;
        this.Celular = Celular;
        this.identificacion = identificacion;
        this.TipoDocumento_nombre = TipoDocumento_nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCelular() {
        return Celular;
    }

    public void setCelular(String Celular) {
        this.Celular = Celular;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipoDocumento_nombre() {
        return TipoDocumento_nombre;
    }

    public void setTipoDocumento_nombre(String TipoDocumento_nombre) {
        this.TipoDocumento_nombre = TipoDocumento_nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArchivoLista() {
        return ArchivoLista;
    }

    public void setArchivoLista(String ArchivoLista) {
        this.ArchivoLista = ArchivoLista;
    }
}
