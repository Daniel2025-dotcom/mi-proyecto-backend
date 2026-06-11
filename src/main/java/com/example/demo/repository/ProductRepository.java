package com.example.demo.repository;

import com.example.demo.DTOs.response.CardProductResponseDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface  ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategoryIn(List<Category> categories);
}
