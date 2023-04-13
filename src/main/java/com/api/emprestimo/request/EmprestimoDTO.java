package com.api.emprestimo.request;

import com.api.emprestimo.enums.Relacionamento;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmprestimoDTO {

    private Long id;
    private String CpfCliente;
//    @NotBlank
    private BigDecimal ValorInicial;
//    @NotBlank
    private BigDecimal ValorFinal;
//    @NotBlank
    private LocalDate DataInicial;
//    @NotBlank
    private LocalDate DataFinal;
    private Relacionamento relacionamento;

    //Construtor

    public EmprestimoDTO() {
    }

    //Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpfCliente() {
        return CpfCliente;
    }

    public void setCpfCliente(String cpfCliente) {
        CpfCliente = cpfCliente;
    }

    public BigDecimal getValorInicial() {
        return ValorInicial;
    }

    public void setValorInicial(BigDecimal valorInicial) {
        ValorInicial = valorInicial;
    }

    public BigDecimal getValorFinal() {
        return ValorFinal;
    }

    public void setValorFinal(BigDecimal valorFinal) {
        ValorFinal = valorFinal;
    }

    public LocalDate getDataInicial() {
        return DataInicial;
    }

    public void setDataInicial(LocalDate dataInicial) {
        DataInicial = dataInicial;
    }

    public LocalDate getDataFinal() {
        return DataFinal;
    }

    public void setDataFinal(LocalDate dataFinal) {
        DataFinal = dataFinal;
    }

    public Relacionamento getRelacionamento() {
        return relacionamento;
    }

    public void setRelacionamento(Relacionamento relacionamento) {
        this.relacionamento = relacionamento;
    }
}
