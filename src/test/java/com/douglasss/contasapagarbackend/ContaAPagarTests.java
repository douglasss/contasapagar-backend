package com.douglasss.contasapagarbackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ContaAPagarTests {

    private CalculadoraPercentuaisAtraso getCalculadoraChain() {
        return new CalculadoraPercentuaisAtrasoService();
    }

    @DisplayName("Deve criar uma conta a pagar com nome, vencimento e valor original")
    @Test
    public void testCriarContaAPagar() {
        String nome = "Douglas";
        Dinheiro valorOriginal = Dinheiro.criar(new BigDecimal("200"));
        LocalDate dataVencimento = LocalDate.now();
        ContaAPagar contaAPagar = new ContaAPagar(nome, valorOriginal, dataVencimento);
        assertThat(contaAPagar.getValorOriginal()).isNotNull();
        assertThat(contaAPagar.getNome()).isEqualTo(nome);
        assertThat(contaAPagar.getDataVencimento()).isEqualTo(dataVencimento);
        assertThat(contaAPagar.getValorOriginal().getValor()).isEqualByComparingTo(new BigDecimal("200"));
    }

    @DisplayName("Não deve ser possível criar conta com os campos nulos")
    @Test
    public void testCriarContaAPagarComCampoNulo() {
        assertThrows(RuntimeException.class, () ->
                new ContaAPagar(null, null, null));
    }

    @DisplayName("Pagar uma conta na data de vencimento")
    @Test
    public void testPagarContaDataVencimento() {
        ContaAPagar conta = new ContaAPagar(
                "Douglas",
                Dinheiro.criar(BigDecimal.TEN),
                LocalDate.now()
        );
        LocalDate dataPagamento = LocalDate.now();
        conta.pagar(dataPagamento, getCalculadoraChain());
        assertThat(conta.getDataPagamento()).isEqualTo(dataPagamento);
    }

    @DisplayName("Pagar uma conta com atraso de um dia")
    @Test
    public void testPagarContaDataVencimentoAtrasada1Dia() {
        LocalDate dataVencimento = LocalDate.of(2021, 9, 1);
        ContaAPagar conta = new ContaAPagar(
                "Douglas",
                Dinheiro.criar(new BigDecimal("100")),
                dataVencimento
        );
        LocalDate dataPagamento = dataVencimento.plusDays(1);
        conta.pagar(dataPagamento, getCalculadoraChain());
        assertThat(conta.getDiasEmAtraso()).isEqualTo(1);
        assertThat(conta.getPercentualMulta()).isEqualByComparingTo(new BigDecimal("2"));
        assertThat(conta.getPercentualJurosPorDia()).isEqualByComparingTo(new BigDecimal("0.1"));
        assertThat(conta.getValorCorrigido().getValor()).isEqualByComparingTo(new BigDecimal("102.1"));
    }

    @DisplayName("Pagar uma conta com atraso de 4 dias")
    @Test
    public void testPagarContaDataVencimentoAtrasada4Dias() {
        LocalDate dataVencimento = LocalDate.of(2021, 9, 1);
        ContaAPagar conta = new ContaAPagar(
                "Douglas",
                Dinheiro.criar(new BigDecimal("100")),
                dataVencimento
        );
        LocalDate dataPagamento = dataVencimento.plusDays(4);
        conta.pagar(dataPagamento, getCalculadoraChain());
        assertThat(conta.getDiasEmAtraso()).isEqualTo(4);
        assertThat(conta.getPercentualMulta()).isEqualByComparingTo(new BigDecimal("3"));
        assertThat(conta.getPercentualJurosPorDia()).isEqualByComparingTo(new BigDecimal("0.2"));
        assertThat(conta.getValorCorrigido().getValor()).isEqualByComparingTo(new BigDecimal("103.8"));
    }

    @DisplayName("Pagar uma conta com atraso de 6 dias")
    @Test
    public void testPagarContaDataVencimentoAtrasada6Dias() {
        LocalDate dataVencimento = LocalDate.of(2021, 9, 1);
        ContaAPagar conta = new ContaAPagar(
                "Douglas",
                Dinheiro.criar(new BigDecimal("100")),
                dataVencimento
        );
        LocalDate dataPagamento = dataVencimento.plusDays(6);
        conta.pagar(dataPagamento, getCalculadoraChain());
        assertThat(conta.getDiasEmAtraso()).isEqualTo(6);
        assertThat(conta.getPercentualMulta()).isEqualByComparingTo(new BigDecimal("5"));
        assertThat(conta.getPercentualJurosPorDia()).isEqualByComparingTo(new BigDecimal("0.3"));
        assertThat(conta.getValorCorrigido().getValor()).isEqualByComparingTo(new BigDecimal("106.8"));
    }


}
