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

import com.estoque_mercado.estoque.model.SaidaEstoque;
import com.estoque_mercado.estoque.repository.SaidaEstoqueRepository;

@RestController
@RequestMapping("/api/saidas")
@CrossOrigin(origins = "*")
public class SaidaEstoqueController {
    @Autowired
    private SaidaEstoqueRepository saidaEstoqueRepository;

    @GetMapping
    public List<SaidaEstoque> listar() {
        return saidaEstoqueRepository.findAll();
    }

    @GetMapping("/{id}")
    public SaidaEstoque buscarPorId(@PathVariable Long id) {
        return saidaEstoqueRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public SaidaEstoque criar(@RequestBody SaidaEstoque saida) {
        return saidaEstoqueRepository.save(saida);
    }

    @PutMapping("/{id}")
    public SaidaEstoque atualizar(@PathVariable Long id, @RequestBody SaidaEstoque saidaAtualizada) {
        SaidaEstoque saida = saidaEstoqueRepository.findById(id).orElseThrow();
        saida.setQuantidade(saidaAtualizada.getQuantidade());
        saida.setMotivo(saidaAtualizada.getMotivo());
        saida.setProduto(saidaAtualizada.getProduto());
        saida.setDataSaida(saidaAtualizada.getDataSaida());
        return saidaEstoqueRepository.save(saida);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        saidaEstoqueRepository.deleteById(id);
    }
}
