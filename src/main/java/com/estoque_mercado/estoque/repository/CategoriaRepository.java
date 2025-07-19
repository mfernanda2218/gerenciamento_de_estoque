package com.estoque_mercado.estoque.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.estoque_mercado.estoque.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
}
