package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PdfService {

    @Autowired
    private ProductRepository productRepository;

    public ByteArrayInputStream obtenerPdf() {
        List<Product> products = productRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);
            document.add(new Paragraph("Catálogo de Productos")
                    .setBold()
                    .setFontSize(18));
            document.add(new Paragraph("\n"));
            Table table = new Table(UnitValue.createPercentArray(new float[]{1, 3, 5, 2}));
            table.setWidth(UnitValue.createPercentValue(100));
            table.addHeaderCell(new Cell().add(new Paragraph("ID").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Nombre").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Descripción").setBold()));
            table.addHeaderCell(new Cell().add(new Paragraph("Precio").setBold()));
            for (Product p : products) {
                table.addCell(String.valueOf(p.getId()));
                table.addCell(
                        p.getName() != null
                                ? p.getName()
                                : "-"
                );
                table.addCell(
                        p.getDescription() != null
                                ? p.getDescription()
                                : "-"
                );
                table.addCell(
                        "$" + (p.getPrice() != null
                                ? p.getPrice()
                                : 0.0)
                );
            }
            document.add(table);
            document.close();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Error al generar el PDF del catálogo", e
            );
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}