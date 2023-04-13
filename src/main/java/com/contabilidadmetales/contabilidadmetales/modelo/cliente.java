/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.modelo;

/**
 *
 * @author narut
 */
public class cliente {
    private int id;
    private int cedula;
    private String nombre;
    private String identificacion;
    private String TipoDeDocumento;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getTipoDeDocumento() {
        return TipoDeDocumento;
    }

    public void setTipoDeDocumento(String TipoDeDocumento) {
        this.TipoDeDocumento = TipoDeDocumento;
    }
    
}
