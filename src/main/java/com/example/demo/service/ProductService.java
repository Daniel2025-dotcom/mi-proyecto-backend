package com.example.demo.service;

import com.example.demo.DTOs.response.CardProductResponseDTO;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<CardProductResponseDTO> getAllProducts(){
        List <Product> listItems = productRepository.findAll();
        List <CardProductResponseDTO> listItemsDTO =  listItems.stream().map(p->new CardProductResponseDTO(p.getId(),p.getUrl(),p.getName(),p.getPrice())).toList();
        return listItemsDTO;

    }
}
