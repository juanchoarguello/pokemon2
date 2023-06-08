/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.contabilidadmetales.contabilidadmetales;

import com.contabilidadmetales.contabilidadmetales.controlador.CPersona;
import com.contabilidadmetales.contabilidadmetales.vistas.FormLogin;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author narut
 */
public class ContabilidadMetales {

    public static void main(String[] args) {
        try {//esto es para que el diseño de la app sea como el del sistema operativo
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(ContabilidadMetales.class.getName()).log(Level.SEVERE, null, ex);
        }
       FormLogin objetoLogin = new FormLogin();
        objetoLogin.setVisible(true);
         //CPersona conp=new CPersona();

    }
}
