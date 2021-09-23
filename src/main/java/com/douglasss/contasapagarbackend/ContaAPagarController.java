package com.douglasss.contasapagarbackend;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/contasapagar")
public class ContaAPagarController {

    private final CalculadoraPercentuaisAtraso calculadoraPercentuaisAtraso;
    private final ContaAPagarRepository repository;

    public ContaAPagarController(CalculadoraPercentuaisAtraso calculadoraPercentuaisAtraso,
                                 ContaAPagarRepository repository) {
        this.calculadoraPercentuaisAtraso = calculadoraPercentuaisAtraso;
        this.repository = repository;
    }

    @PostMapping
    public ContaAPagarDTO criar(@RequestBody @Validated CriarContaRequest request) {
        ContaAPagar contaAPagar = new ContaAPagarBuilder(calculadoraPercentuaisAtraso)
                .comNome(request.getNome())
                .comValor(request.getValorOriginal())
                .dataDeVencimentoEm(request.getDataVencimento())
                .pagoNaData(request.getDataPagamento());
        return ContaAPagarDTO.criarDaEntidade(repository.salvar(contaAPagar));

    }
}
