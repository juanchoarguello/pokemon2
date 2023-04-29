/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import com.aspose.pdf.internal.imaging.internal.bouncycastle.iana.AEADAlgorithm;
import com.contabilidadmetales.contabilidadmetales.controlador.CPersona;
import com.contabilidadmetales.contabilidadmetales.modelo.Pesada;
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Cuentas extends javax.swing.JFrame {

    ArrayList<Pesada> pesadas;
    ArrayList<persona> ListaPersonas;
    CPersona objetopersona;
    int tipo = 0;

    public void getTipo(String TipoPersona) {
        ListaPersonas=null;
        objetopersona=null;
        switch (TipoPersona) {
            case "Proveedor":
                tipo = 1;
                break;
            case "Cliente":
                tipo = 2;
                break;
            default:
        }
        objetopersona=new CPersona();
         ListaPersonas = objetopersona.listaPersonas(tipo);
          Vector<String> items = new Vector<>();
        for (persona ListaPersona : ListaPersonas) {
            items.add(ListaPersona.getNombre());
        }
        ComboBoxModel<String> aModel = new DefaultComboBoxModel<>(items);
        ListaXclienteCB.setModel(aModel);
       
    }

    public Cuentas(String TipoCuenta,String TipoPersona ) {
        pesadas=new ArrayList<>();
        initComponents();
        objetopersona = new CPersona();
        getTipo(TipoPersona);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }
    
    ArrayList<persona> listaProbedor;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        Cuenta = new javax.swing.JTextArea();
        tipo_compra = new javax.swing.JComboBox<>();
        TipoDePersona = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        ListaXclienteCB = new javax.swing.JComboBox<>();
        editar_lista = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        ListMaterialesCB = new javax.swing.JComboBox<>();
        ValorTotalLabel = new javax.swing.JLabel();
        TextPesada = new javax.swing.JTextField();
        CheckCancelado = new javax.swing.JComboBox<>();
        BAgregar = new javax.swing.JButton();
        BotonTerminarCuenta = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Cuenta.setColumns(20);
        Cuenta.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        Cuenta.setRows(5);
        jScrollPane1.setViewportView(Cuenta);

        tipo_compra.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        tipo_compra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venta" }));
        tipo_compra.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipo_compraMouseClicked(evt);
            }
        });

        TipoDePersona.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        TipoDePersona.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Proveedor", "Cliente" }));
        TipoDePersona.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoDePersonaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel2.setText("Persona");

        ListaXclienteCB.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListaXclienteCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaXclienteCBMouseClicked(evt);
            }
        });
        ListaXclienteCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaXclienteCBActionPerformed(evt);
            }
        });

        editar_lista.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        editar_lista.setText("Edit Lista");
        editar_lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editar_listaActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel4.setText("TOTAL");

        jLabel3.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel3.setText("Material ");

        ListMaterialesCB.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListMaterialesCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListMaterialesCBActionPerformed(evt);
            }
        });

        ValorTotalLabel.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ValorTotalLabel.setForeground(new java.awt.Color(255, 0, 0));
        ValorTotalLabel.setText("0.0");

        TextPesada.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        TextPesada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextPesadaActionPerformed(evt);
            }
        });
        TextPesada.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TextPesadaKeyPressed(evt);
            }
        });

        CheckCancelado.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        CheckCancelado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cancelado", "Por Cancelar" }));
        CheckCancelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckCanceladoActionPerformed(evt);
            }
        });

        BAgregar.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BAgregar.setText("Agregar a la cuenta");
        BAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarActionPerformed(evt);
            }
        });

        BotonTerminarCuenta.setBackground(new java.awt.Color(255, 51, 51));
        BotonTerminarCuenta.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonTerminarCuenta.setText("Terminar Cuenta");
        BotonTerminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonTerminarCuentaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ListMaterialesCB, 0, 308, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(TextPesada, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ValorTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CheckCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BotonTerminarCuenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(BAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tipo_compra, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TipoDePersona, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ListaXclienteCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(editar_lista))
                    .addComponent(jScrollPane1))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipo_compra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TipoDePersona, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(ListaXclienteCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(editar_lista))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 429, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ListMaterialesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BAgregar)
                        .addComponent(TextPesada)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ValorTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CheckCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(BotonTerminarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(58, 58, 58))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
 public void optenerListaProbedor(int tipopersona) {
        listaProbedor = null;
        listaProbedor = objetopersona.listaPersonas(tipopersona);
        String[] names = {"Nombre", "Celular", "Identificacion", "Tipo de Documento", "Descripcion"};
        DefaultTableModel model = new DefaultTableModel(names, 0);
        for (persona provedor : listaProbedor) {
            String[] pro = new String[5];
            pro[0] = provedor.getNombre();
            pro[1] = provedor.getCelular();
            pro[2] = provedor.getIdentificacion();
            pro[3] = provedor.getTipoDocumento_nombre();
            pro[4] = provedor.getDescripcion();
            model.addRow(pro);
            // TClientes.setModel(model);
            // ListaClientesCuentaCB.addItem(provedor.getId() + "-" + provedor.getNombre());
        }
    }
    private void TipoDePersonaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoDePersonaActionPerformed
        String TipoPersona=TipoDePersona.getSelectedItem().toString();
       getTipo(TipoPersona);      
    }//GEN-LAST:event_TipoDePersonaActionPerformed

    private void ListaXclienteCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaXclienteCBMouseClicked

    }//GEN-LAST:event_ListaXclienteCBMouseClicked

    private void ListaXclienteCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaXclienteCBActionPerformed
        int cg = ListaXclienteCB.getSelectedIndex();
        getTipo(TipoDePersona.getSelectedItem().toString());
        String lista = ListaPersonas.get(cg).getArchivoLista();
        String[] as = lista.split(" ");
        ListMaterialesCB.removeAllItems();
        for (String a : as) {
            ListMaterialesCB.addItem(a);
        }
        if (Cuenta.getText()!=("")) {
            for (Pesada pesada : pesadas) {
               Double valora=pesada.getValor();
                for (String a : as) {
                    Double valorb=Double.parseDouble(a.split(",")[1]);
                    
                    if (valora.equals(valorb)){
                        
                    }else{
                        pesada.setValor(valorb);
                        JOptionPane.showConfirmDialog(null,pesada.getPesada() );
                    }
                }
            }
        }
    }//GEN-LAST:event_ListaXclienteCBActionPerformed

    private void editar_listaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_listaActionPerformed
          int cg = ListaXclienteCB.getSelectedIndex();
        String lista = ListaPersonas.get(cg).getArchivoLista();
         ActualizarListaPrecios a = new ActualizarListaPrecios(lista, cg);
        a.setVisible(true);
        
    }//GEN-LAST:event_editar_listaActionPerformed

    private void ListMaterialesCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMaterialesCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListMaterialesCBActionPerformed

    private void TextPesadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPesadaActionPerformed
        // TODO add your handling code here:
       
    }//GEN-LAST:event_TextPesadaActionPerformed

    private void TextPesadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPesadaKeyPressed
        // TODO add your handling code here:
           if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
        Double peso=Double.parseDouble(TextPesada.getText());
        String[] vv=ListMaterialesCB.getSelectedItem().toString().split(",");
        String textValor=vv[1];
        Double valor=Double.parseDouble(textValor);
        Pesada nuevaPesada=new Pesada(contado, peso, valor);
        Cuenta.append(nuevaPesada.toString()+"\n");
        pesadas.add(nuevaPesada);
        contado=contado+1;
        TextPesada.setText("");
        total=total+nuevaPesada.getTotal().intValue();
        ValorTotalLabel.setText(total.toString());   
          }
    }//GEN-LAST:event_TextPesadaKeyPressed

    private void CheckCanceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckCanceladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckCanceladoActionPerformed
 int contado =0;
 Integer total=0;
    private void BAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarActionPerformed
      
        Double peso=Double.parseDouble(TextPesada.getText());
        String[] vv=ListMaterialesCB.getSelectedItem().toString().split(",");
        String textValor=vv[1];
        Double valor=Double.parseDouble(textValor);
        Pesada nuevaPesada=new Pesada(contado, peso, valor);
        Cuenta.append(nuevaPesada.toString()+"\n");
        pesadas.add(nuevaPesada);
        contado=contado+1;
        TextPesada.setText("");
        total=total+nuevaPesada.getTotal().intValue();
        ValorTotalLabel.setText(total.toString());
    }//GEN-LAST:event_BAgregarActionPerformed

    private void BotonTerminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonTerminarCuentaActionPerformed

    }//GEN-LAST:event_BotonTerminarCuentaActionPerformed

    private void tipo_compraMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipo_compraMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_compraMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAgregar;
    private javax.swing.JButton BotonTerminarCuenta;
    private javax.swing.JComboBox<String> CheckCancelado;
    private javax.swing.JTextArea Cuenta;
    private javax.swing.JComboBox<String> ListMaterialesCB;
    private javax.swing.JComboBox<String> ListaXclienteCB;
    private javax.swing.JTextField TextPesada;
    private javax.swing.JComboBox<String> TipoDePersona;
    private javax.swing.JLabel ValorTotalLabel;
    private javax.swing.JButton editar_lista;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> tipo_compra;
    // End of variables declaration//GEN-END:variables
}
