package com.douglasss.contasapagarbackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.douglasss.contasapagarbackend.Dinheiro.criar;
import static org.assertj.core.api.Assertions.assertThat;

public class DinheiroTests {

    @DisplayName("Deve ser possível criar um Dinheiro com um valor BigDecimal")
    @Test
    public void testCriarDinheiro() {
        Dinheiro dinheiro = Dinheiro.criar(new BigDecimal("200"));
        assertThat(dinheiro).isNotNull();
        assertThat(dinheiro.getValor()).isEqualByComparingTo(new BigDecimal("200"));
    }

    @DisplayName("Deve somar um valor 200 mais um valor de 100  e retornar Dinheiro com 300")
    @Test
    public void testSomar() {
        Dinheiro dinheiro = criar(new BigDecimal("200"));
        assertThat(dinheiro.somar(criar(new BigDecimal("100"))).getValor())
                .isEqualByComparingTo(new BigDecimal("300"));
    }

    @DisplayName("Deve retornar o percentual de 25% do vlaor de 199.99 e dar 50")
    @Test
    public void testPercentual() {
        BigDecimal valorInicial = new BigDecimal("199.99");
        Dinheiro dinheiro = criar(valorInicial);
        BigDecimal percentual = new BigDecimal("25");
        assertThat(dinheiro.percentualDe(percentual).getValor())
                .isEqualByComparingTo(new BigDecimal("50"));
    }
}
