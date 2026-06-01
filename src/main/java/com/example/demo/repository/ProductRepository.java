package com.example.demo.repository;

import com.example.demo.DTOs.response.CardProductResponseDTO;
import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface  ProductRepository extends JpaRepository<Product, Long> {

}
