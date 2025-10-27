package com.estoque_mercado.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque_mercado.estoque.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    List<Produto> findByCategoriaNome(String nomeCategoria);
    List<Produto> findByQuantidadeEstoqueLessThan(Integer quantidade);
}
