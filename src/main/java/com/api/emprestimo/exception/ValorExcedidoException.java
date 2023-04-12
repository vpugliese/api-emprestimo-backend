package com.api.emprestimo.exception;

import java.math.BigDecimal;

public class ValorExcedidoException extends Exception{
    private static final long serialVersionUID = 1L;
    public ValorExcedidoException(String cpf) {
        super(String.format("O valor total dos empréstimos excede o valor máximo permitido do cliente %s.", cpf));
    }
}
