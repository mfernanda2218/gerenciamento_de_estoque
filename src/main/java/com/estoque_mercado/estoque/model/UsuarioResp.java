package com.estoque_mercado.estoque.model;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
public class UsuarioResp {
    @Getter
    @Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private Perfil perfil;

    //OneToMany - um usuario pode ter varias entradas e saidas de estoque
    @OneToMany(mappedBy = "usuarioResp", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntradaEstoque> entradasEstoque;

    @OneToMany(mappedBy = "usuarioResp") // precisa corresponder exatamente ao nome do atributo abaixo
    private List<SaidaEstoque> saidasEstoque;

    // Método para definir a senha já criptografando
    public void setSenha(String senhaPura) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        this.senha = encoder.encode(senhaPura);
    }

    // Método para verificar se a senha informada está correta
    public boolean verificarSenha(String senhaDigitada) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(senhaDigitada, this.senha);
    }

    public void setEmail(String emai) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
