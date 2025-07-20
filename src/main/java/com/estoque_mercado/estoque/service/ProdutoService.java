package com.estoque_mercado.estoque.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.estoque_mercado.estoque.dto.ProdutoDTO;
import com.estoque_mercado.estoque.model.Produto;
import com.estoque_mercado.estoque.repository.ProdutoRepository;

@Service
public class ProdutoService {
    
    @Autowired
    private ProdutoRepository repository;

    public List<ProdutoDTO> getAllProdutos() {
        return repository.findAll().stream().map(this::toDTO).collect(Collectors.toList());
    }

    public ProdutoDTO createProduto(ProdutoDTO dto) {
        Produto produto = fromDTO(dto);
        return toDTO(repository.save(produto));
    }

    public ProdutoDTO updateProduto(Long id, ProdutoDTO dto) {
        Produto produto = repository.findById(id).orElseThrow();
        produto.setName(dto.name);
        produto.setQuantity(dto.quantity);
        produto.setPrice(dto.price);

        return toDTO(repository.save(produto));
    }

    public void deleteProduto(Long id) {
        repository.deleteById(id);
    }

    private ProdutoDTO toDTO(Produto p) {
        ProdutoDTO dto = new ProdutoDTO(p);
        dto.id = p.getId();
        dto.name = p.getName();
        dto.quantity = p.getQuantity();
        dto.price = p.getPrice();
        return dto;
    }

    private Produto fromDTO(ProdutoDTO dto) {
        Produto p = new Produto();
        p.setName(dto.name);
        p.setQuantity(dto.quantity);
        p.setPrice(dto.price);
        return p;
    }
}

