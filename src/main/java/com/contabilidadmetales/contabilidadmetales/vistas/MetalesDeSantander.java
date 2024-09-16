/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales.vistas;

import com.contabilidadmetales.contabilidadmetales.CajaMenorReporteGenerator;
import com.contabilidadmetales.contabilidadmetales.FacturaPdf;
import com.contabilidadmetales.contabilidadmetales.InventarioReportGenerator;
import com.contabilidadmetales.contabilidadmetales.controlador.CCuentas;
import com.contabilidadmetales.contabilidadmetales.controlador.CInventario;
import com.contabilidadmetales.contabilidadmetales.controlador.CPersona;
import com.contabilidadmetales.contabilidadmetales.controlador.CPrestamos;
import com.contabilidadmetales.contabilidadmetales.controlador.CajaMenorController;
import com.contabilidadmetales.contabilidadmetales.modelo.Cuenta;
import com.contabilidadmetales.contabilidadmetales.modelo.persona;
import java.awt.Color;
import java.time.LocalDate;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import com.contabilidadmetales.contabilidadmetales.controlador.Materiales;
import com.contabilidadmetales.contabilidadmetales.controlador.TipoMovimientoController;
import com.contabilidadmetales.contabilidadmetales.modelo.Inventario;
import com.contabilidadmetales.contabilidadmetales.modelo.Material;
import com.contabilidadmetales.contabilidadmetales.modelo.MovimientoCajaMenor;
import com.contabilidadmetales.contabilidadmetales.modelo.Pesada;
import com.contabilidadmetales.contabilidadmetales.modelo.TipoMovimiento;
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.sql.Date;
import java.util.*;
import java.time.ZoneId;
import java.time.Instant;
import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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

/**
 *
 * @author narut
 */
public class MetalesDeSantander extends javax.swing.JFrame {

    /**
     * Creates new form MetalesDeSantander
     */
    FTipoMovimiento TipoMovimientos;
    private RegistrarProveedor registro;
    private RegistrarPrestamo RegistrarPrestamos;
    private CPersona conp;
    private CPrestamos prestamos;
    private CInventario inve;
    private Materiales cc;
    private String listaTx = null;
    private int idselec = 0;

    private FacturaPdf facturaPdf = new FacturaPdf();
    private int idUsuario;
    private LocalDate fechaActual;
    private LocalDate fechaAnterior;
    private String material;

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

