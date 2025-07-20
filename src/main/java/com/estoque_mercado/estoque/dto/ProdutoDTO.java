package com.estoque_mercado.estoque.dto;

import com.estoque_mercado.estoque.model.Produto;

public class ProdutoDTO {

    public Long id;
    public String name;
    public Integer quantity;
    public Double price;
    public Long categoryId;
    public String categoryName;

    //Construtor vazio para Spring
    public ProdutoDTO() {}

    //Construtor de conversão a partir do modelo
    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.name = produto.getName();
        this.quantity = produto.getQuantity();
        this.price = produto.getPrice();

    }

    // ✅ Getters e Setters
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

    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }
    public void setPrice(Double price) {
        this.price = price;
    }

}
