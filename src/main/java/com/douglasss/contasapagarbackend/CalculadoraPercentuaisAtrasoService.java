package com.douglasss.contasapagarbackend;

import org.springframework.stereotype.Service;

@Service
public class CalculadoraPercentuaisAtrasoService extends CalculadoraPercentuaisAtraso {

    public CalculadoraPercentuaisAtrasoService() {
        super(new CalculadoraPercentuaisAtrasoAposCincoDias(
                new CalculadoraPercentuaisAtrasoAposTresDias(
                        new CalculadoraPercentuaisAtrasoAteTresDias(null)
                )
        ));
    }

    @Override
    protected PercentuaisAtraso percentuaisAtraso() {
        throw new RuntimeException("Classe base para calculadora, sem implementação de percentual");
    }

    @Override
    protected boolean estaDentroDoPeriodoAtraso(int diasEmAtraso) {
        return false;
    }
}
