package com.api.emprestimo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "Clientes")
public class Cliente {

    //Atributos
    @Id
    private String cpf;
    @NotBlank(message = "Insira o nome.")
    private String nome;
    @Column(unique = true)
    private String telefone;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
    private BigDecimal rendimentoMensal;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cpfCliente")
    private List<Emprestimo> emprestimos;

    //Construtores
    public Cliente() {
    }

    public Cliente(String cpf, String nome, String telefone, Endereco endereco, BigDecimal rendimentoMensal, List<Emprestimo> emprestimos) {
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
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
