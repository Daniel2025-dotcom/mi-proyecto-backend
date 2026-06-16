package com.example.demo.controller;

import com.example.demo.DTOs.request.CategoryRequestDTO;
import com.example.demo.DTOs.request.ProductByCategoryRequestDTO;
import com.example.demo.DTOs.request.ProductByIdDTO;
import com.example.demo.DTOs.request.ProductRequestDTO;
import com.example.demo.DTOs.response.CategoryResponseDTO;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.AdminService;
import com.example.demo.service.PdfService;
import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Map;

import com.example.demo.service.ProductService;
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
    @Autowired
    private ProductService productService;

    @PostMapping("/category")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public void addCategory(@RequestBody CategoryRequestDTO dto) {
        adminService.addCategory(dto);
    }

    @GetMapping("/categories")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<CategoryResponseDTO> getCategories() {
        return adminService.getCategoryTree();
    }

    @PostMapping("/loadProduct")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<Map<String, String>> loadProduct(@RequestBody ProductRequestDTO dto) {
        adminService.saveProduct(dto);
        return ResponseEntity.ok(
                Map.of("message", "Producto guardado con éxito")
        );
    }

    @GetMapping("/catalog/pdf")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ResponseEntity<InputStreamResource> getCatalogPdf() {
        ByteArrayInputStream bis = pdfService.obtenerPdf();
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

    @PostMapping("/deleteProduct")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<String> deleteProduct(@RequestBody ProductByIdDTO dto) {
        if (productService.deleteProduct(dto)) {
            return ResponseEntity.ok("Producto eliminado con exito");
        } else {
            return ResponseEntity.badRequest().build();
        }

    }
}
