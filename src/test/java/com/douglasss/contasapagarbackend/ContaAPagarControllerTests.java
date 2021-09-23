package com.douglasss.contasapagarbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test")
@WebMvcTest(ContaAPagarController.class)
public class ContaAPagarControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private CalculadoraPercentuaisAtraso calculadoraPercentuaisAtraso;

    @MockBean
    private ContaAPagarRepository contaAPagarRepository;

    @BeforeEach
    public void setup() {
        when(contaAPagarRepository.salvar(any())).then(returnsFirstArg());
    }

    @Test
    public void testCriarConta() throws Exception {
        CriarContaRequest request = new CriarContaRequest();
        request.setDataPagamento(LocalDate.now());
        request.setNome("Douglas");
        request.setDataVencimento(LocalDate.now());
        request.setDataPagamento(LocalDate.now());
        request.setValorOriginal(BigDecimal.TEN);

        mockMvc.perform(
                post("/api/v1/contasapagar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk());

        verify(contaAPagarRepository,
                times(1)).salvar(any());
    }
}
