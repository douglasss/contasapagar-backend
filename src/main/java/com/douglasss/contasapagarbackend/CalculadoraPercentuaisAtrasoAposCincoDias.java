package com.douglasss.contasapagarbackend;

import java.math.BigDecimal;

public class CalculadoraPercentuaisAtrasoAposCincoDias extends CalculadoraPercentuaisAtraso{

    public CalculadoraPercentuaisAtrasoAposCincoDias(CalculadoraPercentuaisAtraso proximo) {
        super(proximo);
    }

    @Override
    protected PercentuaisAtraso percentuaisAtraso() {
        return new PercentuaisAtraso(new BigDecimal("5"), new BigDecimal("0.3"));
    }

    @Override
    protected boolean estaDentroDoPeriodoAtraso(int diasEmAtraso) {
        return diasEmAtraso > 5;
    }
}
