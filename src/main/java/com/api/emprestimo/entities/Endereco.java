package com.api.emprestimo.entities;

import jakarta.persistence.*;


@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String logradouro;
    private String complemento;
    private String cep;

    //Construtor
    public Endereco() {
    }

    public Endereco(Long id, String logradouro, String complemento, String cep) {
        this.id = id;
        this.logradouro = logradouro;
        this.complemento = complemento;
        this.cep = cep;
    }

    //Getters e Setters


    public void setId(Long id) {
        this.id = id;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Long getId() {
        return id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }
}
