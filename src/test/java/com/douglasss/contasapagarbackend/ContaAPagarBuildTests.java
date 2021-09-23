package com.douglasss.contasapagarbackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ContaAPagarBuildTests {

    private CalculadoraPercentuaisAtraso getCalculadoraChain() {
        return new CalculadoraPercentuaisAtrasoService();
    }

    @DisplayName("Deve criar uma conta a pagar")
    @Test
    public void testCriarConta() {
        LocalDate dataVencimento = LocalDate.now().minusDays(1);
        LocalDate dataPagamento = LocalDate.now();
        ContaAPagar conta = new ContaAPagarBuilder(getCalculadoraChain())
                .comNome("Douglas")
                .dataDeVencimentoEm(dataVencimento)
                .comValor(new BigDecimal("150"))
                .pagoNaData(dataPagamento);
        assertThat(conta).isNotNull();
        assertThat(conta.getNome()).isEqualTo("Douglas");
        assertThat(conta.getDataVencimento()).isEqualTo(dataVencimento);
        assertThat(conta.getValorOriginal().getValor()).isEqualByComparingTo("150");
        assertThat(conta.getDataPagamento()).isEqualTo(dataPagamento);
        assertThat(conta.getDiasEmAtraso()).isEqualByComparingTo(1);
    }


}
