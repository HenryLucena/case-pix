package com.example.demo.model;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public enum TipoChave {
    CELULAR("celular"),
    EMAIL("email"),
    CPF("cpf"),
    CNPJ("cnpj"),
    ALEATORIO("aleatoio");

    private String descricao;

    TipoChave(String descricao){
        this.descricao = descricao;
    }

    public String getDescricao(){
        return descricao;
    }


}
