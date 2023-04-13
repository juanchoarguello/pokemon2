/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import com.contabilidadmetales.contabilidadmetales.controlador.Materiales;
import com.contabilidadmetales.contabilidadmetales.modelo.Material;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

/**
 *
 * @author narut
 */
public class Filtros extends javax.swing.JFrame {

    /**
     * Creates new form Filtros
     */
    public Filtros() {
        initComponents();
        MetodoComboboxInventario(ListMaterialesInve);
        MetodoAnoComboBox(ListAños);
        MEtodoMesesDias(ListMeses, ListDias);
        MetodoAnoComboBox(ListAños1);
        MEtodoMesesDias(ListMeses1, ListDias1);
    }

    public LocalDate obtenerFechasinferior() {
        int añoSeleccionado = Integer.parseInt(ListAños.getSelectedItem().toString());
        int mesSeleccionado = Integer.parseInt(ListMeses.getSelectedItem().toString());
        int diaSeleccionado = Integer.parseInt(ListDias.getSelectedItem().toString());
        LocalDate fechaInferior = LocalDate.of(añoSeleccionado, mesSeleccionado, diaSeleccionado);
        return fechaInferior;
    }

    public LocalDate obtenerFechassuperir() {
        Integer añoSeleccionadoSuperior = Integer.parseInt( ListAños1.getSelectedItem().toString());
        Integer mesSeleccionadoSuperior = Integer.parseInt( ListMeses1.getSelectedItem().toString());
        Integer diaSeleccionadoSuperior = Integer.parseInt( ListDias1.getSelectedItem().toString());
        LocalDate fechaSuperior = LocalDate.of(añoSeleccionadoSuperior, mesSeleccionadoSuperior, diaSeleccionadoSuperior);
        return fechaSuperior;
    }

   public void MEtodoMesesDias(JComboBox ListMeses, JComboBox ListDias) {
    // Obtener el mes actual en un objeto LocalDate
    LocalDate currentDate = LocalDate.now();
    // Obtener el número del mes actual
    int currentMonthValue = currentDate.getMonthValue();
    // Crear una matriz que incluya el mes actual al principio
    String[] months = {currentMonthValue+"", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
    // Crear el modelo del JComboBox utilizando la matriz actualizada
    DefaultComboBoxModel<String> monthModel = new DefaultComboBoxModel<>(months);
    // Establecer el modelo en el JComboBox
    ListMeses.setModel(monthModel);
    Calendar cal = Calendar.getInstance();
    int day = cal.get(Calendar.DAY_OF_MONTH);

    DefaultComboBoxModel<String> modeloDias = new DefaultComboBoxModel<>();
    for (int i = day; i <= 31; i++) {
        modeloDias.addElement(String.format("%02d", i));
    }
    ListDias.setModel(modeloDias);
}


    public void MetodoAnoComboBox(JComboBox Combobox) {
        DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>();
        Calendar calendar = Calendar.getInstance();
        int anioActual = calendar.get(Calendar.YEAR);
        modeloComboBox.addElement(String.valueOf(anioActual));
        for (Integer i = anioActual - 5; i <= anioActual + 70; i++) {
            if (i != anioActual) {
                modeloComboBox.addElement(i.toString());
            }
        }
        Combobox.setModel(modeloComboBox);
    }

    public void MetodoComboboxInventario(JComboBox Combobox2) {
        Materiales materialesController = new Materiales();
        List<Material> materiales = materialesController.listarMateriales();
      //  List<String> nombresMateriales = new ArrayList<>();
        for (Material material : materiales) {
            Combobox2.addItem(material.getNombre());
        }
       // DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>(nombresMateriales.toArray(new String[0]));
       // Combobox2.setModel(modeloComboBox);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton7 = new javax.swing.JButton();
        ListDias1 = new javax.swing.JComboBox<>();
        ListMeses1 = new javax.swing.JComboBox<>();
        jLabel34 = new javax.swing.JLabel();
        ListAños1 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        ListAños = new javax.swing.JComboBox<>();
        ListMeses = new javax.swing.JComboBox<>();
        jLabel31 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        ListDias = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        ListMaterialesInve = new javax.swing.JComboBox<>();
        jCheckBox5 = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jButton7.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton7.setForeground(new java.awt.Color(51, 51, 255));
        jButton7.setText("Aplicar Fechas");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        ListDias1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListDias1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListDias1ActionPerformed(evt);
            }
        });

        ListMeses1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListMeses1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListMeses1ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel34.setText("año");

        ListAños1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListAños1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListAños1ActionPerformed(evt);
            }
        });

        jLabel33.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel33.setText("mes");

        jLabel27.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel27.setText("Dia");

        jLabel28.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel28.setText("Hasta que dia desea ver el inventario");

        jLabel32.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel32.setText("año");

        ListAños.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListAños.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListAñosActionPerformed(evt);
            }
        });

        ListMeses.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListMesesActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel31.setText("mes");

        jLabel25.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel25.setText("Dia");

        ListDias.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListDiasActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel26.setText("Desde que dia desea ver el inventario");

        jLabel29.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(51, 51, 255));
        jLabel29.setText("                Rango de fechas ");

        jLabel23.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel23.setText("Material ");

        ListMaterialesInve.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListMaterialesInve.setForeground(new java.awt.Color(51, 51, 255));
        ListMaterialesInve.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));
        ListMaterialesInve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListMaterialesInveActionPerformed(evt);
            }
        });

        jCheckBox5.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jCheckBox5.setText("Ingresos / egresos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addGap(99, 99, 99))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(jLabel29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(78, 78, 78)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel25)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ListDias, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel31)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ListMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel32)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ListAños, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ListDias1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(2, 2, 2)
                                        .addComponent(jLabel33)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ListMeses1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel34)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(ListAños1, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(31, 31, 31)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel23)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(ListMaterialesInve, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 427, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jCheckBox5))))
                        .addGap(0, 27, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListMaterialesInve, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ListDias, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListAños, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ListDias1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListMeses1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListAños1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jCheckBox5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ListDias1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListDias1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListDias1ActionPerformed

    private void ListMeses1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMeses1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListMeses1ActionPerformed

    private void ListAños1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListAños1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListAños1ActionPerformed

    private void ListAñosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListAñosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListAñosActionPerformed

    private void ListMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMesesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListMesesActionPerformed

    private void ListDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListDiasActionPerformed
    private LocalDate fechaActual;
    private LocalDate fechaAnterior;
    private String material;
    public LocalDate getFechaActual() {
        return fechaActual;
    }
    public LocalDate getFechaAnterior() {
        return fechaAnterior;
    }
    public String getMaterial() {
        return material;
    }
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed

        fechaActual = obtenerFechasinferior();
        fechaAnterior = obtenerFechassuperir();
        material = ListMaterialesInve.getSelectedItem().toString();
        this.setVisible(false);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void ListMaterialesInveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMaterialesInveActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListMaterialesInveActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ListAños;
    private javax.swing.JComboBox<String> ListAños1;
    private javax.swing.JComboBox<String> ListDias;
    private javax.swing.JComboBox<String> ListDias1;
    private javax.swing.JComboBox<String> ListMaterialesInve;
    private javax.swing.JComboBox<String> ListMeses;
    private javax.swing.JComboBox<String> ListMeses1;
    private javax.swing.JButton jButton7;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    // End of variables declaration//GEN-END:variables
}
