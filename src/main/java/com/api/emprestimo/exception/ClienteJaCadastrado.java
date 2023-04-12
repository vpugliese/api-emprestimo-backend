package com.api.emprestimo.exception;

import com.api.emprestimo.entities.Cliente;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClienteNaoEncontrado extends Exception{
    private static final long serialVersionUID = 1L;

    public ClienteNaoEncontrado(Long cpf) {
        super(String.format("O CPF %s já está cadastrado.", cpf));
    }
}
