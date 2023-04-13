package com.api.emprestimo.entities;

import jakarta.persistence.*;


@Entity
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String rua;
    private String complemento;
    private String cep;

    //Construtor
    public Endereco() {
    }

    public Endereco(Long id, String rua, String complemento, String cep) {
        this.id = id;
        this.rua = rua;
        this.complemento = complemento;

        this.cep = cep;
    }

    //Getters e Setters


    public void setId(Long id) {
        this.id = id;
    }

    public void setRua(String rua) {
        this.rua = rua;
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

    public String getRua() {
        return rua;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getCep() {
        return cep;
    }
}
