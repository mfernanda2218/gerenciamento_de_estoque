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

import com.estoque_mercado.estoque.model.Produto;
import com.estoque_mercado.estoque.repository.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
@CrossOrigin(origins = "*")
public class ProdutoController {
    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public List<Produto> listar() {
        return produtoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoRepository.findById(id).orElseThrow();
    }

    @PostMapping
    public Produto criar(@RequestBody Produto produto) {
        return produtoRepository.save(produto);
    }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
        Produto produto = produtoRepository.findById(id).orElseThrow();
        produto.setNome(produtoAtualizado.getNome());
        produto.setDescricao(produtoAtualizado.getDescricao());
        produto.setPrecoCusto(produtoAtualizado.getPrecoCusto());
        produto.setPrecoVenda(produtoAtualizado.getPrecoVenda());
        produto.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
        produto.setDataValidade(produtoAtualizado.getDataValidade());
        produto.setFornecedor(produtoAtualizado.getFornecedor());
        produto.setCategoria(produtoAtualizado.getCategoria());
        return produtoRepository.save(produto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        produtoRepository.deleteById(id);
    }
}
