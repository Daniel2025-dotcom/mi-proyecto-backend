package com.example.demo.service;

import com.example.demo.DTOs.request.CategoryRequestDTO;
import com.example.demo.DTOs.request.ProductRequestDTO;
import com.example.demo.DTOs.response.CategoryResponseDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;

import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addCategory(CategoryRequestDTO dto) {
        System.out.println("mensaje recibido");

        Category category = new Category();
        category.setTitle(dto.getCategoryName());

        if(dto.getParentId() != null) {

            Category parent = categoryRepository.findById(dto.getParentId()).orElseThrow(() -> new RuntimeException("Categoría padre no encontrada"));
            category.setParent(parent);
        }

        categoryRepository.save(category);
    }
    public List<CategoryResponseDTO> getCategoryTree() {

        List<Category> allCategories = categoryRepository.findAll();

        return allCategories.stream()
                .filter(cat -> cat.getParent() == null)
                .map(this::convertToDTO)
                .toList();
    }

    private CategoryResponseDTO convertToDTO(Category category) {
        CategoryResponseDTO dto = new CategoryResponseDTO(category.getId(), category.getTitle());

        if (category.getChildren() != null) {
            for (Category child : category.getChildren()) {
                dto.getHijos().add(convertToDTO(child));
            }
        }

        return dto;
    }
    public void saveProduct(ProductRequestDTO dto) {
        Product product = new Product();
        product.setName(dto.getName());
        product.setPrice(dto.getPrice());
        product.setDescription(dto.getDescription());
        product.setUrl(dto.getUrl());
        Category category = categoryRepository.findById(dto.getCategoryId())
                .orElseThrow(() -> new RuntimeException("La categoría no existe."));
        product.setCategory(category);
        productRepository.save(product);
    }
}