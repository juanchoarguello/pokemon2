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

   private Double pesada,valor,total;
   
   public void totalpesada(){
       this.total=this.pesada*this.valor;
       totalpesada();
   }

    public Pesada(Integer id, Double pesada, Double valor) {
        this.id = id;
        this.pesada = pesada;
        this.valor = valor;
        this.total= this.pesada*this.valor;
    }

    @Override
    public String toString() {
        return  "id=" + id + ", " + pesada + ".kg X " + valor + " = " + total ;
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

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
        this.total=pesada*valor;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
   
}
