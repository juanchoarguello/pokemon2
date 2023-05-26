/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.modelo;

// Clase - MovimientoCajaMenor.java
import java.sql.Date;
import javax.swing.text.StyledEditorKit;

public class MovimientoCajaMenor {
    private int id;
    private Date fecha;
    private int tipoMovimientoId;
    private double monto;
    private String descripcion;
    private Boolean es_gasto;

    public Boolean getEs_gasto() {
        return es_gasto;
    }

    public void setEs_gasto(Boolean es_gasto) {
        this.es_gasto = es_gasto;
    }
    // Constructor

    public MovimientoCajaMenor(Date fecha, int tipoMovimientoId, double monto, String descripcion, Boolean es_gasto) {
        this.fecha = fecha;
        this.tipoMovimientoId = tipoMovimientoId;
        this.monto = monto;
        this.descripcion = descripcion;
        this.es_gasto = es_gasto;
    }
    

    public MovimientoCajaMenor(int id, Date fecha, int tipoMovimientoId, double monto, String descripcion) {
        this.id = id;
        this.fecha = fecha;
        this.tipoMovimientoId = tipoMovimientoId;
        this.monto = monto;
        this.descripcion = descripcion;
    }
    
    // Getters y setters
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    public int getTipoMovimientoId() {
        return tipoMovimientoId;
    }
    
    public void setTipoMovimientoId(int tipoMovimientoId) {
        this.tipoMovimientoId = tipoMovimientoId;
    }
    
    public double getMonto() {
        return monto;
    }
    
    public void setMonto(double monto) {
        this.monto = monto;
    }
    
    public String getDescripcion() {
        return descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
