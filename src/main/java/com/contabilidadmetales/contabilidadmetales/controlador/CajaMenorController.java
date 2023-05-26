/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.controlador;

// Controlador - CajaMenorController.java
import com.contabilidadmetales.contabilidadmetales.CConexion;
import com.contabilidadmetales.contabilidadmetales.modelo.MovimientoCajaMenor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.StyledEditorKit;

public class CajaMenorController {

    private CConexion conexion;

    // Constructor
    public CajaMenorController() {
        conexion = new CConexion();
    }

    // Agregar un movimiento a la caja menor
    public void agregarMovimientoCajaMenor(MovimientoCajaMenor movimiento, Boolean es_gasto) {
        String query = "INSERT INTO caja_menor (fecha, tipo_movimiento_id, monto, descripcion,es_gasto) VALUES (?, ?, ?, ?,?)";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setDate(1, movimiento.getFecha());
            statement.setInt(2, movimiento.getTipoMovimientoId());
            statement.setDouble(3, movimiento.getMonto());
            statement.setString(4, movimiento.getDescripcion());
            statement.setBoolean(5, es_gasto);
            statement.executeUpdate();
            JOptionPane.showConfirmDialog(null, "Se registro correctamente el movimiento");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Obtener todos los movimientos de la caja menor
    public List<MovimientoCajaMenor> obtenerMovimientosCajaMenor() throws SQLException {
        List<MovimientoCajaMenor> movimientos = new ArrayList<>();
        String query = "SELECT * FROM caja_menor";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                Date fecha = resultSet.getDate("fecha");
                int tipoMovimientoId = resultSet.getInt("tipo_movimiento_id");
                double monto = resultSet.getDouble("monto");
                String descripcion = resultSet.getString("descripcion");
                MovimientoCajaMenor movimiento = new MovimientoCajaMenor(id, fecha, tipoMovimientoId, monto, descripcion);
                movimientos.add(movimiento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movimientos;
    }
    public void obtenerMovimientosCajaMenortabla_x_nombre(JTable tabla,String nombre) {
        com.contabilidadmetales.contabilidadmetales.controlador.TipoMovimientoController tpomovimiento=new TipoMovimientoController();
        com.contabilidadmetales.contabilidadmetales.modelo.TipoMovimiento Movimiento=tpomovimiento.obtenerTipoMovimientoPorNombre(nombre);
        JOptionPane.showConfirmDialog(null, Movimiento.getId());
        String query = "SELECT * FROM caja_menor where tipo_movimiento_id="+Movimiento.getId();
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            String[] nombres = {"id", "fecha", "tipo_movimiento_id", "monto", "descripcion"};
            DefaultTableModel modelo = new DefaultTableModel(nombres, 0);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Date fecha = resultSet.getDate("fecha");
                Integer tipoMovimientoId = resultSet.getInt("tipo_movimiento_id");
                Double monto = resultSet.getDouble("monto");
                String descripcion = resultSet.getString("descripcion");
                String[] row = {id.toString(), fecha.toString(), tipoMovimientoId.toString(), monto.toString(), descripcion};
                modelo.addRow(row);
            }
            tabla.setModel(modelo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
  public void obtenerMovimientosCajaMenortablaIngresos(JTable tabla) {
        String query = "SELECT * FROM metalesdb.caja_menor where es_gasto=1";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            String[] nombres = {"id", "fecha", "tipo_movimiento_id", "monto", "descripcion"};
            DefaultTableModel modelo = new DefaultTableModel(nombres, 0);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Date fecha = resultSet.getDate("fecha");
                Integer tipoMovimientoId = resultSet.getInt("tipo_movimiento_id");
                Double monto = resultSet.getDouble("monto");
                String descripcion = resultSet.getString("descripcion");
                String[] row = {id.toString(), fecha.toString(), tipoMovimientoId.toString(), monto.toString(), descripcion};
                modelo.addRow(row);
            }
            tabla.setModel(modelo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
   public void obtenerMovimientosCajaMenortablaEgresos(JTable tabla) {
        String query = "SELECT * FROM caja_menor where es_gasto=0";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            String[] nombres = {"id", "fecha", "tipo_movimiento_id", "monto", "descripcion"};
            DefaultTableModel modelo = new DefaultTableModel(nombres, 0);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Date fecha = resultSet.getDate("fecha");
                Integer tipoMovimientoId = resultSet.getInt("tipo_movimiento_id");
                Double monto = resultSet.getDouble("monto");
                String descripcion = resultSet.getString("descripcion");
                String[] row = {id.toString(), fecha.toString(), tipoMovimientoId.toString(), monto.toString(), descripcion};
                modelo.addRow(row);
            }
            tabla.setModel(modelo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void obtenerMovimientosCajaMenortabla(JTable tabla) {
        String query = "SELECT * FROM caja_menor";
        try ( Connection connection = conexion.estableceConexion();  PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {
            String[] nombres = {"id", "fecha", "tipo_movimiento_id", "monto", "descripcion"};
            DefaultTableModel modelo = new DefaultTableModel(nombres, 0);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                Date fecha = resultSet.getDate("fecha");
                Integer tipoMovimientoId = resultSet.getInt("tipo_movimiento_id");
                Double monto = resultSet.getDouble("monto");
                String descripcion = resultSet.getString("descripcion");
                String[] row = {id.toString(), fecha.toString(), tipoMovimientoId.toString(), monto.toString(), descripcion};
                modelo.addRow(row);
            }
            tabla.setModel(modelo);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
}
