package com.estoque_mercado.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque_mercado.estoque.model.EntradaEstoque;

@Repository
public interface EntradaEstoqueRepository extends JpaRepository<EntradaEstoque, Long> {

}
