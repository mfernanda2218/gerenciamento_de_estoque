package com.estoque_mercado.estoque.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class EntradaEstoque {
    @Getter
    @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime dataEntrada;
    private Integer quantidade;
    private BigDecimal valorUnitario;
    private Produto produto;
    private Fornecedor fornecedor;

    @ManyToOne //varias entradas pertencem a um mesmo usuario
    @JoinColumn(name = "usuario_id") //define a chave estrangeira na tabela EntradaEstoque
    private UsuarioResp usuarioResp;

    public EntradaEstoque(LocalDateTime dataEntrada, Fornecedor fornecedor, Long id, Produto produto, Integer quantidade, UsuarioResp usuarioResp, BigDecimal valorUnitario) {
        this.dataEntrada = dataEntrada;
        this.fornecedor = fornecedor;
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuarioResp = usuarioResp;
        this.valorUnitario = valorUnitario;
    }

}
