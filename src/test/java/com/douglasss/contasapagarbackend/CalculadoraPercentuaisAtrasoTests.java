package com.douglasss.contasapagarbackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CalculadoraPercentuaisAtrasoTests {

    private CalculadoraPercentuaisAtraso getCalculadoraChain() {
        return new CalculadoraPercentuaisAtrasoService();
    }

    @DisplayName("Para um atraso de 1 dia deve retornar multa de 2 e juros de 0.1")
    @Test
    public void testCalcularPercentualUmDiaAtraso() {
        CalculadoraPercentuaisAtraso calculadora = getCalculadoraChain();
        PercentuaisAtraso percentuais = calculadora.calcular(1);
        assertThat(percentuais).isNotNull();
        assertThat(percentuais.getPercentualMulta()).isEqualByComparingTo("2");
        assertThat(percentuais.getPercentualJurosPorDia()).isEqualByComparingTo("0.1");
    }

    @DisplayName("Para um atraso de 4 dias deve retornar multa de 3 e juros de 0.2")
    @Test
    public void testCalcularPercentualDoisDiasAtraso() {
        CalculadoraPercentuaisAtraso calculadora = getCalculadoraChain();
        PercentuaisAtraso percentuais = calculadora.calcular(4);
        assertThat(percentuais).isNotNull();
        assertThat(percentuais.getPercentualMulta()).isEqualByComparingTo("3");
        assertThat(percentuais.getPercentualJurosPorDia()).isEqualByComparingTo("0.2");
    }

    @DisplayName("Para um atraso de 7 dias deve retornar multa de 5 e juros de 0.3")
    @Test
    public void testCalcularPercentualSeteDiasAtraso() {
        CalculadoraPercentuaisAtraso calculadora = getCalculadoraChain();
        PercentuaisAtraso percentuais = calculadora.calcular(7);
        assertThat(percentuais).isNotNull();
        assertThat(percentuais.getPercentualMulta()).isEqualByComparingTo("5");
        assertThat(percentuais.getPercentualJurosPorDia()).isEqualByComparingTo("0.3");
    }
}