    public MetalesDeSantander(int idUsuario) {
        this.idUsuario = idUsuario;
        conp = new CPersona();
        initComponents();
        prestamos = new CPrestamos();
        prestamos.listaPrestamos(TPrestamos);
        optenerListaProbedor(selec());
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        DefaultTableModel model = (DefaultTableModel) TInventario.getModel();
        try {
            llenarTabla(model);
        } catch (SQLException ex) {
            JOptionPane.showConfirmDialog(null, "falla al llenar la sita");
        }
        CajaMenorController caja = new CajaMenorController();
        caja.obtenerMovimientosCajaMenortabla(TablaCajaMenor);
        MetodoComboboxInventario(ListMaterialesInve);

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

    public void llenarTabla2(LocalDate fechaSuperior, LocalDate fechainferior, int idMaterial) throws SQLException {
        inve = new CInventario();
        cc = new Materiales();
        String[] va=  {"idInventario", "peso", "Descripcion", "idMaterial", "Valor", "fecha"};
        TInventario.setModel(new DefaultTableModel(va,0 ));
        ArrayList<Inventario> materiales = inve.obtenerMaterialesPorFechasXidMaterial(TInventario, fechainferior, fechaSuperior, idMaterial);
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

    public void ImprimirImpresoraTextoPlano(String fileName, Double total, ArrayList<Pesada> listaPesadas) throws IOException {

        String filePath = fileName + ".txt";
        String titulo = "Metales de santander\n\n" + "numero de cuenta: " + fileName + "\n\n";
        String pesadastx = "";
        for (Pesada pesada : listaPesadas) {
            pesadastx = pesadastx + pesada.toString2() + "\n";
        }

        // Crear y escribir en el archivo de texto
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            fileWriter.write(titulo + pesadastx + "Total Cuenta = " + total);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Leer el archivo de texto
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        ListaClientesCuentaCB = new javax.swing.JComboBox<>();
        jScrollPane12 = new javax.swing.JScrollPane();
        TCuentaXC = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        DeudaLabel = new javax.swing.JLabel();
        TipoDePersonaComboBox = new javax.swing.JComboBox<>();
        CancelarCuenta = new javax.swing.JButton();
        BotonabonarCC = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane13 = new javax.swing.JScrollPane();
        TCuentaXC1 = new javax.swing.JTable();
        BotonabonarCC1 = new javax.swing.JButton();
        tipo_Cuenta = new javax.swing.JComboBox<>();
        BotonabonarCC2 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        BAgregarClientes = new javax.swing.JButton();
        EditarCliente = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TClientes = new javax.swing.JTable();
        EditarCliente2 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        Descripcion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Valor_dinero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        Tipo = new javax.swing.JComboBox<>();
        TipoMovimiento = new javax.swing.JComboBox<>();
        jButton9 = new javax.swing.JButton();
        EliminarTipoMovimiento = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaCajaMenor = new javax.swing.JTable();
        TipoInforme = new javax.swing.JComboBox<>();
        jButton10 = new javax.swing.JButton();
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
        jButton12 = new javax.swing.JButton();
        Calendarioinferior = new com.toedter.calendar.JDateChooser();
        CalendarioSuperior = new com.toedter.calendar.JDateChooser();
        jPanel7 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTabbedPane1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel2.setText("Persona");

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

        TCuentaXC.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        TCuentaXC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "a", "b", "c", "d"
            }
        ));
        jScrollPane12.setViewportView(TCuentaXC);
        if (TCuentaXC.getColumnModel().getColumnCount() > 0) {
            TCuentaXC.getColumnModel().getColumn(0).setMinWidth(80);
            TCuentaXC.getColumnModel().getColumn(0).setPreferredWidth(80);
            TCuentaXC.getColumnModel().getColumn(0).setMaxWidth(80);
            TCuentaXC.getColumnModel().getColumn(1).setMinWidth(80);
            TCuentaXC.getColumnModel().getColumn(1).setPreferredWidth(80);
            TCuentaXC.getColumnModel().getColumn(1).setMaxWidth(80);
            TCuentaXC.getColumnModel().getColumn(2).setMinWidth(80);
            TCuentaXC.getColumnModel().getColumn(2).setPreferredWidth(80);
            TCuentaXC.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        jLabel7.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel7.setText("CUENTAS POR COBRAR");

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

        jButton8.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton8.setText("Nueva Cuenta");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        TCuentaXC1.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N
        TCuentaXC1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idcuentas", "fecha", "Valor", "Cuenta"
            }
        ));
        jScrollPane13.setViewportView(TCuentaXC1);
        if (TCuentaXC1.getColumnModel().getColumnCount() > 0) {
            TCuentaXC1.getColumnModel().getColumn(0).setMinWidth(80);
            TCuentaXC1.getColumnModel().getColumn(0).setPreferredWidth(80);
            TCuentaXC1.getColumnModel().getColumn(0).setMaxWidth(80);
            TCuentaXC1.getColumnModel().getColumn(1).setMinWidth(80);
            TCuentaXC1.getColumnModel().getColumn(1).setPreferredWidth(80);
            TCuentaXC1.getColumnModel().getColumn(1).setMaxWidth(80);
            TCuentaXC1.getColumnModel().getColumn(2).setMinWidth(80);
            TCuentaXC1.getColumnModel().getColumn(2).setPreferredWidth(80);
            TCuentaXC1.getColumnModel().getColumn(2).setMaxWidth(80);
        }

        BotonabonarCC1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonabonarCC1.setText("Generar Factura");
        BotonabonarCC1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonabonarCC1ActionPerformed(evt);
            }
        });

        tipo_Cuenta.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        tipo_Cuenta.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Compra", "Venta" }));
        tipo_Cuenta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tipo_CuentaMouseClicked(evt);
            }
        });
        tipo_Cuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipo_CuentaActionPerformed(evt);
            }
        });

        BotonabonarCC2.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        BotonabonarCC2.setText("Generar factura");
        BotonabonarCC2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonabonarCC2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(tipo_Cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TipoDePersonaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ListaClientesCuentaCB, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8)
                        .addGap(101, 101, 101)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(DeudaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 39, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 728, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BotonabonarCC1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane12)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(BotonabonarCC)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CancelarCuenta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BotonabonarCC2)
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGap(7, 7, 7)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(TipoDePersonaComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(tipo_Cuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(ListaClientesCuentaCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton8)
                            .addComponent(jLabel7)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(DeudaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BotonabonarCC)
                        .addComponent(CancelarCuenta)
                        .addComponent(BotonabonarCC2))
                    .addComponent(BotonabonarCC1))
                .addContainerGap(2164, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Cuenta", jPanel2);

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
                .addContainerGap(2205, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Personas", jPanel6);

        jLabel5.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel5.setText("Descripcion ");

        jLabel6.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel6.setText("Valor");

        Valor_dinero.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        Valor_dinero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Valor_dineroActionPerformed(evt);
            }
        });
        Valor_dinero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Valor_dineroKeyPressed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel9.setText("Movimientos");

        jButton6.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton6.setText("Agregar Tipo de Movimiento");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        Tipo.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Egresos", "Ingreso" }));
        Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoActionPerformed(evt);
            }
        });

        TipoMovimiento.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        TipoMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoMovimientoActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton9.setText("Agregar Movimiento");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        EliminarTipoMovimiento.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        EliminarTipoMovimiento.setText("Eliminar Tipo de Movimiento");
        EliminarTipoMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EliminarTipoMovimientoActionPerformed(evt);
            }
        });

        TablaCajaMenor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(TablaCajaMenor);

        TipoInforme.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        TipoInforme.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "General", "por movimiento", "por fechas" }));
        TipoInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TipoInformeActionPerformed(evt);
            }
        });

        jButton10.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton10.setText("Generar informe");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(TipoMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, 0)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EliminarTipoMovimiento))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(581, 581, 581)
                        .addComponent(jLabel9))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1364, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Valor_dinero, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(TipoInforme, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)))
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TipoMovimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(EliminarTipoMovimiento)
                        .addComponent(jButton6)
                        .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(7, 7, 7)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Descripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(Valor_dinero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(TipoInforme, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton10))
                .addGap(16, 16, 16)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 466, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(69, 69, 69))
        );

        jTabbedPane1.addTab("Ingresos y egreosos ", jPanel5);

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
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2225, Short.MAX_VALUE))
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

        jButton12.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jButton12.setText("Informe General");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JTpeso, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel28)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel27))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(ListMaterialesInve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Calendarioinferior, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CalendarioSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addComponent(JTValor, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(163, 163, 163)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12)))
                .addContainerGap(259, Short.MAX_VALUE))
            .addComponent(jScrollPane3)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Calendarioinferior, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ListMaterialesInve, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CalendarioSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JTpeso, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JTValor, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 507, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(2127, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Inventario", jPanel9);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1380, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 2732, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Ayuda", jPanel7);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 36)); // NOI18N
        jLabel1.setText("Metales de santander");

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
                .addContainerGap())
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

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        java.util.Date fechaAnterior1 =  Calendarioinferior.getDate();
        java.util.Date fechaActual1 =  CalendarioSuperior.getDate();

        long milliseconds = fechaActual1.getTime();
        long milliseconds2 = fechaAnterior1.getTime();

         fechaActual = LocalDate.ofEpochDay(milliseconds / (24 * 60 * 60 * 1000));
         fechaAnterior = LocalDate.ofEpochDay(milliseconds2 / (24 * 60 * 60 * 1000));

        material = ListMaterialesInve.getSelectedItem().toString();
        int idMaterialo = cc.obtenerIdMaterialPorNombre(material);
        inve.obtenerSumaPesoYValorPorFechasYMaterial(fechaAnterior, fechaActual, idMaterialo, JTpeso, JTValor);
        String[] va=  {"idInventario", "peso", "Descripcion", "idMaterial", "Valor", "fecha"};
        TInventario.setModel(new DefaultTableModel(va,0 ));
        ArrayList<Inventario> inventarioList = inve.obtenerMaterialesPorFechasXidMaterial(TInventario, fechaAnterior, fechaActual, idMaterialo);
        InventarioReportGenerator ss = new InventarioReportGenerator();
        // Generar el informe en PDF
        Double peso = Double.parseDouble(JTpeso.getText());
        Double valorTotal = Double.parseDouble(JTValor.getText());
        ss.generateReport(peso, valorTotal, inventarioList, fechaAnterior, fechaActual, material, "Informe_" + fechaActual + ".pdf");
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODCPrestamoO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) TPrestamos.getModel();
        String aa = (String) modelo.getValueAt(TPrestamos.getSelectedRow(), 0);
        Integer numeroid = Integer.valueOf(aa);
        String fac = (String) modelo.getValueAt(TPrestamos.getSelectedRow(), 1);
        Integer numeroidpersona = Integer.valueOf(fac);
        conp = new CPersona();
        persona pepe = conp.Leerpersonas(numeroidpersona);
        int i = JOptionPane.showConfirmDialog(null, "Esta seguro que desea dar por cancelada esa deuda de " + pepe.getNombre());
        if (i == 0) {
            prestamos.RemoverPrestamos(numeroid);
            JOptionPane.showConfirmDialog(null, "SE HA ELIMINADO CON EXITO");
            TPrestamos.remove(numeroid);
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        RegistrarPrestamos = new RegistrarPrestamo(TPrestamos);
        RegistrarPrestamos.setVisible(true);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        String tipoi = TipoInforme.getSelectedItem().toString();
        CajaMenorReporteGenerator reporte = new CajaMenorReporteGenerator();

    }//GEN-LAST:event_jButton10ActionPerformed

    private void TipoInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoInformeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TipoInformeActionPerformed

    private void EliminarTipoMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EliminarTipoMovimientoActionPerformed
        // TODO add your handling code here:
        TipoMovimientoController nuevo = new TipoMovimientoController();
        nuevo.eliminarTipoMovimientoPorNombre(TipoMovimiento.getSelectedItem().toString());
    }//GEN-LAST:event_EliminarTipoMovimientoActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        String tipom = TipoMovimiento.getSelectedItem().toString();
        String Descripcion = this.Descripcion.getText();
        Double monto = Double.parseDouble(Valor_dinero.getText());
        java.util.Date utilDate = new java.util.Date();
        java.sql.Date fechaActual = new java.sql.Date(utilDate.getTime());
        String Tipoget = Tipo.getSelectedItem().toString();
        CajaMenorController cajamenorcontroller = new CajaMenorController();
        TipoMovimientoController tipomovimientocontrolador = new TipoMovimientoController();
        TipoMovimiento tipomode = tipomovimientocontrolador.obtenerTipoMovimientoPorNombre(tipom);
        Boolean es_gasto = false;
        if (tipom == "Ingresos") {
            es_gasto = true;
        }
        MovimientoCajaMenor modelo = new MovimientoCajaMenor(fechaActual, tipomode.getId(), monto, Descripcion, es_gasto);
        if (Tipoget == "Egresos") {
            cajamenorcontroller.agregarMovimientoCajaMenor(modelo, false);
        } else {
            cajamenorcontroller.agregarMovimientoCajaMenor(modelo, true);
        }
        this.Descripcion.setText("");
        this.Valor_dinero.setText("");
        CajaMenorController caja = new CajaMenorController();
        caja.obtenerMovimientosCajaMenortabla(TablaCajaMenor);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void TipoMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoMovimientoActionPerformed
        // TODO add your handling code here:
        CajaMenorController caja = new CajaMenorController();
        String nombre = TipoMovimiento.getSelectedItem().toString();
        caja.obtenerMovimientosCajaMenortabla_x_nombre(TablaCajaMenor, nombre);
    }//GEN-LAST:event_TipoMovimientoActionPerformed

    private void TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoActionPerformed
        // TODO add your handling code here:
        TipoMovimientoController nuevo = new TipoMovimientoController();
        CajaMenorController caja = new CajaMenorController();
        switch (Tipo.getSelectedItem().toString()) {
            case "Ingreso":
                String[] listaGastos = nuevo.obtenerTiposGastos2();
                DefaultComboBoxModel cx = new DefaultComboBoxModel(listaGastos);
                TipoMovimiento.setModel(cx);
                caja.obtenerMovimientosCajaMenortablaIngresos(TablaCajaMenor);
                break;

            case "Egresos":
                String[] listaIngresos = nuevo.obtenerTiposIngresos2();
                DefaultComboBoxModel cc = new DefaultComboBoxModel(listaIngresos);
                TipoMovimiento.setModel(cc);
                caja.obtenerMovimientosCajaMenortablaEgresos(TablaCajaMenor);
                break;
            default:
                throw new AssertionError();
        }
    }//GEN-LAST:event_TipoActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        FTipoMovimiento objetoFTipoMovimiento = new FTipoMovimiento();
        objetoFTipoMovimiento.setVisible(true);
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void Valor_dineroKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Valor_dineroKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Valor_dineroKeyPressed

    private void Valor_dineroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Valor_dineroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Valor_dineroActionPerformed

    private void EditarCliente2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarCliente2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_EditarCliente2ActionPerformed

    private void EditarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EditarClienteActionPerformed
        // TODO add your handling code here:
        EditarProveedor Editar = new EditarProveedor(listaProbedor.get(TClientes.getSelectedRow()).getId());
        Editar.setVisible(true);
    }//GEN-LAST:event_EditarClienteActionPerformed

    private void BAgregarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarClientesActionPerformed
        // TODO add your handling code here:
        registro = new RegistrarProveedor(TClientes);
        registro.setVisible(true);
    }//GEN-LAST:event_BAgregarClientesActionPerformed

    private void BotonabonarCC2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonabonarCC2ActionPerformed
        // TODO add your handling code here:
        ArrayList<Pesada> listaPesadas = new ArrayList<>();
        String cuenta = null;
        int selectedRow = TCuentaXC.getSelectedRow(); // obtiene el número de fila seleccionado
        Integer value = Integer.parseInt(TCuentaXC.getValueAt(selectedRow, 0).toString()); // obtiene el valor de la primera columna de la fila seleccionada
        Double totalx = Double.parseDouble(TCuentaXC.getValueAt(selectedRow, 2).toString());
        cuenta = TCuentaXC.getValueAt(selectedRow, 3).toString();
        String[] pesadas = cuenta.split("\n");
        for (String pesadaString : pesadas) {
            JOptionPane.showConfirmDialog(null, pesadaString);
            if (pesadaString.isEmpty()) {
                continue; // Ignorar líneas vacías o la línea "el valor total"
            }
            if (pesadaString.startsWith("el valor total")) {
                try {
                    String[] cadena = pesadaString.split("_");

                    String[] cadena1 = cadena[0].split("=");
                    Double totalInicial = Double.parseDouble(cadena1[1]);
                    facturaPdf.setTotalAnteriord(totalInicial);

                    for (String parte : cadena) {
                        if (parte.startsWith("el valor total")) {
                            continue;
                        }
                        String[] partes = parte.split(", ");
                        int id = Integer.parseInt(partes[0].split("=")[1]);
                        String material = partes[1].split(" ")[1];
                        double valor = Double.parseDouble(partes[1].split(" ")[3]);
                        double pesada = Double.parseDouble(partes[1].split(" ")[5]);
                        Pesada pesadaObj = new Pesada(id, material, pesada, valor);
                        listaPesadas.add(pesadaObj);
                    }
                } catch (Exception e) {
                    continue;
                }
                continue;
            }
            String[] partes = pesadaString.split(", ");
            int id = Integer.parseInt(partes[0].split("=")[1]);
            String material = partes[1].split(" ")[1];
            double pesada = Double.parseDouble(partes[1].split(" ")[3]);
            double valor = Double.parseDouble(partes[1].split(" ")[5]);
            Pesada pesadaObj = new Pesada(id, material, pesada, valor);
            listaPesadas.add(pesadaObj);
        }

        try {
            pdf(true, value, totalx, listaPesadas);
        } catch (SQLException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ImprimirImpresoraTextoPlano("Cuenta " + value, totalx, listaPesadas);
        } catch (IOException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_BotonabonarCC2ActionPerformed

    private void tipo_CuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipo_CuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_CuentaActionPerformed

    private void tipo_CuentaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tipo_CuentaMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tipo_CuentaMouseClicked

    private void BotonabonarCC1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonabonarCC1ActionPerformed
        // TODO add your handling code here:
        ArrayList<Pesada> listaPesadas = new ArrayList<>();
        String cuenta = null;
        int selectedRow = TCuentaXC1.getSelectedRow(); // obtiene el número de fila seleccionado
        Integer value = Integer.parseInt(TCuentaXC1.getValueAt(selectedRow, 0).toString()); // obtiene el valor de la primera columna de la fila seleccionada
        Double totalx = Double.parseDouble(TCuentaXC1.getValueAt(selectedRow, 2).toString());
        cuenta = TCuentaXC1.getValueAt(selectedRow, 3).toString();
        String[] pesadas = cuenta.split("\n");
        for (String pesadaString : pesadas) {
            if (pesadaString.isEmpty() || pesadaString.startsWith("el valor total")) {
                continue; // Ignorar líneas vacías o la línea "el valor total"
            }
            String[] partes = pesadaString.split(", ");
            int id = Integer.parseInt(partes[0].split("=")[1]);
            String material = partes[1].split(" ")[1];
            double pesada = Double.parseDouble(partes[1].split(" ")[3]);
            double valor = Double.parseDouble(partes[1].split(" ")[5]);
            Pesada pesadaObj = new Pesada(id, material, pesada, valor);
            listaPesadas.add(pesadaObj);
        }

        try {
            pdf(true, value, totalx, listaPesadas);
        } catch (SQLException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(MetalesDeSantander.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {

            ImprimirImpresoraTextoPlano("Cuenta " + value, totalx, listaPesadas);
        } catch (IOException ex) {
            Logger.getLogger(Cuentas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_BotonabonarCC1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        Cuentas vv = new Cuentas(TipoDePersonaComboBox.getSelectedItem().toString());
        vv.setVisible(true);
    }//GEN-LAST:event_jButton8ActionPerformed

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
            if (total > 0) {
                String g = (String) modelo.getValueAt(i, 0);
                String cadena = "_id=-1,  Abono  " + valIN + " X 1 = " + total;
                Boolean j = nue.modificarCuentaValor(Integer.parseInt(g), total, cadena);
                if (j) {
                    JOptionPane.showConfirmDialog(null, "Se a actualizado");
                } else {
                    JOptionPane.showConfirmDialog(null, "no se a actualizado");
                }
                String[] cn = {"id", "fecha", "valor", "cuenta", "TipoCuenta"};
                TCuentaXC.setModel(new DefaultTableModel(cn, 0));
                String[] idString = ListaClientesCuentaCB.getSelectedItem().toString().split("-");
                CCuentas cunetaobjeto = new CCuentas();
                cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);
            }
            if (total == 0) {
                String g = (String) modelo.getValueAt(i, 0);
                nue.modificarCuentaEstado(Integer.parseInt(g));
                String[] cn = {"id", "fecha", "valor", "cuenta", "TipoCuenta"};
                TCuentaXC.setModel(new DefaultTableModel(cn, 0));
                String[] idString = ListaClientesCuentaCB.getSelectedItem().toString().split("-");
                CCuentas cunetaobjeto = new CCuentas();
                cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Nesesita seleccionar una cuenta");
        }
    }//GEN-LAST:event_BotonabonarCCActionPerformed

    private void CancelarCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarCuentaActionPerformed
        // TODO add your handling code here:
        try {
            DefaultTableModel modelo = (DefaultTableModel) TCuentaXC.getModel();
            int i = TCuentaXC.getSelectedRow();
            CCuentas nue = new CCuentas();
            Boolean j = nue.modificarCuentaEstado(Integer.parseInt((String) modelo.getValueAt(i, 0)));
            if (j) {
                JOptionPane.showConfirmDialog(null, "Se a actualizado");
                String[] cn = {"id", "fecha", "valor", "cuenta", "TipoCuenta"};
                TCuentaXC.setModel(new DefaultTableModel(cn, 0));
                String[] idString = ListaClientesCuentaCB.getSelectedItem().toString().split("-");
                CCuentas cunetaobjeto = new CCuentas();
                cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);
            } else {
                JOptionPane.showConfirmDialog(null, "no se a actualizado");
            }
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, "Nesesita seleccionar una cuenta");
        }
    }//GEN-LAST:event_CancelarCuentaActionPerformed

    private void TipoDePersonaComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TipoDePersonaComboBoxActionPerformed
        // TODO add your handling code here:
        optenerListaProbedor(selec());
        ListaClientesCuentaCB.removeAllItems();
        for (persona object : listaProbedor) {
            ListaClientesCuentaCB.addItem(object.getId() + "-" + object.getNombre());
        }
    }//GEN-LAST:event_TipoDePersonaComboBoxActionPerformed

    private void ListaClientesCuentaCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListaClientesCuentaCBActionPerformed
        try {
            String[] cn = {"id", "fecha", "valor", "cuenta", "TipoCuenta"};
            TCuentaXC.setModel(new DefaultTableModel(cn, 0));
            TCuentaXC1.setModel(new DefaultTableModel(cn, 0));
            CCuentas cunetaobjeto = new CCuentas();
            String[] idString = ListaClientesCuentaCB.getSelectedItem().toString().split("-");
            idselec = Integer.parseInt(idString[0]);
            listaTx = conp.listaPresios_idpersonas(idselec);
            Double Deuda = 0.0;
            switch (tipo_Cuenta.getSelectedItem().toString()) {
                case "Compra" -> {
                    cunetaobjeto.lista_de_Cuentas_x_persona(Integer.parseInt(idString[0]), TCuentaXC1, 1);
                    cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);
                }
                case "Venta" -> {
                    cunetaobjeto.lista_de_Cuentas_x_persona(Integer.parseInt(idString[0]), TCuentaXC1, 2);
                    cunetaobjeto.lista_de_Cuentas_x_cobrar(Integer.parseInt(idString[0]), TCuentaXC, 0);

                }
                default ->
                    throw new AssertionError();
            }
            Deuda = cunetaobjeto.SumCuentasxd(Integer.parseInt(idString[0]));
            DeudaLabel.setText(Deuda.toString());
            if (Deuda > 0) {
                DeudaLabel.setForeground(Color.GREEN);
            } else {
                DeudaLabel.setForeground(Color.RED);
            }

        } catch (Exception e) {
        }
    }//GEN-LAST:event_ListaClientesCuentaCBActionPerformed

    private void ListaClientesCuentaCBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ListaClientesCuentaCBMouseClicked

    }//GEN-LAST:event_ListaClientesCuentaCBMouseClicked

    public void pdf(Boolean es_impresion, Integer idfac, Double total, ArrayList<Pesada> pesadasx) throws SQLException, IOException, DocumentException {

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
        double importe = Double.parseDouble(total.toString());
        Double kilos = 4.0;

        facturaPdf.generarFacturaPdf(es_impresion, nombreArchivo, tipo_Cuenta.getSelectedItem().toString(), idfac, cliente, direccion, fecha, pesadasx, importe, kilos);
        pesadasx = null;
    }
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
    private javax.swing.JButton BAgregarClientes;
    private javax.swing.JButton BotonabonarCC;
    private javax.swing.JButton BotonabonarCC1;
    private javax.swing.JButton BotonabonarCC2;
    private com.toedter.calendar.JDateChooser CalendarioSuperior;
    private com.toedter.calendar.JDateChooser Calendarioinferior;
    private javax.swing.JButton CancelarCuenta;
    private javax.swing.JTextField Descripcion;
    private javax.swing.JLabel DeudaLabel;
    private javax.swing.JButton EditarCliente;
    private javax.swing.JButton EditarCliente2;
    private javax.swing.JButton EliminarTipoMovimiento;
    private javax.swing.JTextField JTValor;
    private javax.swing.JTextField JTpeso;
    private javax.swing.JComboBox<String> ListMaterialesInve;
    private javax.swing.JComboBox<String> ListaClientesCuentaCB;
    private javax.swing.JTable TClientes;
    private javax.swing.JTable TCuentaXC;
    private javax.swing.JTable TCuentaXC1;
    private javax.swing.JTable TInventario;
    private javax.swing.JTable TPrestamos;
    private javax.swing.JTable TablaCajaMenor;
    private javax.swing.JComboBox<String> Tipo;
    private javax.swing.JComboBox<String> TipoDePersonaComboBox;
    private javax.swing.JComboBox<String> TipoInforme;
    private javax.swing.JComboBox<String> TipoMovimiento;
    private javax.swing.JTextField Valor_dinero;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JComboBox<String> tipo_Cuenta;
    // End of variables declaration//GEN-END:variables
}
