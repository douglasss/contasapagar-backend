package com.douglasss.contasapagarbackend;

public abstract class CalculadoraPercentuaisAtraso {

    private final CalculadoraPercentuaisAtraso proximo;

    public CalculadoraPercentuaisAtraso(CalculadoraPercentuaisAtraso proximo) {
        this.proximo = proximo;
    }

    public CalculadoraPercentuaisAtraso() {
        this.proximo = null;
    }

    public final PercentuaisAtraso calcular(int diasEmAtraso) {
        if (estaDentroDoPeriodoAtraso(diasEmAtraso)) {
            return percentuaisAtraso();
        }
        return temProximo() ? proximo.calcular(diasEmAtraso) : PercentuaisAtraso.semPercentual();
    }

    private boolean temProximo() {
        return proximo != null;
    }

    protected abstract PercentuaisAtraso percentuaisAtraso();

    protected abstract boolean estaDentroDoPeriodoAtraso(int diasEmAtraso);
}
