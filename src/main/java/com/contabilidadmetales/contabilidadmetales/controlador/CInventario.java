/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.controlador;

import com.contabilidadmetales.contabilidadmetales.CConexion;
import com.contabilidadmetales.contabilidadmetales.modelo.Inventario;
import java.sql.*;
import java.time.LocalDate;
import java.util.*;
import javax.swing.JTextField;

public class CInventario {

    private Connection conexion;

    private Connection conectar() {
        CConexion objetoConexion = new CConexion();
        return objetoConexion.estableceConexion();
    }

    public CInventario() throws SQLException {
        conexion = conectar();
    }
    public void cerrarConexion() throws SQLException {
        // Cerrar la conexión con la base de datos
        conexion.close();
    }
    public void agregarMaterial(double peso, String descripcion, int idMaterial, double valor) {
        try {
            // Insertar un nuevo registro en la tabla de inventario
            String sql = "INSERT INTO inventario (peso, descripcion, idMaterial, valor) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setDouble(1, peso);
            statement.setString(2, descripcion);
            statement.setInt(3, idMaterial);
            statement.setDouble(4, valor);
            statement.executeUpdate(); // Agregar esta línea para ejecutar el insert
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public void obtenerSumaPesoYValorPorFechas(LocalDate fechaInferior, LocalDate fechaSuperior, JTextField a, JTextField b) {
        try {
          
            String sql = "SELECT SUM(peso) as sumaPeso, SUM(Valor) as sumaValor FROM inventario WHERE fecha BETWEEN ? AND ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setString(1, fechaInferior.atStartOfDay().toString());
            preparedStatement.setString(2, fechaSuperior.atStartOfDay().toString());
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Double sumaPeso = resultSet.getDouble("sumaPeso");
                Double sumaValor = resultSet.getDouble("sumaValor");
                a.setText(sumaPeso.toString());
                b.setText(sumaValor.toString());
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    public ArrayList<Inventario> obtenerMaterialesPorFechas(LocalDate fechaInferior, LocalDate fechaSuperior) {
        ArrayList<Inventario> materiales = new ArrayList<>();
        try {
            // Obtener los registros de la tabla de inventario dentro del rango de fechas especificado
            String sql = "SELECT * FROM inventario WHERE fecha BETWEEN ? AND ?";
            PreparedStatement preparedStatement = conexion.prepareStatement(sql);
            preparedStatement.setTimestamp(1, Timestamp.valueOf(fechaInferior.atStartOfDay()));
            preparedStatement.setTimestamp(2, Timestamp.valueOf(fechaSuperior.atStartOfDay()));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("idInventario");
                double peso = resultSet.getDouble("peso");
                String descripcion = resultSet.getString("descripcion");
                int idMaterial = resultSet.getInt("idMaterial");
                double valor = resultSet.getDouble("valor");
                Timestamp fecha = resultSet.getTimestamp("fecha");
                Inventario material = new Inventario(id, peso, descripcion, idMaterial, valor, fecha);
                materiales.add(material);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materiales;
    }
    public ArrayList<Inventario> obtenerMateriales() {
        ArrayList<Inventario> materiales = new ArrayList<>();
        try {
            // Obtener todos los registros de la tabla de inventario
            String sql = "SELECT * FROM inventario";
            Statement statement = conexion.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                int id = resultSet.getInt("idInventario");
                double peso = resultSet.getDouble("peso");
                String descripcion = resultSet.getString("descripcion");
                int idMaterial = resultSet.getInt("idMaterial");
                double valor = resultSet.getDouble("valor");
                Timestamp fecha = resultSet.getTimestamp("fecha");
                Inventario material = new Inventario(id, peso, descripcion, idMaterial, valor, fecha);
                materiales.add(material);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return materiales;
    }
    public boolean eliminarMaterial(int id) {
        try {
            // Eliminar un registro de la tabla de inventario
            String sql = "DELETE FROM inventario WHERE idInventario = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setInt(1, id);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public boolean actualizarMaterial(int id, double peso, String descripcion, int idMaterial, double valor, Timestamp fecha) {
        try {
            // Actualizar un registro de la tabla de inventario
            String sql = "UPDATE inventario SET peso = ?, descripcion = ?, Material_idMaterial = ?, valor = ?, fecha = ? WHERE idInventario = ?";
            PreparedStatement statement = conexion.prepareStatement(sql);
            statement.setDouble(1, peso);
            statement.setString(2, descripcion);
            statement.setInt(3, idMaterial);
            statement.setDouble(4, valor);
            statement.setTimestamp(5, fecha);
            statement.setInt(6, id);
            int filasAfectadas = statement.executeUpdate();
            if (filasAfectadas > 0) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public ArrayList<Inventario> obtenerMaterialesPorFechasXidMaterial(LocalDate fechaInferior, LocalDate fechaSuperior, int idMaterial) {
    ArrayList<Inventario> materiales = new ArrayList<>();
    try {
        // Obtener los registros de la tabla de inventario dentro del rango de fechas y para el idMaterial especificados
        String sql = "SELECT * FROM inventario WHERE fecha BETWEEN ? AND ? AND idMaterial = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setTimestamp(1, Timestamp.valueOf(fechaInferior.atStartOfDay()));
        preparedStatement.setTimestamp(2, Timestamp.valueOf(fechaSuperior.atStartOfDay()));
        preparedStatement.setInt(3, idMaterial);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            int id = resultSet.getInt("idInventario");
            double peso = resultSet.getDouble("peso");
            String descripcion = resultSet.getString("descripcion");
            double valor = resultSet.getDouble("valor");
            Timestamp fecha = resultSet.getTimestamp("fecha");
            Inventario material = new Inventario(id, peso, descripcion, idMaterial, valor, fecha);
            materiales.add(material);
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return materiales;
}

    public void obtenerSumaPesoYValorPorFechasYMaterial(LocalDate fechaInferior, LocalDate fechaSuperior, int idMaterial, JTextField a, JTextField b) {
    try {
        String sql = "SELECT SUM(peso) as sumaPeso, SUM(Valor) as sumaValor FROM inventario WHERE fecha BETWEEN ? AND ? AND idMaterial = ?";
        PreparedStatement preparedStatement = conexion.prepareStatement(sql);
        preparedStatement.setString(1, fechaInferior.atStartOfDay().toString());
        preparedStatement.setString(2, fechaSuperior.atStartOfDay().toString());
        preparedStatement.setInt(3, idMaterial);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            Double sumaPeso = resultSet.getDouble("sumaPeso");
            Double sumaValor = resultSet.getDouble("sumaValor");
            a.setText(sumaPeso.toString());
            b.setText(sumaValor.toString());
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}

}
