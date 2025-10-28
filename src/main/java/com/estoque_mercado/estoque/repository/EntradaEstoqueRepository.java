package com.estoque_mercado.estoque.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque_mercado.estoque.model.EntradaEstoque;
import com.estoque_mercado.estoque.model.Fornecedor;

@Repository
public interface EntradaEstoqueRepository extends JpaRepository<EntradaEstoque, Long> {
    List <EntradaEstoque> findByFornecedor(Fornecedor fornecedor);
}
