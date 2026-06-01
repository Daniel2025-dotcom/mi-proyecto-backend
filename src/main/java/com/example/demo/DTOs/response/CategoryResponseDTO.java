package com.example.demo.DTOs.response;

import java.util.ArrayList;
import java.util.List;

public class CategoryResponseDTO {
    private Long id;
    private String nombre;
    private List<CategoryResponseDTO> hijos = new ArrayList<>();

    public CategoryResponseDTO() {}

    public CategoryResponseDTO(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public List<CategoryResponseDTO> getHijos() { return hijos; }
    public void setHijos(List<CategoryResponseDTO> hijos) { this.hijos = hijos; }
}