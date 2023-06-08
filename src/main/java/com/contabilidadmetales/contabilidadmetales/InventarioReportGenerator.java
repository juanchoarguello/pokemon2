/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.contabilidadmetales.contabilidadmetales;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.time.LocalDate;
import java.util.List;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.contabilidadmetales.contabilidadmetales.modelo.Inventario;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

import java.awt.Color;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.jfree.chart.ChartUtils;


public class InventarioReportGenerator {

    private static final Font HEADER_FONT = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
    private static final Font DATA_FONT = new Font(Font.FontFamily.HELVETICA, 10);

    public static void generateReport(Double peso,Double valor,List<Inventario> inventarioList, LocalDate fechaAnterior, LocalDate fechaActual, String materialFiltro, String outputPath) {
        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            addTitle(document);
            addFilterInfo(document, fechaAnterior, fechaActual, materialFiltro);
            addTable(document, inventarioList);
            /*------------------------------------------*/
            // Crear las celdas para la fila adicional
            PdfPCell pesoCell = new PdfPCell(new Phrase("Peso (Kg) =  "+peso));
            PdfPCell valorCell = new PdfPCell(new Phrase("Valor (COP $) =  "+valor));

            PdfPCell pesoValorCell = new PdfPCell();
            PdfPCell valorValorCell = new PdfPCell();
            // Establecer los valores específicos en las celdas
            
            // Crear una tabla para la fila adicional
            PdfPTable additionalRowTable = new PdfPTable(2);
            additionalRowTable.setWidthPercentage(100);

            // Añadir las celdas a la tabla
            additionalRowTable.addCell(pesoCell);
            additionalRowTable.addCell(valorCell);

            // Añadir la tabla adicional al documento
            document.add(additionalRowTable);

            /*------------------------------------------*/
            addChart(document, inventarioList, fechaAnterior, fechaActual);
            document.close();
            // Reemplaza con la ruta real de tu archivo PDF

            // Verificar si Desktop está soportado en el sistema
            if (Desktop.isDesktopSupported()) {
                Desktop desktop = Desktop.getDesktop();
                File file = new File(outputPath);

                try {
                    // Abrir el archivo PDF con el programa predeterminado
                    desktop.open(file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Desktop no está soportado en este sistema.");
            }
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }

    private static void addChart(Document document, List<Inventario> inventarioList, LocalDate fechaAnterior, LocalDate fechaActual) throws IOException, DocumentException {
        // Crear el dataset con los datos de la gráfica
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        for (Inventario inventario : inventarioList) {
            // Aquí debes ajustar las llamadas a los métodos de tu clase Inventario según la estructura de tu objeto
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Timestamp fecha =inventario.getFecha();
            String fechaFormateada = dateFormat.format(fecha);
            dataset.addValue(Double.valueOf(inventario.getPeso()), "material: " + inventario.getIdMaterial(), fechaFormateada);

        }

        // Crear la gráfica de barras
        JFreeChart chart = ChartFactory.createBarChart("Inventario por Mes", "Mes", "Cantidad", dataset);
        chart.setBackgroundPaint(Color.WHITE);

        // Renderizar la gráfica en un archivo de imagen 
        ChartUtils.saveChartAsPNG(new File("imagen.png"), chart, 500, 300);

        // Insertar la imagen de la gráfica en el documento PDF
        Image chartImage = Image.getInstance("imagen.png");

        document.add(chartImage);
    }

    private static void addTitle(Document document) throws DocumentException {
        Paragraph title = new Paragraph("Reporte de Inventario", new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD));
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);
    }

    private static void addFilterInfo(Document document, LocalDate fechaAnterior, LocalDate fechaActual, String materialFiltro) throws DocumentException {
        Paragraph filterInfo = new Paragraph("Filtros aplicados:", HEADER_FONT);
        filterInfo.setSpacingAfter(10f);
        document.add(filterInfo);

        // Fecha Anterior
        Paragraph fechaAnteriorInfo = new Paragraph("Fecha Anterior: " + fechaAnterior.toString(), DATA_FONT);
        document.add(fechaAnteriorInfo);

        // Fecha Actual
        Paragraph fechaActualInfo = new Paragraph("Fecha Actual: " + fechaActual.toString(), DATA_FONT);
        document.add(fechaActualInfo);

        // Material Filtro
        Paragraph materialFiltroInfo = new Paragraph("Material Filtro: " + materialFiltro, DATA_FONT);
        document.add(materialFiltroInfo);

        document.add(new Paragraph("\n"));
    }

    private static void addTable(Document document, List<Inventario> inventarioList) throws DocumentException {
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10f);
        table.setSpacingAfter(10f);
        addTableHeader(table);
        addTableData(table, inventarioList);

        document.add(table);
    }

    private static void addTableHeader(PdfPTable table) {
        table.addCell(new PdfPCell(new Phrase("ID", HEADER_FONT)));
        table.addCell(new PdfPCell(new Phrase("Peso", HEADER_FONT)));
        table.addCell(new PdfPCell(new Phrase("Número de Cuenta", HEADER_FONT)));
        table.addCell(new PdfPCell(new Phrase("Material", HEADER_FONT)));
        table.addCell(new PdfPCell(new Phrase("Valor", HEADER_FONT)));
        table.addCell(new PdfPCell(new Phrase("Fecha", HEADER_FONT)));
    }
//idInventario, peso, Descripcion, idMaterial, Valor, fecha
    // ...

    private static void addTableData(PdfPTable table, List<Inventario> inventarioList) {
        for (Inventario inventario : inventarioList) {
            table.addCell(new PdfPCell(new Phrase(String.valueOf(inventario.getId()), DATA_FONT)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(inventario.getPeso()), DATA_FONT)));
            table.addCell(new PdfPCell(new Phrase(inventario.getDescripcion(), DATA_FONT)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(inventario.getIdMaterial()), DATA_FONT)));
            table.addCell(new PdfPCell(new Phrase(String.valueOf(inventario.getValor()), DATA_FONT)));
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Timestamp fecha =inventario.getFecha();
            String fechaFormateada = dateFormat.format(fecha);
            table.addCell(new PdfPCell(new Phrase(fechaFormateada, DATA_FONT)));
        }

    }

// ...

    public void generateReport(Double peso, Double valorTotal, ArrayList<Inventario> inventarioList, Date fechaAnterior, Date fechaActual, String material, String string) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
