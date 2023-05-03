package com.teste.demo.entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {
    private static final long serialVersionUID = 1L;

    // ANOTATION ID PARA DIZERMOS QUAL ATRIBUTO É O ID DA NOSSA CLASSE
    // GENERATED VALUE QUANDO ESTÁ COM GENERATIONTYPE IDENTITY SIGNIFICA QUE O BANCO SERÁ RESPONSÁVEL POR GERAR ID'S.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String imgUrl;

    // ANOTATION @ManyToMany SIGNIFICA DE MUITOS PARA MUITOS, OU SEJA UM PRODUTO PODE TER MUITAS CATEGORIAS E UMA CATEGORIA PODE ESTAR RELACIONADA...
    // ...A MUITOS PRODUTOS, NESSE CASO PASSAMOS A ANOTATION @JoinTable DIZENDO QUE QUEREMOS CRIAR UMA TABELA COM NOME tb_product_category, UMA DAS COLUNAS...
    // ...AMARRAÇÃO SERÁ product_id E O INVERSE SIGNIFICA A OUTRA CLASSE QUE SERÁ AMARRADA A ESSA, POR ESTARMOS NA CLASSE DE PRODUTO, COLOCAMOS O INVERSO A DE CATEGORIA...
    // ...MAS PODERIA SER AO CONTRÁRIO.
    @ManyToMany
    @JoinTable(name = "tb_product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    public Product(){
    }

    public Product(Long id, String name, String description, Double price, String imgUrl) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return id.equals(product.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
