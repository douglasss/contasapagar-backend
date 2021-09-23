package com.douglasss.contasapagarbackend;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Dinheiro {
    public static final BigDecimal DECIMAL_100 = new BigDecimal("100");
    private static final int SCALE_PERCENT = 4;
    private static final int MONEY_SCALE = 2;

    private final BigDecimal valor;

    private Dinheiro(BigDecimal valor) {
        this.valor = scale(valor);
    }

    public static Dinheiro criar(BigDecimal valor) {
        return new Dinheiro(valor);
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Dinheiro somar(Dinheiro soma) {
        return new Dinheiro(getValor().add(soma.getValor()));
    }

    public Dinheiro percentualDe(BigDecimal percentual) {
        return new Dinheiro(getValor().multiply(percentual)
                .divide(DECIMAL_100, SCALE_PERCENT, RoundingMode.HALF_UP));
    }

    private BigDecimal scale(BigDecimal value) {
        return value.setScale(MONEY_SCALE, RoundingMode.HALF_UP);
    }
}
