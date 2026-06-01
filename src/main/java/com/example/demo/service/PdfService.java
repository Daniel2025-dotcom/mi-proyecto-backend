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

    public ByteArrayInputStream ObtenerPdf() {
        List<Product> products = productRepository.findAll();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter writer = new PdfWriter(out);
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf);

            for (Product p : products) {
                document.add(
                    new Paragraph(p.getName() != null ? p.getName() : "")
                );
                document.add(
                    new Paragraph(
                        p.getDescription() != null ? p.getDescription() : ""
                    )
                );
                document.add(
                    new Paragraph(
                        "$" + (p.getPrice() != null ? p.getPrice() : 0.0)
                    )
                );
            }
            document.close();
        } catch (Exception e) {
            throw new RuntimeException(
                "Error al generar el PDF del catálogo",
                e
            );
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}
