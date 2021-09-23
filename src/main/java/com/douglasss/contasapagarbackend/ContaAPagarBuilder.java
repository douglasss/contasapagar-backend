package com.douglasss.contasapagarbackend;

import java.math.BigDecimal;
import java.time.LocalDate;


public class ContaAPagarBuilder {
    private final CalculadoraPercentuaisAtraso calculadoraPercentuais;

    private String nome;
    private Dinheiro valorOriginal;
    private LocalDate dataVencimento;

    public ContaAPagarBuilder(CalculadoraPercentuaisAtraso calculadoraPercentuais) {
        this.calculadoraPercentuais = calculadoraPercentuais;
    }

    public ContaAPagarBuilder comNome(String nome) {
        this.nome = nome;
        return this;
    }

    public ContaAPagarBuilder comValor(BigDecimal valorOriginal) {
        this.valorOriginal = Dinheiro.criar(valorOriginal);
        return this;
    }

    public ContaAPagarBuilder dataDeVencimentoEm(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
        return this;
    }

    public ContaAPagar pagoNaData(LocalDate dataPagamento) {
        ContaAPagar conta = new ContaAPagar(
                this.nome,
                this.valorOriginal,
                this.dataVencimento
        );
        conta.pagar(dataPagamento, calculadoraPercentuais);
        return conta;
    }
}
