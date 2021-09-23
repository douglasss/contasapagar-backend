package com.douglasss.contasapagarbackend;

import java.math.BigDecimal;

public class CalculadoraPercentuaisAtrasoAposTresDias extends CalculadoraPercentuaisAtraso{

    public CalculadoraPercentuaisAtrasoAposTresDias(CalculadoraPercentuaisAtraso proximo) {
        super(proximo);
    }

    @Override
    protected PercentuaisAtraso percentuaisAtraso() {
        return new PercentuaisAtraso(new BigDecimal("3"), new BigDecimal("0.2"));
    }

    @Override
    protected boolean estaDentroDoPeriodoAtraso(int diasEmAtraso) {
        return diasEmAtraso > 3;
    }
}
