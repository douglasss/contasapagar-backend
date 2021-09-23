package com.douglasss.contasapagarbackend;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class CriarContaRequest {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    private BigDecimal valorOriginal;
    @NotNull
    private LocalDate dataVencimento;
    @NotNull
    private LocalDate dataPagamento;
}
