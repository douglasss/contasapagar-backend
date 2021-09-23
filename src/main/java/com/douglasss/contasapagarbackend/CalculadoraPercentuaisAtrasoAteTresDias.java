package com.douglasss.contasapagarbackend;

import java.math.BigDecimal;

public class CalculadoraPercentuaisAtrasoAteTresDias extends CalculadoraPercentuaisAtraso{

    public CalculadoraPercentuaisAtrasoAteTresDias(CalculadoraPercentuaisAtraso proximo) {
        super(proximo);
    }

    @Override
    protected PercentuaisAtraso percentuaisAtraso() {
        return new PercentuaisAtraso(new BigDecimal("2"), new BigDecimal("0.1"));
    }

    @Override
    protected boolean estaDentroDoPeriodoAtraso(int diasEmAtraso) {
        return diasEmAtraso <= 3;
    }
}
