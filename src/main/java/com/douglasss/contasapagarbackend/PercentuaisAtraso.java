package com.douglasss.contasapagarbackend;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class PercentuaisAtraso {
    private BigDecimal percentualMulta;
    private BigDecimal percentualJurosPorDia;

    public static PercentuaisAtraso semPercentual() {
        return new PercentuaisAtraso(BigDecimal.ZERO, BigDecimal.ZERO);
    }
}
