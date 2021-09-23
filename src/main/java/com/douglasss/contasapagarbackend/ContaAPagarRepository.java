package com.douglasss.contasapagarbackend;

import java.util.List;

public interface ContaAPagarRepository {

    ContaAPagar salvar(ContaAPagar conta);

    List<ContaAPagar> findAll();
}
