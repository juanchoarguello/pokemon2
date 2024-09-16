/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import PlaceHolder.PlaceHolder;
import com.contabilidadmetales.contabilidadmetales.ContabilidadMetales;
import com.contabilidadmetales.contabilidadmetales.controlador.CLogin;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author blood
 */
public class FormLogin extends javax.swing.JFrame {

    public FormLogin() {
        
        
        initComponents();
     this.setResizable(false); //para que no modifiquen el ancho y no se agrande
        int ancho = java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
        int alto = java.awt.Toolkit.getDefaultToolkit().getScreenSize().height;//consigo el ancho y alto de la pantalla

        this.setBounds((ancho / 2) - (this.getWidth() / 2), (alto / 2) - (this.getHeight() / 2), 702, 500); //determino el tamaño de la pantalla del sw , ancho , alto
    
         this.setLocationRelativeTo(this);//para q se ponga en el centro
         
         AsignacionImagenes();
         AsignacionPlaceHolder_Jtext();
    }

    public void AsignacionImagenes(){
    ImageIcon icon = new ImageIcon("Imagenes/FormLogin/city.png");
jLabelImagenLogin.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/favicon.png");
jLabelIcono.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/logo.png");
jLabelLogo.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/Ingresar.png");
jButtonIngresar.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/Registro.png");
jButtonRegistro.setIcon(icon);

icon = new ImageIcon("Imagenes/FormLogin/Registro2.png");
jButtonRegistro.setRolloverIcon(icon);
jButtonRegistro.setFocusPainted(false);
    }
    
    public void AsignacionPlaceHolder_Jtext(){
     PlaceHolder placeholder = new PlaceHolder("Ingrese su nombre de usuario", txtUsuario);//aqui le asigon l oq se vera en el text q se borra al escribir
   placeholder = new PlaceHolder("Ingrese su clave", txtContrasenia);
    
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUsuario = new javax.swing.JLabel();
        jLabelContraseña = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        txtContrasenia = new javax.swing.JPasswordField();
        jButtonIngresar = new javax.swing.JButton();
        jButtonRegistro = new javax.swing.JButton();
        jLabelRecuperacion_de_hierro = new javax.swing.JLabel();
        jLabelLogo = new javax.swing.JLabel();
        jLabelImagenLogin = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabelIcono = new javax.swing.JLabel();
        jLabelINICIARSESION = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        jLabelUsuario.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabelUsuario.setText("USUARIO");

        jLabelContraseña.setFont(new java.awt.Font("Arial", 1, 16)); // NOI18N
        jLabelContraseña.setText("CONTRASEÑA");

        txtUsuario.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtUsuario.setBorder(null);

        txtContrasenia.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        txtContrasenia.setBorder(null);
        txtContrasenia.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseniaKeyPressed(evt);
            }
        });

        jButtonIngresar.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButtonIngresar.setText("Ingresar");
        jButtonIngresar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonIngresar.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        jButtonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIngresarActionPerformed(evt);
            }
        });
        jButtonIngresar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButtonIngresarKeyPressed(evt);
            }
        });

        jButtonRegistro.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButtonRegistro.setContentAreaFilled(false);
        jButtonRegistro.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonRegistro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButtonRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistroActionPerformed(evt);
            }
        });

        jLabelRecuperacion_de_hierro.setFont(new java.awt.Font("Comic Sans MS", 1, 20)); // NOI18N
        jLabelRecuperacion_de_hierro.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRecuperacion_de_hierro.setText("Recuperación de hierro");

        jLabelINICIARSESION.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabelINICIARSESION.setText("INICIAR SESIÓN");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabelINICIARSESION))
                        .addGap(114, 114, 114)
                        .addComponent(jButtonRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelUsuario)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelContraseña)
                    .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jLabelRecuperacion_de_hierro, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabelImagenLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(75, 75, 75)
                        .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 15, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(jLabelIcono, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(jLabelINICIARSESION))
                            .addComponent(jButtonRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(38, 38, 38)
                        .addComponent(jLabelUsuario)
                        .addGap(21, 21, 21)
                        .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(jLabelContraseña)
                        .addGap(21, 21, 21)
                        .addComponent(txtContrasenia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(11, 11, 11)
                        .addComponent(jButtonIngresar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(230, 230, 230)
                        .addComponent(jLabelRecuperacion_de_hierro))
                    .addComponent(jLabelImagenLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jLabelLogo, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void jButtonRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonRegistroActionPerformed

    private void jButtonIngresarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButtonIngresarKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonIngresarKeyPressed

    private void jButtonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIngresarActionPerformed
        CLogin objetoLogin = new CLogin();
        objetoLogin.validaUsuario(txtUsuario.getText(), txtContrasenia.getText(), this);
    }//GEN-LAST:event_jButtonIngresarActionPerformed

    private void txtContraseniaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseniaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            CLogin objetoLogin = new CLogin();
            objetoLogin.validaUsuario(txtUsuario.getText(), txtContrasenia.getText(), this);

        }
    }//GEN-LAST:event_txtContraseniaKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIngresar;
    private javax.swing.JButton jButtonRegistro;
    private javax.swing.JLabel jLabelContraseña;
    private javax.swing.JLabel jLabelINICIARSESION;
    private javax.swing.JLabel jLabelIcono;
    private javax.swing.JLabel jLabelImagenLogin;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JLabel jLabelRecuperacion_de_hierro;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPasswordField txtContrasenia;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
