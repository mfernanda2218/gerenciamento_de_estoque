package com.estoque_mercado.estoque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoque_mercado.estoque.dto.ProdutoDTO;
import com.estoque_mercado.estoque.model.Produto;
import com.estoque_mercado.estoque.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/products")
@CrossOrigin
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<ProdutoDTO> getAll() {
        return produtoRepository.findAll()
                .stream()
                .map(ProdutoDTO::new)
                .toList();
    }

    @PostMapping
    public void create(@RequestBody ProdutoDTO dto) {
        Produto p = new Produto();
        p.setName(dto.getName());
        p.setQuantity(dto.getQuantity());
        p.setPrice(dto.getPrice());

        produtoRepository.save(p);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody ProdutoDTO dto) {
        Produto p = produtoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        p.setName(dto.getName());
        p.setQuantity(dto.getQuantity());
        p.setPrice(dto.getPrice());

        produtoRepository.save(p);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}
