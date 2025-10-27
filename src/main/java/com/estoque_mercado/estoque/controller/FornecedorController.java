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

import com.estoque_mercado.estoque.model.Fornecedor;
import com.estoque_mercado.estoque.repository.FornecedorRepository;

@RestController
@RequestMapping("/api/fornecedores")
@CrossOrigin(origins = "*")
public class FornecedorController {
    @Autowired
    private FornecedorRepository fornecedorRepository;

    @GetMapping
    public List<Fornecedor> listar() {
        return fornecedorRepository.findAll();
    }

    @GetMapping("/{id}")
    public Fornecedor buscarPorId(@PathVariable Long id) {
        return fornecedorRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Fornecedor criar(@RequestBody Fornecedor fornecedor) {
        return fornecedorRepository.save(fornecedor);
    }

    @PutMapping("/{id}")
    public Fornecedor atualizar(@PathVariable Long id, @RequestBody Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedor = fornecedorRepository.findById(id).orElseThrow();
        fornecedor.setNome(fornecedorAtualizado.getNome());
        fornecedor.setCnpj(fornecedorAtualizado.getCnpj());
        fornecedor.setEmail(fornecedorAtualizado.getEmail());
        fornecedor.setTelefone(fornecedorAtualizado.getTelefone());
        fornecedor.setEndereco(fornecedorAtualizado.getEndereco());
        return fornecedorRepository.save(fornecedor);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        fornecedorRepository.deleteById(id);
    }

}
