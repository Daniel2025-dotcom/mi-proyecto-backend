package com.example.demo.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
public class Category {
    //@Column(name = "category_title") ,solo alli se necesita especificar el collumn , cuando cambiomos el nombre .@Transient ignora el atributo

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "parent_id")  //“esta relación usa esta foreign key”.
    private Category parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)   //NO crea otra columna.
    private List<Category> children = new ArrayList<>();

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();


    public Category() {
    }

    public Category(String title) {
        this.title = title;
    }

    public void addChild(Category child) {
        child.setParent(this);
        children.add(child);
    }

    public void setParent(Category parent) {
        this.parent = parent;
    }

    public Category getParent() {
            return parent;
    }
    public List<Category> getChildren() {
        return children;
    }

    public Long getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }

    public void setChildren(List<Category> children) {}

    public void setTitle(String title) {
        this.title = title;
    }
}