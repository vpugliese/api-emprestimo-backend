package com.api.emprestimo.model;

import jakarta.persistence.*;

@Entity
@Embeddable
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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


    public void setId(Long id) {
        this.id = id;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public void setCEP(long CEP) {
        this.CEP = CEP;
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

    public long getCEP() {
        return CEP;
    }
}
