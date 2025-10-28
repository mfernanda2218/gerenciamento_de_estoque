package com.estoque_mercado.estoque.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Data
public class Fornecedor {
    @Getter
    @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String cnpj;
    private String telefone;
    private String endereco;

    //Um fornecedor pode ter vários produtos
    @OneToMany(mappedBy = "fornecedor")
    private List<Produto> produtos;

    public Fornecedor(Long id, String nome, String contato, String endereco, String telefone, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public Object getEmail() {
        throw new UnsupportedOperationException("Unimplemented method 'getEmail'");
    }

    public void setEmail(Object email) {
        throw new UnsupportedOperationException("Unimplemented method 'setEmail'");
    }
}
