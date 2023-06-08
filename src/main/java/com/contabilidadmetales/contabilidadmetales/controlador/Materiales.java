package com.contabilidadmetales.contabilidadmetales.controlador;

import com.contabilidadmetales.contabilidadmetales.CConexion;
import com.contabilidadmetales.contabilidadmetales.modelo.Material;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Materiales {

    // Método para conectarse a la base de datos
    private  CConexion objetoConexion;
    private Connection conectar() {
        objetoConexion= new CConexion();
        return objetoConexion.estableceConexion();
    }

    // Método para insertar un nuevo material en la tabla 'materiales'
    public boolean insertarMaterial(Material material) {
        boolean resultado = false;
        Connection conexion = conectar();

        String sql = "INSERT INTO materiales (idMateriales, nombre, descripcion, valor) VALUES (?, ?, ?, ?)";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, material.getIdMateriales());
            ps.setString(2, material.getNombre());
            ps.setString(3, material.getDescripcion());
            ps.setDouble(4, material.getValor());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al insertar el material: " + e.getMessage());
        }

        return resultado;
    }

    // Método para eliminar un material de la tabla 'materiales' por su idMateriales
    public boolean eliminarMaterial(int idMateriales) {
        boolean resultado = false;
        Connection conexion = conectar();

        String sql = "DELETE FROM materiales WHERE idMateriales = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setInt(1, idMateriales);
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al eliminar el material: " + e.getMessage());
        }

        return resultado;
    }

    // Método para listar todos los materiales de la tabla 'materiales'
    public List<Material> listarMateriales() {
        List<Material> materiales = new ArrayList<>();
        Connection conexion = conectar();

        String sql = "SELECT * FROM materiales";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Material material = new Material();
                material.setIdMateriales(rs.getInt("idMateriales"));
                material.setNombre(rs.getString("nombre"));
                material.setDescripcion(rs.getString("descripcion"));
                material.setValor(rs.getDouble("valor"));
                materiales.add(material);
            }
        } catch (SQLException e) {
            System.out.println("Error al listar los materiales: " + e.getMessage());
        }

        return materiales;
    }

    // Método para actualizar un material de la tabla 'materiales' por su idMateriales
    public boolean actualizarMaterial(Material material) {
        boolean resultado = false;
        Connection conexion = conectar();

        String sql = "UPDATE materiales SET nombre = ?, descripcion = ?, valor = ? WHERE idMateriales = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, material.getNombre());
            ps.setString(2, material.getDescripcion());
            ps.setDouble(3, material.getValor());
            ps.setInt(4, material.getIdMateriales());
            resultado = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error al actualizar el material: " + e.getMessage());
        }

        return resultado;
    }
    public String obtenerNombreMaterial(int idMaterial) {
    String nombre = "";
    try {
        // Obtener el registro de la tabla de materiales correspondiente al idMaterial
        String sql = "SELECT nombre FROM Materiales WHERE idMateriales = ?";
        PreparedStatement preparedStatement = conectar().prepareStatement(sql);
        preparedStatement.setInt(1, idMaterial);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            nombre = resultSet.getString("nombre");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
    return nombre;
}

    public Material obtenerMaterialPorNombre(String nombreMaterial) {
    Material material = null;
    Connection conexion = conectar();

    String sql = "SELECT * FROM materiales WHERE nombre = ?";

    try {
        PreparedStatement ps = conexion.prepareStatement(sql);
        ps.setString(1, nombreMaterial);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            material = new Material();
            material.setIdMateriales(rs.getInt("idMateriales"));
            material.setNombre(rs.getString("nombre"));
            material.setDescripcion(rs.getString("descripcion"));
            material.setValor(rs.getDouble("valor"));
        }
    } catch (SQLException e) {
        System.out.println("Error al obtener el material por nombre: " + e.getMessage());
    }

    return material;
}

    // Método para obtener el idMateriales de un material por su nombre

        public int obtenerIdMaterialPorNombre(String nombreMaterial) {
        int idMaterial = -1;
        Connection conexion = conectar();

        String sql = "SELECT idMateriales FROM materiales WHERE nombre = ?";

        try {
            PreparedStatement ps = conexion.prepareStatement(sql);
            ps.setString(1, nombreMaterial);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                idMaterial = rs.getInt("idMateriales");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el id del material: " + e.getMessage());
        }

        return idMaterial;
    }
}
