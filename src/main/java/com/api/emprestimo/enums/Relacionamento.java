package com.api.emprestimo.enums;

import java.math.BigDecimal;
import java.math.MathContext;

public enum Relacionamento {

    Bronze(1) {
        @Override
        public BigDecimal valorMultiplicado(BigDecimal valorInicial, Integer numDeEmprestimos) {
            return valorInicial.multiply(BigDecimal.valueOf(1.8), MathContext.DECIMAL32);
        }
    },

    Prata(2) {
        @Override
        public BigDecimal valorMultiplicado(BigDecimal valorInicial, Integer numDeEmprestimos) {
            if (valorInicial.compareTo(BigDecimal.valueOf(5000)) > 0) {
                return valorInicial.multiply(BigDecimal.valueOf(1.4), MathContext.DECIMAL32);
            }
            return valorInicial.multiply(BigDecimal.valueOf(1.6), MathContext.DECIMAL32);
        }
    },

    Ouro(3) {
        @Override
        public BigDecimal valorMultiplicado(BigDecimal valorInicial, Integer numDeEmprestimos) {
            if (numDeEmprestimos <= 1) {
                return valorInicial.multiply(BigDecimal.valueOf(1.2), MathContext.DECIMAL32);
            }
            return valorInicial.multiply(BigDecimal.valueOf(1.3), MathContext.DECIMAL32);
        }
    };

    public abstract BigDecimal valorMultiplicado(BigDecimal valorInicial, Integer numDeEmprestimos);

    private int codigo;

    private Relacionamento(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }
}
