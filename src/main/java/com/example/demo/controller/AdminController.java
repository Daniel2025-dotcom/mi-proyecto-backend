package com.example.demo.controller;

import com.example.demo.DTOs.request.CategoryRequestDTO;
import com.example.demo.DTOs.request.ProductRequestDTO;
import com.example.demo.DTOs.response.CategoryResponseDTO;
import com.example.demo.service.AdminService;
import com.example.demo.service.PdfService;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//en el sofware a nivel profesion desde la parte del servidor tendrian que haber muchas mas validaciones

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/category")
    @CrossOrigin(origins = "http://localhost:4200")
    public void addCategory(@RequestBody CategoryRequestDTO dto) {
        adminService.addCategory(dto);
    }

    @GetMapping("/categories")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<CategoryResponseDTO> getCategories() {
        return adminService.getCategoryTree();
    }

    @PostMapping("/loadProduct")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<Map<String, String>> loadProduct(@RequestBody ProductRequestDTO dto) {
        adminService.saveProduct(dto);
        return ResponseEntity.ok(
                Map.of("message", "Producto guardado con éxito")
        );
    }

    @GetMapping("/catalog/pdf")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<InputStreamResource> getCatalogPdf() {
        ByteArrayInputStream bis = pdfService.ObtenerPdf();
        HttpHeaders headers = new HttpHeaders();
        headers.add(
            "Content-Disposition",
            "attachment; filename=catalogo_productos.pdf"
        );
        return ResponseEntity.ok()
            .headers(headers)
            .contentType(MediaType.APPLICATION_PDF)
            .body(new InputStreamResource(bis));
    }
}
