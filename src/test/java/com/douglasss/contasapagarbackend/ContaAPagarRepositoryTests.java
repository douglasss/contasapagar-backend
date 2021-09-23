package com.douglasss.contasapagarbackend;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ContaAPagarRepositoryTests {

    @DisplayName("Deve ser possível salvar uma conta no repositorio")
    @Test
    public void testSalvar() {
        ContaAPagarRepository repository = new EmMemoriaContaAPagarRepository();
        ContaAPagar conta = new ContaAPagarBuilder(new CalculadoraPercentuaisAtrasoService())
                .comNome("Douglas")
                .dataDeVencimentoEm(LocalDate.now())
                .comValor(new BigDecimal("10"))
                .pagoNaData(LocalDate.now());
        repository.salvar(conta);
        assertThat(repository.findAll()).hasSize(1);
    }

}
