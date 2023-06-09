package com.api.emprestimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClienteJaCadastrado extends Exception{
    private static final long serialVersionUID = 1L;
    public ClienteJaCadastrado(String cpf) {
        super(String.format("O CPF %s já está cadastrado.", cpf));
    }
}
