package com.api.emprestimo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CLIENTES")
public class Cliente {

    //Atributos
    @Id
    @NotNull
    @Column(unique = true)
    private Long cpf;
    @NotBlank(message = "Insira o nome.")
    private String nome;
    @NotNull
    @Column(unique = true)
    private Integer telefone;
    @Embedded
    private Endereco endereco;
//    @NotBlank
//    private String logradouro;
//    @NotBlank
//    private String complemento;
//    @NotNull(message = "Insira o CEP.")
//    private Integer cep;
    @NotNull(message = "Insira a renda mensal.")
    private BigDecimal renda;

    //O que exatamente isso faz?
    @OneToMany
    private List<Emprestimo> emprestimos = new ArrayList<>();

    //Construtor

    public Cliente(){

    }



    //Getters e Setters

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public Integer getTelefone() {
        return telefone;
    }

    public BigDecimal getRenda() {
        return renda;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public void setRenda(BigDecimal renda) {
        this.renda = renda;
    }

//toString

}
