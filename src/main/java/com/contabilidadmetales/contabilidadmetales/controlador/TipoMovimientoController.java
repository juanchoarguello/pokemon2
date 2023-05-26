/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.controlador;

import com.contabilidadmetales.contabilidadmetales.CConexion;
import com.contabilidadmetales.contabilidadmetales.modelo.TipoMovimiento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class TipoMovimientoController {

    private CConexion conexion;

    // Constructor
    public TipoMovimientoController() {
        conexion = new CConexion();
    }

    // Agregar un tipo de movimiento
    public void agregarTipoMovimiento(TipoMovimiento tipoMovimiento) {
        String query = "INSERT INTO tipos_movimientos (descripcion, es_gasto) VALUES (?, ?)";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, tipoMovimiento.getDescripcion());
            statement.setBoolean(2, tipoMovimiento.esGasto());
            statement.executeUpdate();
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null, "Ya esta registrado un tipo de movimiento igual");
        }
    }

    // Obtener todos los tipos de movimientos
    public List<TipoMovimiento> obtenerTiposMovimientos() {
        List<TipoMovimiento> tiposMovimientos = new ArrayList<>();
        String query = "SELECT * FROM tipos_movimientos";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");
                boolean esGasto = resultSet.getBoolean("es_gasto");
                TipoMovimiento tipoMovimiento = new TipoMovimiento(id, descripcion, esGasto);
                tiposMovimientos.add(tipoMovimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposMovimientos;
    }

    // Obtener tipos de gastos
    public List<TipoMovimiento> obtenerTiposGastos() {
        List<TipoMovimiento> tiposGastos = new ArrayList<>();
        String query = "SELECT * FROM tipos_movimientos WHERE es_gasto = ?";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");
                boolean esGasto = resultSet.getBoolean("es_gasto");
                TipoMovimiento tipoMovimiento = new TipoMovimiento(id, descripcion, esGasto);
                tiposGastos.add(tipoMovimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposGastos;
    }

    // Obtener tipos de ingresos
    public List<TipoMovimiento> obtenerTiposIngresos() {
        List<TipoMovimiento> tiposIngresos = new ArrayList<>();
        String query = "SELECT * FROM tipos_movimientos WHERE es_gasto = ?";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setBoolean(1, false);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");
                boolean esGasto = resultSet.getBoolean("es_gasto");
                TipoMovimiento tipoMovimiento = new TipoMovimiento(id, descripcion, esGasto);
                tiposIngresos.add(tipoMovimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposIngresos;

    }

    // Obtener tipos de gastos
    public String[] obtenerTiposGastos2() {
        List<String> tiposGastos = new ArrayList<>();
        String query = "SELECT descripcion FROM tipos_movimientos WHERE es_gasto = ?";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, 1);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String descripcion = resultSet.getString("descripcion");
                tiposGastos.add(descripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposGastos.toArray(new String[0]);
    }

    // Obtener tipos de ingresos
    public String[] obtenerTiposIngresos2() {
        List<String> tiposIngresos = new ArrayList<>();
        String query = "SELECT descripcion FROM tipos_movimientos WHERE es_gasto = ?";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, 0);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String descripcion = resultSet.getString("descripcion");
                tiposIngresos.add(descripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tiposIngresos.toArray(new String[0]);
    }

   

    // Obtener un tipo de ingreso por nombre
    public TipoMovimiento obtenerTipoIngresoPorNombre(String nombre) {
        TipoMovimiento tipoIngreso = null;
        String query = "SELECT * FROM tipos_movimientos WHERE descripcion = ? AND tipos_movimientos = false";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");
                boolean esGasto = resultSet.getBoolean("tipos_movimientos");
                tipoIngreso = new TipoMovimiento(id, descripcion, esGasto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoIngreso;
    }

    // Obtener un tipo de movimiento por nombre
    public TipoMovimiento obtenerTipoMovimientoPorNombre(String nombre) {
        TipoMovimiento tipoMovimiento = null;
        String query = "SELECT * FROM tipos_movimientos WHERE descripcion = ?";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String descripcion = resultSet.getString("descripcion");
                boolean esGasto = resultSet.getBoolean("es_gasto");
                tipoMovimiento = new TipoMovimiento(id, descripcion, esGasto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tipoMovimiento;
    }

    // Eliminar un tipo de movimiento por el nombre
    public void eliminarTipoMovimientoPorNombre(String nombre) {
        String query = "DELETE FROM tipos_movimientos WHERE descripcion = ?";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, nombre);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
