package com.example.demo.controller;
import com.example.demo.DTOs.request.ProductByCategoryRequestDTO;
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
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<CardProductResponseDTO> getProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/getProductsByCategory")
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    public List<CardProductResponseDTO> getProductsByCategory(@RequestBody ProductByCategoryRequestDTO request) {return productService.getAllProductsByCategoryAndChildren(request.getId());
    }
}
