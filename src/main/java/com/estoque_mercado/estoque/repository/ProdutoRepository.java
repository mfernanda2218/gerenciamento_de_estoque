package com.estoque_mercado.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoque_mercado.estoque.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    // Additional query methods can be defined here if needed

}
