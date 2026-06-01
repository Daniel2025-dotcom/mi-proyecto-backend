package com.example.demo.controller;
import com.example.demo.DTOs.response.CardProductResponseDTO;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/getProducts")
    @CrossOrigin(origins = "http://localhost:4200")
    public List<CardProductResponseDTO> getProducts() {
        return productService.getAllProducts();
    }
}
