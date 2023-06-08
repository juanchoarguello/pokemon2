package com.contabilidadmetales.contabilidadmetales.controlador;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.contabilidadmetales.contabilidadmetales.CConexion;
import com.contabilidadmetales.contabilidadmetales.vistas.MetalesDeSantander;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author blood
 */
public class CLogin {
    private CConexion objetoConexion ;
    public void validaUsuario(String usuario, String contrasenia,JFrame j) {
        try {
            ResultSet rs = null;
            PreparedStatement ps = null;
            objetoConexion= new CConexion();
            String consulta = "select * from personas where personas.nombre =(?) and personas.password=(?) and personas.IdTP=3 ;";
            ps = objetoConexion.estableceConexion().prepareStatement(consulta);
            String contra = String.valueOf(contrasenia);
            ps.setString(1, usuario);
            ps.setString(2, contra);
            rs = ps.executeQuery();
            if (rs.next()) {
                int i= rs.getInt(1);   
                MetalesDeSantander objetoMenu = new MetalesDeSantander(i);                
                objetoMenu.setVisible(true);
               // j.setVisible(false);
                j.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "El Usuario es INCORRECTO, VUELVA A INTENTAR");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "ERROR: " + e.toString());            
        }
    }

}
