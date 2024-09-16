/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.controlador;

import com.contabilidadmetales.contabilidadmetales.CConexion;
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author narut
 */
public class CPersona {

    private CConexion objetoConexion;

    public Boolean registrarProvedor(persona persona, int Tipo, String pasword) {
        try {
            PreparedStatement ps = null;
            objetoConexion = new CConexion();
            String sql = "insert into personas(IdTP,nombre, Celular, identificacion, TipoDocumento_nombre,Archivo,Descripcion,password) values('" + Tipo + "','" + persona.getNombre() + "','" + persona.getCelular() + "','" + persona.getIdentificacion() + "','" + persona.getTipoDocumento_nombre() + "','" + persona.getArchivoLista() + "','" + persona.getDescripcion() + "','" + pasword + "')";
            ps = objetoConexion.estableceConexion().prepareStatement(sql);
            ps.executeUpdate(sql);
            return true;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
            return false;
        }

    }

    public persona Leerpersonas(int id) {
        try {
            Statement ps = null;
            objetoConexion = new CConexion();
            String sql = "SELECT * FROM personas where idpersonas=" + id + ";";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            persona PR = new persona();
            rs.next();
            PR.setId(id);

            PR.setNombre(rs.getString(3));
            PR.setCelular(rs.getString(4));
            PR.setIdentificacion(rs.getString(5));
            PR.setTipoDocumento_nombre(rs.getString(6));
            PR.setArchivoLista(rs.getString(7));
            PR.setDescripcion(rs.getString(8));
            return PR;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
            return null;
        }

    }

    public ArrayList listaPersonas(int TipoPersona) {
        try {
            Statement ps = null;
            objetoConexion = new CConexion();
            String sql = "SELECT * FROM personas where IdTP=" + TipoPersona + ";";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            ArrayList<persona> listaProbedor = new ArrayList<>();
            persona PR;
            while (rs.next()) {
                PR = new persona();
                PR.setId(rs.getInt(1));
                PR.setNombre(rs.getString(3));
                PR.setCelular(rs.getString(4));
                PR.setIdentificacion(rs.getString(5));
                PR.setTipoDocumento_nombre(rs.getString(6));
                PR.setArchivoLista(rs.getString(7));
                PR.setDescripcion(rs.getString(8));
                listaProbedor.add(PR);
            }

            return listaProbedor;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
            return null;
        }

    }
    //SELECT Archivo FROM personas where idpersonas=;

    public String listaPresios_idpersonas(int idpersonas) {
        try {
            Statement ps = null;
            objetoConexion = new CConexion();
            String sql = "SELECT Archivo FROM personas where idpersonas=" + idpersonas + ";";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            rs.next();
            String s = rs.getString(1);
            return s;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
            return null;
        }

    }

    public void modificarArchivo(String lista, int id) {
        Statement st;
        try {
            CConexion objetoConexion = new CConexion();
            PreparedStatement stmt;
            stmt = objetoConexion.estableceConexion().prepareStatement("UPDATE personas SET Archivo='" + lista + "' WHERE idpersonas=" + id + ";");
            int retorno = stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showInputDialog(null, "No se han podido actualizar los datos" + ex);
        }
    }
}
