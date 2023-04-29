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
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import com.itextpdf.text.DocumentException;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import com.contabilidadmetales.contabilidadmetales.controlador.Materiales;
import com.contabilidadmetales.contabilidadmetales.modelo.Inventario;
import com.contabilidadmetales.contabilidadmetales.modelo.Material;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author narut
 */
public class MetalesDeSantander extends javax.swing.JFrame {

    /**
     * Creates new form MetalesDeSantander
     */
    public void leerClientes() {
        TextAreaCuentas.append((String) ListaClientesCuentaCB.getSelectedItem() + "\n" + "\n");
    }
    CPersona conp;
    CPrestamos prestamos;

    public int selec() {
        int TipoDePersona;
        switch (TipoDePersonaComboBox.getSelectedItem().toString()) {
            case "Cliente" ->
                TipoDePersona = 2;
            case "Proveedor" ->
                TipoDePersona = 1;
            case "Usuario" ->
                TipoDePersona = 3;
            default ->
                throw new AssertionError();
        }
        return TipoDePersona;
    }
    private int idUsuario;
    HashMap<String, HashMap<String, Double>> cuenta;

    public MetalesDeSantander(int id) {
        cuenta = new HashMap<String, HashMap<String, Double>>();
        this.idUsuario = id;
        conp = new CPersona();
        initComponents();
        prestamos = new CPrestamos();
        prestamos.listaPrestamos(TPrestamos);
        leerClientes();
        optenerListaProbedor(selec());
        Toolkit t = Toolkit.getDefaultToolkit();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        int dimencionX = (int) screenSize.getWidth();
        int dimenciony = (int) screenSize.getHeight();
        DefaultTableModel model = (DefaultTableModel) TInventario.getModel();
        try {
            llenarTabla(model);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "falla al llenar la sita");
        }
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
        Integer añoSeleccionadoSuperior = Integer.parseInt(ListAños1.getSelectedItem().toString());
        Integer mesSeleccionadoSuperior = Integer.parseInt(ListMeses1.getSelectedItem().toString());
        Integer diaSeleccionadoSuperior = Integer.parseInt(ListDias1.getSelectedItem().toString());
        LocalDate fechaSuperior = LocalDate.of(añoSeleccionadoSuperior, mesSeleccionadoSuperior, diaSeleccionadoSuperior);
        return fechaSuperior;
    }

    public void MEtodoMesesDias(JComboBox ListMeses, JComboBox ListDias) {
        // Obtener el mes actual en un objeto LocalDate
        LocalDate currentDate = LocalDate.now();
        // Obtener el número del mes actual
        int currentMonthValue = currentDate.getMonthValue();
        // Crear una matriz que incluya el mes actual al principio
        String[] months = {currentMonthValue + "", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        // Crear el modelo del JComboBox utilizando la matriz actualizada
        DefaultComboBoxModel<String> monthModel = new DefaultComboBoxModel<>(months);
        // Establecer el modelo en el JComboBox
        ListMeses.setModel(monthModel);

        // Agregar el día actual al principio del JComboBox de días
        int currentDay = currentDate.getDayOfMonth();
        DefaultComboBoxModel<String> modeloDias = new DefaultComboBoxModel<>();
        modeloDias.addElement(String.format("%02d", currentDay));

        for (int i = 1; i <= 31; i++) {

            if (i == currentDay) {
            } else {
                modeloDias.addElement(String.format("%02d", i));
            }
        }
        ListDias.setModel(modeloDias);
    }

    CInventario inve;
    Materiales cc;

    public void llenarTabla(DefaultTableModel modeloTabla) throws SQLException {
        inve = new CInventario();
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();
        // Restar un mes a la fecha actual
        LocalDate fechaUnMesAtras = fechaActual.minusMonths(1);

        cc = new Materiales();
        ArrayList<Inventario> materiales = inve.obtenerMaterialesPorFechas(fechaUnMesAtras, fechaActual);
        inve.obtenerSumaPesoYValorPorFechas(fechaUnMesAtras, fechaActual, JTpeso, JTValor);
        for (Inventario material : materiales) {
            String mate = cc.obtenerNombreMaterial(material.getIdMaterial());
            Object[] fila = {material.getId(), material.getPeso(), material.getDescripcion(), mate, material.getValor(), material.getFecha()};
            modeloTabla.addRow(fila);
        }
    }
 public void llenarTabla2( LocalDate fechaSuperior,  LocalDate fechainferior,int idMaterial) throws SQLException {
        inve = new CInventario();
        cc = new Materiales();
          String [] va={"idInventario", "peso", "Descripcion", "idMaterial", "Valor", "fecha"};
        TInventario.setModel(new DefaultTableModel(va,0));
        ArrayList<Inventario> materiales = inve.obtenerMaterialesPorFechasXidMaterial(TInventario,fechainferior, fechaSuperior,idMaterial);
       /* DefaultTableModel modeloTabl=(DefaultTableModel)TInventario.getModel();
        for (Inventario material : materiales) {
            String mate = cc.obtenerNombreMaterial(material.getIdMaterial());
            Object[] fila = {material.getId(), material.getPeso(), material.getDescripcion(), mate, material.getValor(), material.getFecha()};
            modeloTabl.addRow(fila);
        }
        TInventario.setModel(modeloTabl);*/
    }
    public void MetodoComboboxInventario(JComboBox Combobox2) {
        cc = new Materiales();
        List<Material> materiales = cc.listarMateriales();
        //  List<String> nombresMateriales = new ArrayList<>();
        for (Material material : materiales) {
            Combobox2.addItem(material.getNombre());
        }
        // DefaultComboBoxModel<String> modeloComboBox = new DefaultComboBoxModel<>(nombresMateriales.toArray(new String[0]));
        // Combobox2.setModel(modeloComboBox);
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
    private LocalDate fechaActual;
    private LocalDate fechaAnterior;
    private String material;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        TextPesada = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        TextAreaCuentas = new javax.swing.JTextArea();
        ListMaterialesCB = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        BAgregar = new javax.swing.JButton();
        ListaClientesCuentaCB = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        ValorTotalLabel = new javax.swing.JLabel();
        BotonTerminarCuenta = new javax.swing.JButton();
        jScrollPane12 = new javax.swing.JScrollPane();
        TCuentaXC = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        CheckCancelado = new javax.swing.JComboBox<>();
        DeudaLabel = new javax.swing.JLabel();
        TipoDePersonaComboBox = new javax.swing.JComboBox<>();
        CancelarCuenta = new javax.swing.JButton();
        BotonabonarCC = new javax.swing.JButton();
        tipoCb = new javax.swing.JComboBox<>();
        jButton8 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        ListMaterialesCB2 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jCheckBox2 = new javax.swing.JCheckBox();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        jButton5 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        jCheckBox3 = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jPanel6 = new javax.swing.JPanel();
        BAgregarClientes = new javax.swing.JButton();
        EditarCliente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TClientes = new javax.swing.JTable();
        EditarCliente2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jTextField1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        ValorI = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();
        jButton6 = new javax.swing.JButton();
        Tipo = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        TClientes1 = new javax.swing.JTable();
        BAgregarClientes1 = new javax.swing.JButton();
        EditarCliente1 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        TPrestamos = new javax.swing.JTable();
        jPanel9 = new javax.swing.JPanel();
        jButton7 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        JTpeso = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        TInventario = new javax.swing.JTable();
        jLabel27 = new javax.swing.JLabel();
        JTValor = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        ListMaterialesInve = new javax.swing.JComboBox<>();
        ListDias = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        ListMeses = new javax.swing.JComboBox<>();
        jLabel32 = new javax.swing.JLabel();
        ListAños = new javax.swing.JComboBox<>();
        jLabel29 = new javax.swing.JLabel();
        ListDias1 = new javax.swing.JComboBox<>();
        ListMeses1 = new javax.swing.JComboBox<>();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        ListAños1 = new javax.swing.JComboBox<>();
        jLabel35 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        jLabel1.setText("Metales de santander");

        jTabbedPane1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton1.setText("Edit Lista");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel2.setText("Persona");

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

        TextAreaCuentas.setColumns(20);
        TextAreaCuentas.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        TextAreaCuentas.setRows(5);
        jScrollPane1.setViewportView(TextAreaCuentas);

        ListMaterialesCB.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListMaterialesCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListMaterialesCBActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel3.setText("Material ");

        BAgregar.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BAgregar.setText("Agregar a la cuenta");
        BAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarActionPerformed(evt);
            }
        });

        ListaClientesCuentaCB.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListaClientesCuentaCB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ListaClientesCuentaCBMouseClicked(evt);
            }
        });
        ListaClientesCuentaCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListaClientesCuentaCBActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel4.setText("TOTAL");

        ValorTotalLabel.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ValorTotalLabel.setForeground(new java.awt.Color(255, 0, 0));
        ValorTotalLabel.setText("0.0");

        BotonTerminarCuenta.setBackground(new java.awt.Color(255, 51, 51));
        BotonTerminarCuenta.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonTerminarCuenta.setText("Terminar Cuenta");
        BotonTerminarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonTerminarCuentaActionPerformed(evt);
            }
        });

        TCuentaXC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        TCuentaXC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "a", "b", "c", "d"
            }
        ));
        jScrollPane12.setViewportView(TCuentaXC);

        jLabel7.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel7.setText("CUENTAS POR COBRAR");

        CheckCancelado.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        CheckCancelado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cancelado", "Por Cancelar" }));
        CheckCancelado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckCanceladoActionPerformed(evt);
            }
        });

        DeudaLabel.setFont(new java.awt.Font("Arial Narrow", 3, 48)); // NOI18N
        DeudaLabel.setForeground(new java.awt.Color(255, 255, 255));
        DeudaLabel.setText("500.000 $");

        TipoDePersonaComboBox.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        TipoDePersonaComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Proveedor", "Cliente" }));
        TipoDePersonaComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoDePersonaComboBoxActionPerformed(evt);
            }
        });

        CancelarCuenta.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        CancelarCuenta.setText("Cancelar Cuenta");
        CancelarCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarCuentaActionPerformed(evt);
            }
        });

        BotonabonarCC.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonabonarCC.setText("Abonar");
        BotonabonarCC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonabonarCCActionPerformed(evt);
            }
        });

        tipoCb.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        tipoCb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venta" }));
        tipoCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoCbActionPerformed(evt);
            }
        });

        jButton8.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton8.setText("Edit Lista");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(tipoCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TipoDePersonaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ListaClientesCuentaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ListMaterialesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TextPesada, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BAgregar)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(DeudaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(170, 170, 170)
                                .addComponent(BotonabonarCC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CancelarCuenta)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ValorTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(CheckCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 248, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotonTerminarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 761, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton8)
                                        .addGap(177, 177, 177)))
                                .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 591, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(16, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(DeudaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonabonarCC)
                    .addComponent(CancelarCuenta))
                .addGap(844, 844, 844))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tipoCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TipoDePersonaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(ListaClientesCuentaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(31, 31, 31)
                .addComponent(jButton8)
                .addGap(35, 35, 35)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ListMaterialesCB, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TextPesada)
                    .addComponent(BAgregar))
                .addGap(10, 10, 10)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonTerminarCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ValorTotalLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CheckCancelado, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cuenta", jPanel2);

        jLabel13.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel13.setText("Material ");

        ListMaterialesCB2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListMaterialesCB2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListMaterialesCB2ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel16.setText("Filtros");

        jCheckBox2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jCheckBox2.setText("In / eg");

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane6.setViewportView(jTextArea2);

        jLabel17.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel17.setText("Fecha");

        jLabel18.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel18.setText("Valor");

        jTextArea4.setColumns(20);
        jTextArea4.setRows(5);
        jScrollPane8.setViewportView(jTextArea4);

        jLabel19.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel19.setText("Material / otros");

        jTextArea5.setColumns(20);
        jTextArea5.setRows(5);
        jScrollPane9.setViewportView(jTextArea5);

        jButton5.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton5.setText("Aplicar Filtro");

        jLabel20.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel20.setText("Decripcion");

        jTextArea6.setColumns(20);
        jTextArea6.setRows(5);
        jScrollPane10.setViewportView(jTextArea6);

        jCheckBox3.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jCheckBox3.setText("otros");

        jLabel21.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel21.setText("Fecha");

        jLabel22.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel22.setText("Valor");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(113, 113, 113)
                        .addComponent(jLabel18)
                        .addGap(127, 127, 127)
                        .addComponent(jLabel19)
                        .addGap(97, 97, 97)
                        .addComponent(jLabel20)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel16)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ListMaterialesCB2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel21)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel22)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(149, Short.MAX_VALUE))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextField4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(ListMaterialesCB2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jCheckBox2)
                        .addComponent(jCheckBox3)))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(858, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Ingresos / Egresos", jPanel3);

        BAgregarClientes.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BAgregarClientes.setText("Agregar Persona");
        BAgregarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarClientesActionPerformed(evt);
            }
        });

        EditarCliente.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        EditarCliente.setText("Editar Personas");
        EditarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarClienteActionPerformed(evt);
            }
        });

        TClientes.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        TClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nombre", "Celular", "identificacion", "TipoDocumento_nombre", "Descripcion"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, true, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TClientes.setEditingColumn(0);
        TClientes.setEditingRow(0);
        jScrollPane2.setViewportView(TClientes);

        EditarCliente2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        EditarCliente2.setText("Eliminar Proveedor");
        EditarCliente2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EditarCliente2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(BAgregarClientes)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditarCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditarCliente2))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(457, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BAgregarClientes)
                    .addComponent(EditarCliente)
                    .addComponent(EditarCliente2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(869, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Personas", jPanel6);

        jLabel5.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel5.setText("Descripcion del gasto ");

        jLabel6.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel6.setText("Valor");

        ValorI.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ValorI.setText("0.0 $");

        jLabel9.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel9.setText("Caja Capital");

        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jTextArea3.setRows(5);
        jScrollPane5.setViewportView(jTextArea3);

        jToggleButton1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jToggleButton1.setText("INGRESO DEL DIA ");

        jToggleButton2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jToggleButton2.setText("EGRESO DEL DIA");

        jButton6.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton6.setText("Agregar PYG");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        Tipo.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Gasto", "IIngreso" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(259, 259, 259)
                                        .addComponent(jLabel5))
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(ValorI, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton6)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jToggleButton2)
                                    .addComponent(jToggleButton1)))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 1211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(615, 615, 615)
                        .addComponent(jLabel9)))
                .addContainerGap(157, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel5)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(ValorI, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(27, 27, 27)
                                .addComponent(jLabel9))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(jToggleButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jToggleButton1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(443, 443, 443))
        );

        jTabbedPane1.addTab("Caja menor", jPanel5);

        TClientes1.setFont(new java.awt.Font("Arial", 3, 18)); // NOI18N
        TClientes1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "idcliente", "nombre", "Cedula", "identificacion", "TipoDocumento_nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane7.setViewportView(TClientes1);

        BAgregarClientes1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BAgregarClientes1.setText("Agregar Clientes");

        EditarCliente1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        EditarCliente1.setText("Editar Clientes");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 917, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(BAgregarClientes1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EditarCliente1)))
                .addContainerGap(428, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BAgregarClientes1)
                    .addComponent(EditarCliente1))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(843, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Facturacion", jPanel8);

        jButton2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton2.setText("Registrar prestamo");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton3.setText("Actualizar Estado Prestamo");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton4.setText("Cancelar Prestamo ");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        TPrestamos.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        TPrestamos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane4.setViewportView(TPrestamos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 1271, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4)))
                .addContainerGap(103, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 612, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(721, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Prestamos", jPanel4);

        jButton7.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton7.setText("AplicarFiltro");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel26.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel26.setText("Kilogramos (Kg)");

        JTpeso.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N

        TInventario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idInventario", "peso", "#Cuenta", "Material", "Valor", "fecha"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(TInventario);

        jLabel27.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel27.setText("Valor (COP $)");

        JTValor.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N

        jLabel28.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N

        ListMaterialesInve.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListMaterialesInve.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione" }));

        ListDias.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListDias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListDiasActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel25.setText("Dia");

        jLabel31.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel31.setText("mes");

        ListMeses.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListMesesActionPerformed(evt);
            }
        });

        jLabel32.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel32.setText("año");

        ListAños.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListAños.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListAñosActionPerformed(evt);
            }
        });

        jLabel29.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel29.setText("Dia");

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

        jLabel33.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel33.setText("mes");

        jLabel34.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel34.setText("año");

        ListAños1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        ListAños1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListAños1ActionPerformed(evt);
            }
        });

        jLabel35.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel35.setForeground(new java.awt.Color(51, 51, 255));
        jLabel35.setText("hasta :");

        jLabel30.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(51, 51, 255));
        jLabel30.setText("Desde :");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(JTpeso, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(JTValor, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(ListMaterialesInve, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel30)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ListDias, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ListMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(ListAños, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ListDias1, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel33)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ListMeses1, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(ListAños1, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 79, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ListMaterialesInve, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel29, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ListAños, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel32, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ListMeses, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ListDias, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ListDias1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ListMeses1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ListAños1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTpeso, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTValor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 516, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(785, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inventario", jPanel9);

        jLabel10.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel10.setText("Capital :");

        jLabel12.setFont(new java.awt.Font("Arial Narrow", 3, 48)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 204, 51));
        jLabel12.setText("500.000 $");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(533, 533, 533)
                        .addComponent(jLabel1)
                        .addGap(107, 107, 107)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(538, 538, 538))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(jLabel12)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1)
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTabbedPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseClicked
        // TODO add your handling code here:
        listaProbedor = null;
        listaProbedor = conp.listaPersonas(selec());

    }//GEN-LAST:event_jTabbedPane1MouseClicked

    private void EditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarClienteActionPerformed
        // TODO add your handling code here:
        EditarProveedor Editar = new EditarProveedor(listaProbedor.get(TClientes.getSelectedRow()).getId());
        Editar.setVisible(true);
    }//GEN-LAST:event_EditarClienteActionPerformed

    private void BAgregarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarClientesActionPerformed
        // TODO add your handling code here:
        RegistrarProveedor registro = new RegistrarProveedor(TClientes);
        registro.setVisible(true);
    }//GEN-LAST:event_BAgregarClientesActionPerformed

    private void ListMaterialesCB2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMaterialesCB2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListMaterialesCB2ActionPerformed

    private void CheckCanceladoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckCanceladoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckCanceladoActionPerformed

    private void BotonTerminarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonTerminarCuentaActionPerformed
        CCuentas cuenta = new CCuentas();
        String s = (String) ListaClientesCuentaCB.getSelectedItem();
        String[] sa = s.split("-");
        // System.out.println(sa[0]);
        Integer id = Integer.parseInt(sa[0]);
        CPrestamos cP = new CPrestamos();
        Double de = cP.SumPrestamo(id);
        int abono = 0;
        if (de != 0) {
            abono = Integer.parseInt(JOptionPane.showInputDialog(null, "Esta persona tine una deuda de: " + de + " ¿desea cruzar con la cuenta ?"));
            TextAreaCuentas.append("abona " + abono + " a la deuda de " + de);
            ValorTotal = ValorTotal - abono;
            ValorTotalLabel.setText(ValorTotal.toString());
            cP.AbonoPrestamos(id, abono, de);
            prestamos.listaPrestamos(TPrestamos);
        }

        //TextAreaCuentas.append(Cm.toString());
        TextAreaCuentas.append("\n" + "el valor total= " + ValorTotal.toString());
        Cuenta nueva = null;
        if (TipoDePersonaComboBox.getSelectedItem().toString().equals("Proveedor")) {
            switch (CheckCancelado.getSelectedItem().toString()) {
                case "Cancelado":
                    nueva = new Cuenta(1, id, 1, TextAreaCuentas.getText(), ValorTotal);
                    break;
                case "Por Cancelar":
                    nueva = new Cuenta(1, id, 0, TextAreaCuentas.getText(), ValorTotal);
                    break;
                default:
                    throw new AssertionError();
            }
        } else {
            switch (CheckCancelado.getSelectedItem().toString()) {
                case "Cancelado":
                    nueva = new Cuenta(2, id, 1, TextAreaCuentas.getText(), ValorTotal);
                    break;
                case "Por Cancelar":
                    nueva = new Cuenta(2, id, 0, TextAreaCuentas.getText(), ValorTotal);
                    break;
                default:
                    throw new AssertionError();
            }

        }

        JOptionPane.showConfirmDialog(null, "el Valor q se de debe pagar es : " + ValorTotal);
        Double num = 0.0;
        Integer idcuenta = cuenta.registrarCuenta(nueva, num);
        try {
            /* com.contabilidadmetales.contabilidadmetales.Pdf cuentaPdf = new Pdf();
            cuentaPdf.pdf_plano(sa[1] + "cuenta", TextAreaCuentas.getText());
             */
            pdf(idcuenta, TipoDePersonaComboBox.getSelectedItem().toString());

        } catch (SQLException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        }

        TextAreaCuentas.setText("");
        TextPesada.setText("");
        ValorTotalLabel.setText("");
        ValorTotal = 0.0;
    }//GEN-LAST:event_BotonTerminarCuentaActionPerformed
    String listaTx = null;
    int idselec = 0;

    public void pdf(Integer idfac, String tipo) throws SQLException {
        String fac = (String) ListaClientesCuentaCB.getSelectedItem();
        String[] fac2 = fac.split("-");
        Integer numeroidpersona = Integer.parseInt(fac2[0]);
        conp = new CPersona();
        persona pepe = conp.Leerpersonas(numeroidpersona);
        String nombreArchivo = "factura de " + pepe.getNombre() + ".pdf";
        String cliente = pepe.getNombre();
        String direccion = pepe.getDescripcion();
        LocalDate fechaActual = LocalDate.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd 'de' MMMM 'de' yyyy", new Locale("es", "ES"));
        String fecha = fechaActual.format(formatoFecha);
        double importe = Double.parseDouble(ValorTotal.toString());
        Double kilos = 4.0;
        FacturaPdf facturaPdf = new FacturaPdf();
        try {
            facturaPdf.generarFacturaPdf(nombreArchivo, tipo, idfac, cliente, direccion, fecha, this.cuenta, importe, kilos);
        } catch (IOException | DocumentException e) {
            JOptionPane.showConfirmDialog(null, e);
        }
    }


    private void ListaClientesCuentaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaClientesCuentaCBActionPerformed
        try {
            String[] cn = {"id", "fecha", "valor", "cuenta", "abono"};
            TCuentaXC.setModel(new DefaultTableModel(cn, 0));
            CCuentas cuenta = new CCuentas();
            String[] idString = ListaClientesCuentaCB.getSelectedItem().toString().split("-");
            idselec = Integer.parseInt(idString[0]);
            TextAreaCuentas.setText("");
            leerClientes();
            int i = ListaClientesCuentaCB.getSelectedIndex();
            persona so = listaProbedor.get(i);
            listaTx = conp.listaPresios_idpersonas(idselec);
            String[] as = listaTx.split(" ");
            ListMaterialesCB.removeAllItems();
            for (String a : as) {
                ListMaterialesCB.addItem(a);
            }

            switch (TipoDePersonaComboBox.getSelectedItem().toString()) {
                case "Proveedor":

                    cuenta.listaCuentasxc(Integer.parseInt(idString[0]), TCuentaXC, 1);
                    Double Deuda = -1 * cuenta.SumCuentasxd(Integer.parseInt(idString[0]));
                    DeudaLabel.setText(Deuda.toString());
                    DeudaLabel.setForeground(Color.red);
                    break;
                case "Cliente":
                    cuenta.listaCuentasxc(Integer.parseInt(idString[0]), TCuentaXC, 2);
                    Double pago = cuenta.SumCuentasxd(Integer.parseInt(idString[0]));
                    DeudaLabel.setText(pago.toString());
                    DeudaLabel.setForeground(Color.GREEN);
                    break;
                default:
                    throw new AssertionError();
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_ListaClientesCuentaCBActionPerformed

    private void ListaClientesCuentaCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaClientesCuentaCBMouseClicked

    }//GEN-LAST:event_ListaClientesCuentaCBMouseClicked


    private void BAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarActionPerformed
        String material;
        double cantidad;
        double valorx;
        double costo;
        String TextoPesada = this.TextPesada.getText();
        try {
            cantidad = Double.parseDouble(TextoPesada);
            String MAterialSelecc = (String) ListMaterialesCB.getSelectedItem();
            String[] s = MAterialSelecc.split(",");
            material = s[0];
            valorx = Double.parseDouble(s[1]);
            costo = cantidad * valorx;
            TextAreaCuentas.append(material + " : " + cantidad + " kg x " + valorx + " = " + costo + " $\n");
            ValorTotal += costo;
            TextPesada.setText("");
            if (cuenta.containsKey(material)) {
                HashMap<String, Double> info = cuenta.get(material);
                double cantidadActual = info.get("cantidad");
                double costoActual = info.get("costo");
                info.put("cantidad", cantidadActual + cantidad);
                info.put("costo", costoActual + costo);
            } else {
                HashMap<String, Double> info = new HashMap<String, Double>();
                info.put("cantidad", cantidad);
                info.put("costo", costo);
                cuenta.put(material, info);
            }

        } catch (Exception e) {
            TextPesada.setText("");
            JOptionPane.showConfirmDialog(null, "Recuerde q solo se admiten numeros desimales o enteros");
        }
        ValorTotalLabel.setText(ValorTotal.toString());
    }//GEN-LAST:event_BAgregarActionPerformed

    private void ListMaterialesCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMaterialesCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListMaterialesCBActionPerformed

    private void TextPesadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextPesadaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TextPesadaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        ActualizarListaPrecios a = new ActualizarListaPrecios(listaTx, idselec);
        a.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        RegistrarPrestamo ne = new RegistrarPrestamo(TPrestamos);
        ne.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void TipoDePersonaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoDePersonaComboBoxActionPerformed
        // TODO add your handling code here:
        optenerListaProbedor(selec());
        ListaClientesCuentaCB.removeAllItems();
        for (persona object : listaProbedor) {
            ListaClientesCuentaCB.addItem(object.getId() + "-" + object.getNombre());
        }
    }//GEN-LAST:event_TipoDePersonaComboBoxActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        int estado = 1;
        Double valorG = Double.parseDouble(ValorI.getText());
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        CPrestamos c = new CPrestamos();
        DefaultTableModel modelo = (DefaultTableModel) TPrestamos.getModel();
        String aa = (String) modelo.getValueAt(TPrestamos.getSelectedRow(), 0);
        Integer numeroid = Integer.valueOf(aa);
        String fac = (String) modelo.getValueAt(TPrestamos.getSelectedRow(), 1);
        Integer numeroidpersona = Integer.valueOf(fac);
        conp = new CPersona();
        persona pepe = conp.Leerpersonas(numeroidpersona);
        int i = JOptionPane.showConfirmDialog(null, "Esta seguro que desea dar por cancelada esa deuda de " + pepe.getNombre());
        if (i == 0) {
            c.RemoverPrestamos(numeroid);
            JOptionPane.showConfirmDialog(null, "SE HA ELIMINADO CON EXITO");
            TPrestamos.remove(numeroid);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void EditarCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarCliente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarCliente2ActionPerformed

    private void CancelarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarCuentaActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) TCuentaXC.getModel();
            int i = TCuentaXC.getSelectedRow();
            CCuentas nue = new CCuentas();
            Boolean j = nue.modificarCuentaEstado(Integer.parseInt((String) modelo.getValueAt(i, 0)));
            if (j) {
                JOptionPane.showConfirmDialog(null, "Se a actualizado");
            } else {
                JOptionPane.showConfirmDialog(null, "no se a actualizado");
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Nesesita seleccionar una cuenta");
        }

    }//GEN-LAST:event_CancelarCuentaActionPerformed

    private void BotonabonarCCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonabonarCCActionPerformed
        // TODO add your handling code here
        try {
            DefaultTableModel modelo = (DefaultTableModel) TCuentaXC.getModel();
            int i = TCuentaXC.getSelectedRow();
            CCuentas nue = new CCuentas();
            Integer valIN = Integer.parseInt(JOptionPane.showInputDialog("Digite el valor q desia abonar"));
            JOptionPane.showConfirmDialog(null, modelo.getValueAt(i, 2));
            Double valTB = Double.parseDouble((String) modelo.getValueAt(i, 2));
            Double total = valTB - valIN;
            String g = (String) modelo.getValueAt(i, 0);
            Boolean j = nue.modificarCuentaValor(Integer.parseInt(g), total);
            if (j) {
                JOptionPane.showConfirmDialog(null, "Se a actualizado");
            } else {
                JOptionPane.showConfirmDialog(null, "no se a actualizado");
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Nesesita seleccionar una cuenta");
        }
    }//GEN-LAST:event_BotonabonarCCActionPerformed
    LocalDate FechaAnterior;
    LocalDate FechaActual;
    Filtros nueas;
    private void TextPesadaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TextPesadaKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String material;
            double cantidad;
            double valorx;
            double costo;
            String TextoPesada = this.TextPesada.getText();
            try {
                cantidad = Double.parseDouble(TextoPesada);
                String MAterialSelecc = (String) ListMaterialesCB.getSelectedItem();
                String[] s = MAterialSelecc.split(",");
                material = s[0];
                valorx = Double.parseDouble(s[1]);
                costo = cantidad * valorx;
                TextAreaCuentas.append(material + " : " + cantidad + " kg x " + valorx + " = " + costo + " $\n");
                ValorTotal += costo;
                TextPesada.setText("");
                if (cuenta.containsKey(material)) {
                    HashMap<String, Double> info = cuenta.get(material);
                    double cantidadActual = info.get("cantidad");
                    double costoActual = info.get("costo");
                    info.put("cantidad", cantidadActual + cantidad);
                    info.put("costo", costoActual + costo);
                } else {
                    HashMap<String, Double> info = new HashMap<String, Double>();
                    info.put("cantidad", cantidad);
                    info.put("costo", costo);
                    cuenta.put(material, info);
                }

            } catch (Exception e) {
                TextPesada.setText("");
                JOptionPane.showConfirmDialog(null, "Recuerde q solo se admiten numeros desimales o enteros");
            }
            ValorTotalLabel.setText(ValorTotal.toString());
        }
    }//GEN-LAST:event_TextPesadaKeyPressed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        fechaAnterior = obtenerFechasinferior();
        fechaActual = obtenerFechassuperir();
        material = ListMaterialesInve.getSelectedItem().toString();
        int idMaterialo = cc.obtenerIdMaterialPorNombre(material);
        System.out.println(idMaterialo);
        System.out.println(fechaAnterior);
        System.out.println(fechaActual);
        inve.obtenerSumaPesoYValorPorFechasYMaterial(fechaAnterior, fechaActual, idMaterialo, JTpeso, JTValor);
        String [] va={"idInventario", "peso", "Descripcion", "idMaterial", "Valor", "fecha"};
        TInventario.setModel(new DefaultTableModel(va,0));
        ArrayList<Inventario> ju=inve.obtenerMaterialesPorFechasXidMaterial(TInventario,fechaAnterior, fechaActual, idMaterialo);
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void ListDiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListDiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListDiasActionPerformed

    private void ListMesesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMesesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListMesesActionPerformed

    private void ListAñosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListAñosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListAñosActionPerformed

    private void ListDias1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListDias1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListDias1ActionPerformed

    private void ListMeses1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListMeses1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListMeses1ActionPerformed

    private void ListAños1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListAños1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ListAños1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    Cuentas vv=new Cuentas(tipoCb.getSelectedItem().toString(),TipoDePersonaComboBox.getSelectedItem().toString() );
    vv.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tipoCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoCbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipoCbActionPerformed
    Double ValorTotal = 0.0;
    ArrayList<Cuenta> listaCuenta;
    ArrayList<Cuenta> listaCuenta2;
    ArrayList<persona> listaProbedor;

    public void optenerListaProbedor(int tipopersona) {
        listaProbedor = null;
        listaProbedor = conp.listaPersonas(tipopersona);
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
            TClientes.setModel(model);
            ListaClientesCuentaCB.addItem(provedor.getId() + "-" + provedor.getNombre());
        }
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAgregar;
    private javax.swing.JButton BAgregarClientes;
    private javax.swing.JButton BAgregarClientes1;
    private javax.swing.JButton BotonTerminarCuenta;
    private javax.swing.JButton BotonabonarCC;
    private javax.swing.JButton CancelarCuenta;
    private javax.swing.JComboBox<String> CheckCancelado;
    private javax.swing.JLabel DeudaLabel;
    private javax.swing.JButton EditarCliente;
    private javax.swing.JButton EditarCliente1;
    private javax.swing.JButton EditarCliente2;
    private javax.swing.JTextField JTValor;
    private javax.swing.JTextField JTpeso;
    private javax.swing.JComboBox<String> ListAños;
    private javax.swing.JComboBox<String> ListAños1;
    private javax.swing.JComboBox<String> ListDias;
    private javax.swing.JComboBox<String> ListDias1;
    private javax.swing.JComboBox<String> ListMaterialesCB;
    private javax.swing.JComboBox<String> ListMaterialesCB2;
    private javax.swing.JComboBox<String> ListMaterialesInve;
    private javax.swing.JComboBox<String> ListMeses;
    private javax.swing.JComboBox<String> ListMeses1;
    private javax.swing.JComboBox<String> ListaClientesCuentaCB;
    private javax.swing.JTable TClientes;
    private javax.swing.JTable TClientes1;
    private javax.swing.JTable TCuentaXC;
    private javax.swing.JTable TInventario;
    private javax.swing.JTable TPrestamos;
    private javax.swing.JTextArea TextAreaCuentas;
    private javax.swing.JTextField TextPesada;
    private javax.swing.JComboBox<String> Tipo;
    private javax.swing.JComboBox<String> TipoDePersonaComboBox;
    private javax.swing.JTextField ValorI;
    private javax.swing.JLabel ValorTotalLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JComboBox<String> tipoCb;
    // End of variables declaration//GEN-END:variables
}
