package com.example.demo.service;

import com.example.demo.DTOs.request.ProductByIdDTO;
import com.example.demo.DTOs.response.CardProductResponseDTO;
import com.example.demo.model.Category;
import com.example.demo.model.Product;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public List<CardProductResponseDTO> getAllProducts(){
        List <Product> listItems = productRepository.findAll();
        List <CardProductResponseDTO> listItemsDTO =  listItems.stream().map(p->new CardProductResponseDTO(p.getId(),p.getUrl(),p.getName(),p.getPrice(),pathCategory(p.getCategory()))).toList();
        return listItemsDTO;

    }
    public List<CardProductResponseDTO> getAllProductsByCategoryAndChildren(Long categoryId) {
        Category rootCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new RuntimeException("Categoría no encontrada con el ID: " + categoryId));
        List<Category> allCategories = new ArrayList<>();
        findAllChildrenRecursive(rootCategory, allCategories);
        List<Product> listItems = productRepository.findByCategoryIn(allCategories);
        return listItems.stream().map(p -> new CardProductResponseDTO(p.getId(), p.getUrl(), p.getName(), p.getPrice(),pathCategory(p.getCategory()))).toList();
    }


    private void findAllChildrenRecursive(Category currentCategory, List<Category> accumulator) {
        accumulator.add(currentCategory);
        for (Category child : currentCategory.getChildren()) {
            findAllChildrenRecursive(child, accumulator);
        }
    }
    private String pathCategory(Category category) {
        String path = "";
        while (category.getParent() != null) {
            path = path + category.getTitle() + "/" ;
            category = category.getParent();
        }
        return path  + category.getTitle();
    }

    public Boolean deleteProduct(ProductByIdDTO dto) {
        Product product = productRepository.findById(dto.getId()).orElseThrow(() -> new RuntimeException("El id no existe."));
            productRepository.delete(product);
            return true;
    }
}
