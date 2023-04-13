/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.modelo;

/**
 *
 * @author narut
 */
public class Cuenta {

    private Integer idcuentas, Estado, Id_TipoCuenta, Id_Persona;

    public void setEstado(Integer Estado) {
        this.Estado = Estado;
    }
    private Double abono;

    public Double getAbono() {
        return abono;
    }

    public void setAbono(Double abono) {
        this.abono = abono;
    }
    private String fecha, Cuenta;

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Cuenta() {
    }

    public Cuenta(Integer Id_TipoCuenta, Integer Id_Persona, int Estado, String Cuenta, Double Valor) {
        this.Id_TipoCuenta = Id_TipoCuenta;
        this.Id_Persona = Id_Persona;
        this.Estado = Estado;
        this.Cuenta = Cuenta;
        this.Valor = Valor;
    }

    public Cuenta(Integer idcuentas, Integer Id_TipoCuenta, String fecha, int Estado, String Cuenta, Double Valor) {
        this.idcuentas = idcuentas;
        this.Id_TipoCuenta = Id_TipoCuenta;
        this.fecha = fecha;
        this.Estado = Estado;
        this.Cuenta = Cuenta;
        this.Valor = Valor;
    }

    public Cuenta(Integer idcuentas, Integer Id_TipoCuenta, Integer Id_Persona, String fecha, int Estado, String Cuenta, Double Valor) {
        this.idcuentas = idcuentas;
        this.Id_TipoCuenta = Id_TipoCuenta;
        this.Id_Persona = Id_Persona;
        this.fecha = fecha;
        this.Estado = Estado;
        this.Cuenta = Cuenta;
        this.Valor = Valor;
    }

    public Integer getIdcuentas() {
        return idcuentas;
    }

    public void setIdcuentas(Integer idcuentas) {
        this.idcuentas = idcuentas;
    }

    public Integer getId_TipoCuenta() {
        return Id_TipoCuenta;
    }

    public void setId_TipoCuenta(Integer Id_TipoCuenta) {
        this.Id_TipoCuenta = Id_TipoCuenta;
    }

    public Integer getId_Persona() {
        return Id_Persona;
    }

    public void setId_Persona(Integer Id_Persona) {
        this.Id_Persona = Id_Persona;
    }

    public Integer getEstado() {
        return Estado;
    }

    public void setEstado(int Estado) {
        this.Estado = Estado;
    }

    public String getCuenta() {
        return Cuenta;
    }

    public void setCuenta(String Cuenta) {
        this.Cuenta = Cuenta;
    }

    public Double getValor() {
        return Valor;
    }

    public void setValor(Double Valor) {
        this.Valor = Valor;
    }
    private Double Valor;
}
