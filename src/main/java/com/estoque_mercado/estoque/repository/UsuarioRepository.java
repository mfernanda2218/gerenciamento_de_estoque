package com.estoque_mercado.estoque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estoque_mercado.estoque.model.UsuarioResp;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioResp, Long> {
    Optional<UsuarioResp> findByEmail(String email);
}
