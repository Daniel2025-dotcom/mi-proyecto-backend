package com.example.demo.DTOs.response;

public class ProductResponseDTO {
    private String name;
    private String description;
    private double price;
    private String url;
    private Long categoryId;
    private String path;
    public ProductResponseDTO(String name, String description, double price, String url, Long categoryId,String path) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.url = url;
        this.categoryId = categoryId;
        this.path = path;
    }

    public  String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;

    }
    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public Long getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
    public String getPath() {
        return path;
    }
    public void setPath(String path) {
        this.path = path;
    }
}
