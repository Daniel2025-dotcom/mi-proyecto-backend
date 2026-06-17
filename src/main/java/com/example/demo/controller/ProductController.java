package com.example.demo.controller;
import com.example.demo.DTOs.request.ProductByCategoryRequestDTO;
import com.example.demo.DTOs.request.ProductByIdDTO;
import com.example.demo.DTOs.request.ProductRequestDTO;
import com.example.demo.DTOs.response.CardProductResponseDTO;
import com.example.demo.DTOs.response.ProductResponseDTO;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<CardProductResponseDTO> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/getProductsByCategory")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<CardProductResponseDTO> getProductsByCategory(@RequestBody ProductByCategoryRequestDTO request) {return productService.getAllProductsByCategoryAndChildren(request.getId());
    }
    @PostMapping("/getProductById")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public ProductResponseDTO getProductById(@RequestBody ProductByIdDTO id) {
        return productService.getProductById(id);
    }

    @PostMapping("/modifyProduct")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> modifyProduct(@RequestBody ProductRequestDTO dto,Long id) {
        productService.modifyProduct(dto,id);
        return ResponseEntity.ok("Modificado con exito");
    }
}