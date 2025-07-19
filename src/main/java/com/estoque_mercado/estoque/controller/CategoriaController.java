package com.estoque_mercado.estoque.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoque_mercado.estoque.dto.CategoriaDTO;
import com.estoque_mercado.estoque.model.Categoria;
import com.estoque_mercado.estoque.repository.CategoriaRepository;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @GetMapping
    public List<CategoriaDTO> getAll() {
        return categoriaRepository.findAll()
                .stream()
                .map(c -> {
                    CategoriaDTO dto = new CategoriaDTO();
                    dto.id = c.getId();
                    dto.name = c.getName();
                    return dto;
                })
                .toList();
    }

    @PostMapping
    public CategoriaDTO create(@RequestBody CategoriaDTO dto) {
        Categoria c = new Categoria();
        c.setName(dto.name);
        Categoria saved = categoriaRepository.save(c);
        dto.id = saved.getId();
        return dto;
    }
}
