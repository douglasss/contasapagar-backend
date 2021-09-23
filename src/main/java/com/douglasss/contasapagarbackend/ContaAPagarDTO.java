package com.douglasss.contasapagarbackend;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContaAPagarDTO {
    private String nome;
    private BigDecimal valorOriginal;
    private BigDecimal valorCorrigido;
    private Integer diasEmAtraso;
    private LocalDate dataPagamento;

    public static ContaAPagarDTO criarDaEntidade(ContaAPagar conta) {
        return new ContaAPagarDTO(
                conta.getNome(),
                conta.getValorOriginal().getValor(),
                conta.getValorCorrigido().getValor(),
                conta.getDiasEmAtraso(),
                conta.getDataPagamento()
        );
    }
}
