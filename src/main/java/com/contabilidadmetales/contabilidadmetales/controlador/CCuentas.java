/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.controlador;

import com.contabilidadmetales.contabilidadmetales.CConexion;
import com.contabilidadmetales.contabilidadmetales.modelo.Cuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author narut
 */
public class CCuentas {
    private CConexion objetoConexion;
    public int registrarCuenta(Cuenta cuenta, Double abono) {
        try {
            PreparedStatement ps = null;
            objetoConexion = new CConexion();
            String sql = "insert into cuentas(Valor, Estado, Cuenta, Id_TipoCuenta, Id_Persona, abono) values(?, ?, ?, ?, ?, ?)";
            ps = objetoConexion.estableceConexion().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, cuenta.getValor());
            ps.setInt(2, cuenta.getEstado());
            ps.setString(3, cuenta.getCuenta());
            ps.setInt(4, cuenta.getId_TipoCuenta());
            ps.setInt(5, cuenta.getId_Persona());
            ps.setDouble(6, abono);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                int idCuenta = generatedKeys.getInt(1);
                JOptionPane.showConfirmDialog(null, "La cuenta se ha registrado con éxito. ID de la cuenta: " + idCuenta);
                return idCuenta;
            } else {
                JOptionPane.showConfirmDialog(null, "No se ha podido obtener el ID de la cuenta registrada.");
                return 0;
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Error al insertar la cuenta " + e);
            return 0;
        }
    }

    public Cuenta LeerCuenta(int id) {
        try {
            Statement ps = null;
             objetoConexion = new CConexion();
            String sql = "SELECT * FROM cuentas where idcuentas=" + id + ";";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            Cuenta PR = new Cuenta();
            rs.next();
            // idcuentas,Estado,  Id_TipoCuenta, Id_Persona;
            PR.setIdcuentas(1);
            PR.setFecha(rs.getString(2));
            PR.setValor(rs.getDouble(3));
            PR.setEstado(rs.getInt(4));
            PR.setCuenta(rs.getString(5));
            PR.setId_TipoCuenta(rs.getInt(6));
            PR.setId_Persona(rs.getInt(7));
            return PR;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
            return null;
        }

    }

    public ArrayList listaCuentas() {
        try {
            Statement ps = null;
             objetoConexion = new CConexion();
            String sql = "SELECT * FROM cuentas ;";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            ArrayList<Cuenta> listaCuenta = new ArrayList<>();

            Cuenta PR;
            while (rs.next()) {
                PR = new Cuenta();
                PR.setIdcuentas(1);
                PR.setFecha(rs.getString(2));
                PR.setValor(rs.getDouble(3));
                PR.setEstado(rs.getInt(4));
                PR.setCuenta(rs.getString(5));
                PR.setId_TipoCuenta(rs.getInt(6));
                PR.setId_Persona(rs.getInt(7));
                listaCuenta.add(PR);
            }
            return listaCuenta;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
            return null;
        }

    }

    public void lista_de_Cuentas_x_cobrar(int id, JTable t, int Estado) {
        try {
            Statement ps = null;
             objetoConexion = new CConexion();
            String sql = "SELECT * FROM cuentas where Id_Persona=" + id + " and Estado=0 and Estado=" + Estado + ";";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {
                String[] pro = new String[5];
                pro[0] = rs.getString(1);
                pro[1] = rs.getString(2);
                pro[2] = rs.getString(3);
                pro[3] = rs.getString(5);
                 switch (rs.getInt(6)) {
                    case 1:
                        pro[4] = "compra";
                        break;
                    case 2:
                        pro[4] = "venta";
                        break;
                    default:
                        throw new AssertionError();
                }
                DefaultTableModel model = (DefaultTableModel) t.getModel();
                JButton BCancelado = new JButton("Cancelado");
                model.addRow(pro);
            }
            //return listaCuenta;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
        }
        // return null;

    }

    public void lista_de_Cuentas_x_persona(int id, JTable t, int idTipoCuenta) {
        try {
            Statement ps = null;
             objetoConexion = new CConexion();
            String sql = "SELECT * FROM cuentas where Id_Persona=" + id + " and Id_TipoCuenta=" + idTipoCuenta + ";";
            ps = objetoConexion.estableceConexion().createStatement();
            ResultSet rs = ps.executeQuery(sql);
            while (rs.next()) {

                String[] pro = new String[5];
                pro[0] = rs.getString(1);
                pro[1] = rs.getString(2);
                pro[2] = rs.getString(3);
                pro[3] = rs.getString(5);
                switch (rs.getInt(6)) {
                    case 1:
                        pro[4] = "compra";
                        break;
                    case 2:
                        pro[4] = "venta";
                        break;
                    default:
                        throw new AssertionError();
                }

                DefaultTableModel model = (DefaultTableModel) t.getModel();
                JButton BCancelado = new JButton("Cancelado");
                model.addRow(pro);
            }
            //return listaCuenta;
        } catch (Exception e) {
            System.out.println("Error al insertar el USUARIO " + e);
        }
        // return null;

    }

    public Double SumCuentasxd(int id) {
        Double cc = SumCuentas_x_cobrar_Compras(id) + SumCuentas_x_cobrar_Ventas(id);
        return cc;
    }

    public Double SumCuentas_x_cobrar_Ventas(int id) {
        try {
             objetoConexion = new CConexion();
            Connection conexion = objetoConexion.estableceConexion();
            String sql = "SELECT SUM(Valor) FROM cuentas WHERE Id_Persona = ? AND Estado = 0 AND Id_TipoCuenta = 2;";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Double deuda = -rs.getDouble(1);
            return deuda;
        } catch (Exception e) {
            System.out.println("Error al consultar la suma de las cuentas por cobrar de las Ventas: " + e);
            return null;
        }
    }

    public Double SumCuentas_x_cobrar_Compras(int id) {
        try {
            objetoConexion = new CConexion();
            Connection conexion = objetoConexion.estableceConexion();
            String sql = "SELECT SUM(Valor) FROM cuentas WHERE Id_Persona = ? AND Estado = 0 AND Id_TipoCuenta = 1;";
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Double deuda = rs.getDouble(1);
            return deuda;
        } catch (Exception e) {
            System.out.println("Error al consultar la suma de las cuentas por cobrar de las compras: " + e);
            return null;
        }
    }

    public Boolean modificarCuentaEstado(int id) {
        Statement st;
        try {
            objetoConexion = new CConexion();
            PreparedStatement stmt;
            stmt = objetoConexion.estableceConexion().prepareStatement("UPDATE cuentas SET Estado=1 WHERE idcuentas=" + id + ";");
            int retorno = stmt.executeUpdate();
            if (retorno == 1) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException ex) {
            JOptionPane.showInputDialog(null, "No se han podido actualizar los datos " + ex);
            return false;
        }
    }

    public Boolean modificarCuentaValor(int id, Double valor, String cuenta) {
    try {
        objetoConexion = new CConexion();
        PreparedStatement stmt = objetoConexion.estableceConexion().prepareStatement("UPDATE cuentas SET Valor=?, Cuenta=CONCAT(Cuenta, ?) WHERE idcuentas=?");
        stmt.setDouble(1, valor);
        stmt.setString(2, cuenta);
        stmt.setInt(3, id);
        int retorno = stmt.executeUpdate();
        return retorno == 1;
    } catch (SQLException ex) {
        JOptionPane.showInputDialog(null, "No se ha podido actualizar los datos: " + ex);
        return false;
    }
}

}
