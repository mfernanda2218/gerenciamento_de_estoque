package com.estoque_mercado.estoque.model;

public enum Perfil {
    ADMIN("Administrador"),
    OPERADOR("Operador de Estoque"),
    GERENTE("Gerente de Depósito");

    private final String descricao;

    Perfil(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
