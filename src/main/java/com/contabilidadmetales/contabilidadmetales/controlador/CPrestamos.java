/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.controlador;

import com.contabilidadmetales.contabilidadmetales.CConexion;
import com.contabilidadmetales.contabilidadmetales.modelo.Prestamos;
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author narut
 */
public class CPrestamos {

    //SELECT idpersonas FROM personas where nombre="asd"
    int Id_Tipo_Persona;
    private CConexion objetoConexion;

    public int Leer_id_X_por_nombre(String Nombre) {
        int PR = 0;
        try {
            Statement ps = null;
            objetoConexion = new CConexion();
            String sql = "SELECT idpersonas,IdTP FROM personas where nombre='" + Nombre + "';";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            rs.next();
            PR = rs.getInt(1);
            Id_Tipo_Persona = rs.getInt(2);
            return PR;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error la consultar el nombre de la pesona");
            JOptionPane.showConfirmDialog(null, e);
            System.out.println(e);
            return 0;
        }

    }

    public void RegistrarPrestamo(String nombre, Double Valor, String Descripcion) {
        try {
            PreparedStatement ps = null;
             objetoConexion = new CConexion(); //                                                                idcuentas, descripcion, Valor, Estado, Cuenta, Id_TipoCuenta, Id_Persona     
            String sql = "insert into prestamos( Id_Persona,Id_Tipo_Persona, Valor, descripcion) values('" + Leer_id_X_por_nombre(nombre) + "','" + Id_Tipo_Persona + "','" + Valor + "','" + Descripcion + "')";
            ps = objetoConexion.estableceConexion().prepareStatement(sql);
            ps.executeUpdate(sql);

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error al incertar el nombre de la pesona");

        }
    }

    public Double SumPrestamo(int Id_Persona) {
        try {
            Statement ps = null;
             objetoConexion = new CConexion();
            String sql = "SELECT SUM(Valor) FROM prestamos where Id_Persona=" + Id_Persona + ";";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            rs.next();
            Double Deuda = rs.getDouble(1);
            return Deuda;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
            return null;
        }

    }

    public ArrayList<Prestamos> LeerPrestamos(int idPrestamos) {
        try {
            Statement ps = null;
             objetoConexion = new CConexion();
            String sql = "SELECT * FROM prestamos where Id_Persona=" + idPrestamos + ";";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            Prestamos PR;
            ArrayList<Prestamos> lista = new ArrayList();
            while (rs.next()) {
                PR = new Prestamos();
                PR.setIdPrestamos(rs.getInt(1));
                PR.setId_Persona(rs.getInt(2));
                PR.setTipopersona(rs.getInt(3));
                PR.setFecha(rs.getString(4));
                PR.setValor(rs.getDouble(5));
                PR.setDescripcion(rs.getString(6));
                PR.setAbono(rs.getDouble(7));
                lista.add(PR);

            }
            // idcuentas,Estado,  Id_TipoCuenta, Id_Persona;

            return lista;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
            return null;
        }
    }

    public void AbonoPrestamos(int u, int Abono, Double suma) {
        int Abonox = Abono;
        ArrayList<Prestamos> gg = LeerPrestamos(u);
        int cont = 0;
        try {
            if (Abonox <= suma) {
                Prestamos pres = gg.get(0);
                while (Abonox >= 0) {
                    if (pres.getValor() >= Abonox) {
                        String descripcion = "el prestamo con ultima actividad el dia : " + pres.getFecha() + " era de " + pres.getValor() + " se abono un total de =" + Abonox;
                        modificarPrestamos(pres.getValor() - Abonox, pres.getIdPrestamos(), descripcion);
                        break;
                    }
                    if (pres.getValor() <= Abonox) {
                        RemoverPrestamos(pres.getIdPrestamos());
                        Abonox = (int) (Abonox - pres.getValor());
                    }
                    cont += 1;
                    pres = gg.get(cont);
                }

            } else {
                JOptionPane.showConfirmDialog(null, "No se puede abonar mas de la suma de las deudas ");
            }

        } catch (Exception e) {
        }
    }
    // DELETE FROM ``prestamos` WHERE (`idPrestamos` = '14');

    public void RemoverPrestamos(int id) {
        try {
            objetoConexion = new CConexion();
            PreparedStatement stmt;
            stmt = objetoConexion.estableceConexion().prepareStatement("DELETE FROM prestamos WHERE idPrestamos =" + id);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showInputDialog(null, "No se han podido actualizar los datos" + ex);
        }
    }

    public void modificarPrestamos(Double Valor, int id, String descripcion) {
        try {
            objetoConexion = new CConexion();
            PreparedStatement stmt;
            stmt = objetoConexion.estableceConexion().prepareStatement("UPDATE prestamos SET Valor=" + Valor + ",descripcion='" + descripcion + "' WHERE idPrestamos=" + id + ";");
            stmt.executeUpdate();
        } catch (SQLException ex) {
            JOptionPane.showInputDialog(null, "No se han podido actualizar los datos" + ex);
        }
    }

    public void listaPrestamos(JTable j) {
        try {
            Statement ps = null;
            objetoConexion = new CConexion();
            String sql = "SELECT * FROM prestamos;";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            persona PR;
            String[] nombres = {"idPrestamos", "Id_Persona", "Id_Tipo_Persona", " fecha", " Valor", "descripcion", "Abono"};
            DefaultTableModel model = new DefaultTableModel(null, nombres);
            while (rs.next()) {
                String[] s = new String[7];
                s[0] = rs.getString(1);
                s[1] = rs.getString(2);
                s[2] = rs.getString(3);
                s[3] = rs.getString(4);
                s[4] = rs.getString(5);
                s[5] = rs.getString(6);
                s[6] = rs.getString(7);
                model.addRow(s);
            }
            j.setModel(model);

        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
        }

    }
}
