package com.api.emprestimo.entities;

import com.api.emprestimo.enums.Relacionamento;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "Empréstimos")
public class Emprestimo {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String cpfCliente;
    @NotNull
    private BigDecimal valorInicial;
    private BigDecimal valorFinal;
    @NotNull
    private LocalDate dataInicial;
    @NotNull
    private LocalDate dataFinal;
    @NotNull
    private Relacionamento relacionamento;
    @JsonIgnore
    @ManyToOne
    private Cliente cliente;

    //Construtores
    public Emprestimo() {
    }

    //Métodos
    public Integer numDeEmprestimos() {
        return cliente.getEmprestimos().size();
    }

    public void setValorFinal() {
        this.valorFinal = this.relacionamento.valorMultiplicado(valorInicial, numDeEmprestimos());
    }

    //Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return cpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        this.cpfCliente = cpfCliente;
    }

    public BigDecimal getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        this.valorInicial = valorInicial;
    }

    public BigDecimal getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        this.valorFinal = valorFinal;
    }

    public LocalDate getDataInicial() {
        return dataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        this.dataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        this.dataFinal = dataFinal;
    }

    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(Relacionamento relacionamento) {
        this.relacionamento = relacionamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

}