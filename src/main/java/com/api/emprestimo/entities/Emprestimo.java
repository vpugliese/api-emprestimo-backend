package com.api.emprestimo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "EMPRÉSTIMOS")
public class Emprestimo {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "CPFCliente")
    private Cliente cpf;
    @NotNull
    private BigDecimal ValorInicial;
    @NotNull
    private BigDecimal ValorFinal;
    private LocalDate DataInicial;
    private LocalDate DataFinal;

    //Construtores

    public Emprestimo() {
    }

    public Emprestimo(Long id, BigDecimal valorInicial, BigDecimal valorFinal, LocalDate dataInicial, LocalDate dataFinal) {
        this.id = id;
        ValorInicial = valorInicial;
        ValorFinal = valorFinal;
        DataInicial = dataInicial;
        DataFinal = dataFinal;
    }

//Getters e Setters

    public Long getId() {
        return id;
    }

    public Cliente getCpf() {
        return cpf;
    }

    public BigDecimal getValorInicial() {
        return ValorInicial;
    }

    public BigDecimal getValorFinal() {
        return ValorFinal;
    }

    public LocalDate getDataInicial() {
        return DataInicial;
    }

    public LocalDate getDataFinal() {
        return DataFinal;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCpf(Cliente cpf) {
        this.cpf = cpf;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        ValorInicial = valorInicial;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        ValorFinal = valorFinal;
    }

    public void setDataInicial(LocalDate dataInicial) {
        DataInicial = dataInicial;
    }

    public void setDataFinal(LocalDate dataFinal) {
        DataFinal = dataFinal;
    }
}
