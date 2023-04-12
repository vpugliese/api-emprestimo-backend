package com.api.emprestimo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EmprestimoException extends Exception {

    private static final long serialVersionUID = 1L;

    public EmprestimoException(Long id) {
        super(String.format("O empréstimo de id %s não foi encontrado.", id));
    }

}
