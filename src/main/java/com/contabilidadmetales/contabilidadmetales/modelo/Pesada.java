/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.modelo;

/**
 *
 * @author narut
 */
public class Pesada {
   private Integer id;
   private String Material;
   private Double pesada,valor,total;

    @Override
    public String toString() {
        return "id=" + id + ",  " + Material + "  " + pesada + " X " + valor + " = " + total ;
    }
   public String toString2() {
        return Material +" "+ pesada + "X" + valor + "=" + total ;
    }
    public Pesada(Integer id, String Material, Double pesada, Double valor) {
        this.id = id;
        this.Material = Material;
        this.pesada = pesada;
        this.valor = valor;
        this.total=this.pesada*this.valor;
    }

    public String getMaterial() {
        return Material;
        
    }

    public void setMaterial(String Material) {
        this.Material = Material;
    }
  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getPesada() {
        return pesada;
    }

    public void setPesada(Double pesada) {
        this.pesada = pesada;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
        this.total=pesada*valor;
    }

  
   
}
