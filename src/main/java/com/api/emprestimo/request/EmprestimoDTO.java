package com.api.emprestimo.request;

import com.api.emprestimo.entities.Cliente;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.time.LocalDate;

public class EmprestimoDTO {

    @NotBlank
    private BigDecimal ValorInicial;
    @NotBlank
    private BigDecimal ValorFinal;
    @NotBlank
    private LocalDate DataInicial;
    @NotBlank
    private LocalDate DataFinal;

    //Construtor

    //Getters e Setters


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
}
