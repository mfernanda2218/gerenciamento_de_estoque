package com.estoque_mercado.estoque.model;

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
public class SaidaEstoque {
    @Getter
    @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private LocalDateTime dataSaida;
    private Integer quantidade;
    private String motivo; //venda, avaria, doação, etc
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private UsuarioResp usuarioResp;

    public SaidaEstoque(LocalDateTime dataSaida, Long id, String motivo, Produto produto, Integer quantidade, UsuarioResp usuarioResp) {
        this.dataSaida = dataSaida;
        this.id = id;
        this.motivo = motivo;
        this.produto = produto;
        this.quantidade = quantidade;
        this.usuarioResp = usuarioResp;
    }


}
