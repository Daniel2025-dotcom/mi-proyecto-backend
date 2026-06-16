package com.example.demo.DTOs.response;

import org.hibernate.query.sql.internal.ParameterRecognizerImpl;

public class CardProductResponseDTO {
    private Long id;
    private String url;
    private String name;
    private Double price;
    private String pathCategory;

    public CardProductResponseDTO() {

    }
    public CardProductResponseDTO(Long id,String url, String name, Double price, String pathCategory) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.price = price;
        this.pathCategory = pathCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPathCategory() {
        return pathCategory;
    }
    public void setPathCategory(String pathCategory) {
        this.pathCategory = pathCategory;
    }
}
