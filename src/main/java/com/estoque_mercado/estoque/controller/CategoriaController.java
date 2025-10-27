package com.estoque_mercado.estoque.controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.estoque_mercado.estoque.model.Categoria;
import com.estoque_mercado.estoque.service.CategoriaService;

@RestController
@RequestMapping("/api/categorias") // Define o caminho base para todos os endpoints desta classe
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService; // Injeta o serviço que contém a lógica de negócio

    // ENDPOINT PARA CRIAR UMA NOVA CATEGORIA (CREATE)
    @PostMapping
    public ResponseEntity<Categoria> criarCategoria(@RequestBody Categoria categoria) {
        Categoria novaCategoria = categoriaService.salvar(categoria);
        return new ResponseEntity<>(novaCategoria, HttpStatus.CREATED);
    }

    // ENDPOINT PARA BUSCAR TODAS AS CATEGORIAS (READ)
    @GetMapping
    public ResponseEntity<List<Categoria>> listarTodasCategorias() {
        List<Categoria> categorias = categoriaService.buscarTodas();
        return new ResponseEntity<>(categorias, HttpStatus.OK);
    }

    // ENDPOINT PARA BUSCAR UMA CATEGORIA POR ID (READ)
    @GetMapping("/{id}")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@PathVariable Long id) {
        Optional<Categoria> categoria = categoriaService.buscarPorId(id);
        // Se a categoria for encontrada, retorna 200 OK, senão, retorna 404 Not Found
        return categoria.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                        .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // ENDPOINT PARA ATUALIZAR UMA CATEGORIA (UPDATE)
    @PutMapping("/{id}")
    public ResponseEntity<Categoria> atualizarCategoria(@PathVariable Long id, @RequestBody Categoria categoriaDetalhes) {
        try {
            Categoria categoriaAtualizada = categoriaService.atualizar(id, categoriaDetalhes);
            return new ResponseEntity<>(categoriaAtualizada, HttpStatus.OK);
        } catch (RuntimeException e) {
            // Lança exceção se a categoria com o ID informado não existir
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // ENDPOINT PARA DELETAR UMA CATEGORIA (DELETE)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCategoria(@PathVariable Long id) {
        try {
            categoriaService.deletar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // 204 No Content indica sucesso sem corpo de resposta
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
