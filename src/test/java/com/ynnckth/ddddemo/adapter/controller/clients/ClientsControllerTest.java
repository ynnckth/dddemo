package com.ynnckth.ddddemo.adapter.controller.clients;

import com.ynnckth.ddddemo.core.domain.Amount;
import com.ynnckth.ddddemo.core.domain.Client;
import com.ynnckth.ddddemo.core.domain.Currency;
import com.ynnckth.ddddemo.core.application.use_cases.GetClientsUseCase;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientsControllerTest {

    private final GetClientsUseCase getClientsUseCase = mock(GetClientsUseCase.class);

    private final MockMvc mockMvc = MockMvcBuilders
            .standaloneSetup(new ClientsController(getClientsUseCase))
            .build();

    @Test
    void getClients_clientsReturned() throws Exception {
        when(getClientsUseCase.invoke(new Currency("SGD"), LocalDate.parse("2024-07-01")))
                .thenReturn(List.of(new Client("0001", "Alice",
                                new Amount(BigDecimal.valueOf(1_000), new Currency("SGD")),
                                new Amount(BigDecimal.valueOf(2_000), new Currency("SGD")))));

        this.mockMvc.perform(get("/clients?targetCurrency=SGD&exchangeRateDate=2024-07-01").contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[*].id").value("0001"))
                .andExpect(jsonPath("$.[*].displayName").value("Alice"))
                .andExpect(jsonPath("$.[*].assetsUnderManagement.value").value("1000"))
                .andExpect(jsonPath("$.[*].assetsUnderManagement.currency").value("SGD"))
                .andExpect(jsonPath("$.[*].netRevenue.value").value("2000"))
                .andExpect(jsonPath("$.[*].netRevenue.currency").value("SGD"));
    }

}