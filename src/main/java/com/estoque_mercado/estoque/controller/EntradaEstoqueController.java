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

import com.estoque_mercado.estoque.model.EntradaEstoque;
import com.estoque_mercado.estoque.repository.EntradaEstoqueRepository;

@RestController
@RequestMapping("/api/entrada-estoque")
@CrossOrigin(origins = "*") //habilita o CORS para todas as origens (evita conflito de portas entre back e front)
public class EntradaEstoqueController {
    @Autowired
    private EntradaEstoqueRepository entradaEstoqueRepository;

    @GetMapping
    public List<EntradaEstoque> listar() {
        return entradaEstoqueRepository.findAll();
    }

    @GetMapping("/{id}")
    public EntradaEstoque buscarPorId(@PathVariable Long id) {
        return entradaEstoqueRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public EntradaEstoque criar(@RequestBody EntradaEstoque entrada) {
        return entradaEstoqueRepository.save(entrada);
    }

    @PutMapping("/{id}")
    public EntradaEstoque atualizar(@PathVariable Long id, @RequestBody EntradaEstoque entradaAtualizada) {
        EntradaEstoque entrada = entradaEstoqueRepository.findById(id).orElseThrow();
        entrada.setQuantidade(entradaAtualizada.getQuantidade());
        entrada.setProduto(entradaAtualizada.getProduto());
        entrada.setFornecedor(entradaAtualizada.getFornecedor());
        entrada.setDataEntrada(entradaAtualizada.getDataEntrada());
        return entradaEstoqueRepository.save(entrada);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        entradaEstoqueRepository.deleteById(id);
    }
}
