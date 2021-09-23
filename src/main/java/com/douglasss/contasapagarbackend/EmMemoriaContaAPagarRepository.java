package com.douglasss.contasapagarbackend;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmMemoriaContaAPagarRepository implements ContaAPagarRepository {

    private final List<ContaAPagar> contas;

    public EmMemoriaContaAPagarRepository() {
        contas = new ArrayList<>();
    }

    @Override
    public ContaAPagar salvar(ContaAPagar conta) {
        contas.add(conta);
        return conta;
    }

    @Override
    public List<ContaAPagar> findAll() {
        return Collections.unmodifiableList(contas);
    }
}
