package com.api.emprestimo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Clientes")
public class Cliente {

    //Atributos
    @Id
    @NotNull
    @Column(unique = true)
    private String cpf;
    @NotBlank(message = "Insira o nome.")
    private String nome;
    @NotNull
    @Column(unique = true)
    private Long telefone;
    @Embedded
    private List<Endereco> endereco;
    @NotNull(message = "Insira a rendimento mensal.")
    private BigDecimal rendimentoMensal;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cpf")
    private List<Emprestimo> emprestimos;

    //Construtores
    public Cliente() {
    }

    public Cliente(String cpf, String nome, Long telefone, List<Endereco> endereco, BigDecimal rendimentoMensal, List<Emprestimo> emprestimos) {
        this.cpf = cpf;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
        this.rendimentoMensal = rendimentoMensal;
        this.emprestimos = emprestimos;
    }

    //Métodos
    public boolean podeReceberEmprestimo(BigDecimal valorInicial) {
        BigDecimal totalEmprestimos = this.getEmprestimos()
                .stream()
                .map(Emprestimo::getValorInicial)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        totalEmprestimos = totalEmprestimos.add(valorInicial);
        BigDecimal limiteEmprestimo = Optional.ofNullable(this.getRendimentoMensal())
                .map(rendimento -> rendimentoMensal.multiply(BigDecimal.TEN))
                .orElse(BigDecimal.ZERO);
        return totalEmprestimos.compareTo(limiteEmprestimo) <= 0;
    }

    //Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getTelefone() {
        return telefone;
    }

    public void setTelefone(Long telefone) {
        this.telefone = telefone;
    }

    public List<Endereco> getEndereco() {
        return endereco;
    }

    public void setEndereco(List<Endereco> endereco) {
        this.endereco = endereco;
    }

    public BigDecimal getRendimentoMensal() {
        return rendimentoMensal;
    }

    public void setRendimentoMensal(BigDecimal rendimentoMensal) {
        this.rendimentoMensal = rendimentoMensal;
    }

    public List<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {
        this.emprestimos = emprestimos;
    }
}
