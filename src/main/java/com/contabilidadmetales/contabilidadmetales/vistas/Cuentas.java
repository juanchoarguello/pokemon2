/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import com.contabilidadmetales.contabilidadmetales.FacturaPdf;
import com.contabilidadmetales.contabilidadmetales.controlador.CCuentas;
import com.contabilidadmetales.contabilidadmetales.controlador.CInventario;
import com.contabilidadmetales.contabilidadmetales.controlador.CPersona;
import com.contabilidadmetales.contabilidadmetales.controlador.CPrestamos;
import com.contabilidadmetales.contabilidadmetales.modelo.Cuenta;
import com.contabilidadmetales.contabilidadmetales.modelo.Pesada;
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import com.itextpdf.text.DocumentException;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

public class Cuentas extends javax.swing.JFrame {

    ArrayList<persona> listaProbedor;
    ArrayList<Pesada> pesadas;
    ArrayList<persona> ListaPersonas;
    CPersona objetopersona;
    int tipo = 0;

    public void getTipo(String TipoPersona) {
        ListaPersonas = null;
        objetopersona = null;
        switch (TipoPersona) {
            case "Proveedor":
                tipo = 1;
                break;
            case "Cliente":
                tipo = 2;
                break;
            default:
        }
        objetopersona = new CPersona();
        ListaPersonas = objetopersona.listaPersonas(tipo);
        Vector<String> items = new Vector<>();
        for (persona ListaPersona : ListaPersonas) {
            items.add(ListaPersona.getId() + "_" + ListaPersona.getNombre());
        }
        ComboBoxModel<String> aModel = new DefaultComboBoxModel<>(items);
        ListaXclienteCB.setModel(aModel);
    }

    public void ImprimirImpresoraTextoPlano(String fileName,Double total) throws IOException {

        String filePath = fileName + ".txt";
        String titulo = "Metales de santander\n\n" + "numero de cuenta: " + fileName + "\n\n";
        String pesadastx = "";
        for (Pesada pesada : pesadas) {
            pesadastx = pesadastx + pesada.toString2() + "\n";
        }

        // Crear y escribir en el archivo de texto
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(titulo + pesadastx+"Total Cuenta = "+total);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer el archivo de texto
        StringBuilder content = new StringBuilder();
        try ( BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Obtener las impresoras disponibles
        PrintService[] printServices = PrintServiceLookup.lookupPrintServices(null, null);

        // Verificar si hay impresoras disponibles
        if (printServices.length > 0) {
            // Imprimir el archivo de texto
            try {
                // Crear el documento a imprimir
                // Crear el documento a imprimir
                DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                InputStream inputStream = new ByteArrayInputStream(content.toString().getBytes());
                Doc doc = new SimpleDoc(inputStream, flavor, null);

                // Configurar la impresión
                PrintRequestAttributeSet attributeSet = new HashPrintRequestAttributeSet();
                PrintService printService = PrintServiceLookup.lookupDefaultPrintService();
                DocPrintJob printJob = printService.createPrintJob();

                // Enviar el documento a imprimir
                printJob.print(doc, attributeSet);
            } catch (PrintException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No se encontraron impresoras disponibles.");
        }
    }

    public Cuentas(String TipoPersona) {
        pesadas = new ArrayList<>();
        initComponents();
        objetopersona = new CPersona();
        getTipo(TipoPersona);
        this.setDefaultCloseOperation(this.DISPOSE_ON_CLOSE);
    }

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
        String TipoPersona = TipoDePersona.getSelectedItem().toString();
        getTipo(TipoPersona);
    }//GEN-LAST:event_TipoDePersonaActionPerformed

    private void ListaXclienteCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaXclienteCBMouseClicked

    }//GEN-LAST:event_ListaXclienteCBMouseClicked

