package com.api.emprestimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteException extends Exception {

    private static final long serialVersionUID = 1L;

    public ClienteException(String cpf) {
        super(String.format("O cpf %s não foi encontrado.", cpf));
    }

}
