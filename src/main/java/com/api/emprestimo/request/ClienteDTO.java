package com.api.emprestimo.request;

public class ClienteDTO {

    private Long cpf;
    private String nome;
    private Integer telefone;


    //Construtores

    public ClienteDTO() {
    }


    //Getters e Setters


    public void setCpf(Long cpf) {        this.cpf = cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTelefone(Integer telefone) {
        this.telefone = telefone;
    }

    public Long getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Integer getTelefone() {
        return telefone;
    }
}