    private void ListaXclienteCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaXclienteCBActionPerformed
        String Textid = ListaXclienteCB.getSelectedItem().toString().split("_")[0];
        Integer Isid = Integer.parseInt(Textid);
        String lista = objetopersona.listaPresios_idpersonas(Isid);
        String[] as = lista.split(" ");
        ListMaterialesCB.removeAllItems();
        for (String a : as) {
            ListMaterialesCB.addItem(a);
        }
        if (Cuenta.getText() != ("")) {
            Cuenta.setText("");
            for (Pesada pesada : pesadas) {
                Double valora = pesada.getValor();
                String nombrea = pesada.getMaterial();
                for (String a : as) {
                    String[] material = a.split(",");
                    String nombreb = material[0];
                    Double valorb = Double.parseDouble(material[1]);
                    if (valora != valorb && nombrea.equals(nombreb)) {
                        pesada.setValor(valorb);
                    }
                }

                Cuenta.append(pesada.toString() + "\n");
            }
    }//GEN-LAST:event_ListaXclienteCBActionPerformed
    }

    private void editar_listaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editar_listaActionPerformed
        String TextId = ListaXclienteCB.getSelectedItem().toString().split("_")[0];

        Integer identificacion = Integer.parseInt(TextId);
        String lista = ListaPersonas.get(ListaXclienteCB.getSelectedIndex()).getArchivoLista();
        ActualizarListaPrecios a = new ActualizarListaPrecios(lista, identificacion);
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
            Double peso = Double.parseDouble(TextPesada.getText());
            String[] vv = ListMaterialesCB.getSelectedItem().toString().split(",");
            String textValor = vv[1];
            Double valor = Double.parseDouble(textValor);
            String material = vv[0];
            Pesada nuevaPesada = new Pesada(contado, material, peso, valor);
            Cuenta.append(nuevaPesada.toString() + "\n");
            pesadas.add(nuevaPesada);
            contado = contado + 1;
            TextPesada.setText("");
            total = total + nuevaPesada.getTotal().intValue();
            ValorTotalLabel.setText(total.toString());
        }
    }//GEN-LAST:event_TextPesadaKeyPressed

    private void CheckCanceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckCanceladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckCanceladoActionPerformed
    int contado = 0;
    Double total = 0.0;
    private void BAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarActionPerformed

        Double peso = Double.parseDouble(TextPesada.getText());
        String[] vv = ListMaterialesCB.getSelectedItem().toString().split(",");
        String textValor = vv[1];
        Double valor = Double.parseDouble(textValor);
        String material = vv[0];
        Pesada nuevaPesada = new Pesada(contado, material, peso, valor);
        Cuenta.append(nuevaPesada.toString() + "\n");
        pesadas.add(nuevaPesada);
        contado = contado + 1;
        TextPesada.setText("");
        total = total + nuevaPesada.getTotal();
        ValorTotalLabel.setText(total.toString());

    }//GEN-LAST:event_BAgregarActionPerformed

    private void BotonTerminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonTerminarCuentaActionPerformed
        CCuentas cuenta = new CCuentas();
        String s = ListaXclienteCB.getSelectedItem().toString();
        String[] sa = s.split("_");
        // System.out.println(sa[0]);
        Integer id = Integer.parseInt(sa[0]);
        CPrestamos cP = new CPrestamos();
        Double de = cP.SumPrestamo(id);
        int abono = 0;

        if (de != 0) {
            abono = Integer.parseInt(JOptionPane.showInputDialog(null, "Esta persona tine una deuda de: " + de + " ¿desea cruzar con la cuenta ?"));
            Cuenta.append("abona " + abono + " a la deuda de " + de);
            total = total - abono;
            ValorTotalLabel.setText(total.toString());
            cP.AbonoPrestamos(id, abono, de);
            //cP.listaPrestamos(TPrestamos);
        }

        //TextAreaCuentas.append(Cm.toString());
        Cuenta.append("\n" + "el valor total= " + total.toString());
        Cuenta nueva = null;

        if (tipo_compra.getSelectedItem().toString().equals("Compra")) {
            switch (CheckCancelado.getSelectedItem().toString()) {
                case "Cancelado":
                    nueva = new Cuenta(1, id, 1, Cuenta.getText(), total);
                    break;
                case "Por Cancelar":
                    nueva = new Cuenta(1, id, 0, Cuenta.getText(), total);
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            switch (CheckCancelado.getSelectedItem().toString()) {
                case "Cancelado":
                    nueva = new Cuenta(2, id, 1, Cuenta.getText(), total);
                    break;
                case "Por Cancelar":
                    nueva = new Cuenta(2, id, 0, Cuenta.getText(), total);
                    break;
                default:
                    throw new AssertionError();
            }

        }

        JOptionPane.showConfirmDialog(null, "el Valor q se de debe pagar es : " + total);
        Double num = 0.0;
        Integer idcuenta = cuenta.registrarCuenta(nueva, num);

        try {
            pdf(idcuenta);
        } catch (SQLException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        String nombreCuenta = "Cuenta" + idcuenta;
        try {

            ImprimirImpresoraTextoPlano(nombreCuenta,total);
        } catch (IOException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.dispose();
    }//GEN-LAST:event_BotonTerminarCuentaActionPerformed

    public void pdf(Integer idfac) throws SQLException, IOException, DocumentException {
        String fac = (String) ListaXclienteCB.getSelectedItem();
        String[] fac2 = fac.split("_");
        Integer numeroidpersona = Integer.parseInt(fac2[0]);
        objetopersona = new CPersona();
        persona pepe = objetopersona.Leerpersonas(numeroidpersona);

        String cliente = pepe.getNombre();
        String direccion = pepe.getDescripcion();
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fecha = fechaActual.format(formatoFecha);
        String nombreArchivo = "factura de " + pepe.getNombre() + idfac + ".pdf";
        double importe = Double.parseDouble(total.toString());
        Double kilos = 4.0;
        FacturaPdf facturaPdf = new FacturaPdf();
        facturaPdf.generarFacturaPdf(false, nombreArchivo, tipo_compra.getSelectedItem().toString(), idfac, cliente, direccion, fecha, pesadas, importe, kilos);

    }


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
