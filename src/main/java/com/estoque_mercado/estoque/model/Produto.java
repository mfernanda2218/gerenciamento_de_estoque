package com.estoque_mercado.estoque.model;
import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Produto {
    @Getter
    @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private BigDecimal precoVenda;
    private Integer quantidadeEstoque;
    private LocalDate dataValidade;

    //Muitos produtos pertencem a uma categoria
    @ManyToOne
    @JoinColumn(name = "categoria_id") // nome da coluna no banco
    private Categoria categoria;

    //Muitos produtos pertencem a um fornecedor
    @ManyToOne
    @JoinColumn(name = "fornecedor_id") // chave estrangeira no banco
    private Fornecedor fornecedor;

    public Produto(Categoria categoria, LocalDate dataValidade, String descricao, Fornecedor fornecedor, Long id, String nome, BigDecimal precoVenda, Integer quantidadeEstoque) {
        this.categoria = categoria;
        this.dataValidade = dataValidade;
        this.descricao = descricao;
        this.fornecedor = fornecedor;
        this.id = id;
        this.nome = nome;
        this.precoVenda = precoVenda;
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public Object getPrecoCusto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPrecoCusto'");
    }

    public void setPrecoCusto(Object precoCusto) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPrecoCusto'");
    }

    public Object getUnidadeMedida() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUnidadeMedida'");
    }

    public void setUnidadeMedida(Object unidadeMedida) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setUnidadeMedida'");
    }
    
}
