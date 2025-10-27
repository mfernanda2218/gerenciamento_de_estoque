package com.estoque_mercado.estoque.service;

import com.estoque_mercado.estoque.model.Produto;
import com.estoque_mercado.estoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.util.List;

@Service
public class RelatorioService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public void exportarEstoqueCSV(PrintWriter writer) {
        List<Produto> produtos = produtoRepository.findAll();

        writer.println("ID;Nome;Quantidade;Preço Venda;Categoria");

        for (Produto produto : produtos) {
            writer.printf("%d;%s;%d;%.2f;%s%n",
                    produto.getId(),
                    produto.getNome(),
                    produto.getQuantidadeEstoque(),
                    produto.getPrecoVenda(),
                    produto.getCategoria() != null ? produto.getCategoria().getNome() : "Sem categoria");
        }
    }
}
