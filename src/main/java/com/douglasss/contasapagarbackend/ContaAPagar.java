package com.douglasss.contasapagarbackend;

import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;

@Getter
public class ContaAPagar {
    private String nome;
    private Dinheiro valorOriginal;
    private LocalDate dataVencimento;
    private LocalDate dataPagamento;
    private BigDecimal percentualMulta;
    private BigDecimal percentualJurosPorDia;
    private Dinheiro valorCorrigido;
    private Integer diasEmAtraso;

    public ContaAPagar(String nome, Dinheiro valorOriginal, LocalDate dataVencimento) {
        this.nome = Optional.ofNullable(nome)
                .orElseThrow(() -> new RuntimeException("O nome é obrigatório para conta"));
        this.valorOriginal = Optional.ofNullable(valorOriginal)
                .orElseThrow(() -> new RuntimeException("O valor original é obrigatório para conta"));
        this.dataVencimento = Optional.ofNullable(dataVencimento)
                .orElseThrow(() -> new RuntimeException("A data de vencimento é obrigatória para conta"));
    }

    public void pagar(LocalDate dataPagamento, CalculadoraPercentuaisAtraso calculadoraPercentuais) {
        this.dataPagamento = Optional.ofNullable(dataPagamento)
                .orElseThrow(() -> new RuntimeException("A data de pagamento é obrigatória"));
        this.diasEmAtraso = calcularDiasEmAtraso();
        calcularPendenciasPagamento(calculadoraPercentuais.calcular(diasEmAtraso));
    }

    private void calcularPendenciasPagamento(PercentuaisAtraso percentuaisAtraso) {
        percentualMulta = percentuaisAtraso.getPercentualMulta();
        percentualJurosPorDia = percentuaisAtraso.getPercentualJurosPorDia();
        this.valorCorrigido = calcularValorCorrigido(diasEmAtraso, percentualMulta, percentualJurosPorDia);
    }

    private Dinheiro calcularValorCorrigido(Integer diasEmAtraso, BigDecimal percentualMulta, BigDecimal percentualJurosPorDia) {
        return valorOriginal.somar(valorOriginal.percentualDe(percentualMulta))
                .somar(valorOriginal.percentualDe(getPercentualTotalJuros(diasEmAtraso, percentualJurosPorDia)));
    }

    private BigDecimal getPercentualTotalJuros(Integer diasEmAtraso, BigDecimal percentualJurosPorDia) {
        return percentualJurosPorDia.multiply(BigDecimal.valueOf(diasEmAtraso));
    }

    private int calcularDiasEmAtraso() {
        int periodoEmDiasEntreVencimentoEPagamento = Period
                .between(this.dataVencimento, this.dataPagamento).getDays();
        //Caso seja negativo, não está em atraso, portanto retornará 0
        return Math.max(periodoEmDiasEntreVencimentoEPagamento, 0);
    }
}
