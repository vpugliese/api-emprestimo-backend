package com.api.emprestimo.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;

@Entity
@Embeddable
public class Endereco {
    private String logradouro;
    private String complemento;
    private long CEP;

    //Construtor

    public Endereco(String logradouro, String complemento, long CEP) {
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.CEP = CEP;
    }

    //Getters e Setters

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setCEP(long CEP) {
        this.CEP = CEP;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public long getCEP() {
        return CEP;
    }
}
