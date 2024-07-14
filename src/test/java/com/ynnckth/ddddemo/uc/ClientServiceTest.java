package com.ynnckth.ddddemo.uc;

import com.ynnckth.ddddemo.adapter.clients.ClientsRepository;
import com.ynnckth.ddddemo.adapter.exchange_rates.ExchangeRatesRepository;
import com.ynnckth.ddddemo.domain.Amount;
import com.ynnckth.ddddemo.domain.Client;
import com.ynnckth.ddddemo.domain.Currency;
import com.ynnckth.ddddemo.domain.ExchangeRate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ClientServiceTest {

    @Mock
    ExchangeRatesRepository exchangeRatesRepository;

    @Mock
    ClientsRepository clientsRepository;

    @InjectMocks
    private ClientService clientService;

    @Test
    void givenClientWithKeyFiguresInDifferentBaseCurrencies_whenGetClients_returnsSameClientsWithConvertedKeyFigures() {
        LocalDate today = LocalDate.parse("2024-07-01");
        when(clientsRepository.getClients())
                .thenReturn(List.of(new Client("0001", "Alice",
                        new Amount(BigDecimal.valueOf(10_000), new Currency("CHF")),
                        new Amount(BigDecimal.valueOf(20_000), new Currency("EUR")))
                ));
        when(exchangeRatesRepository.getExchangeRate(new Currency("CHF"), new Currency("SGD"), today))
                .thenReturn(new ExchangeRate(new Currency("CHF"), new Currency("SGD"), BigDecimal.valueOf(0.5), today));
        when(exchangeRatesRepository.getExchangeRate(new Currency("EUR"), new Currency("SGD"), today))
                .thenReturn(new ExchangeRate(new Currency("EUR"), new Currency("SGD"), BigDecimal.valueOf(0.5), today));


        List<Client> clientsWithConvertedKeyFigures = clientService.getClients(new Currency("SGD"), today);

        assertThat(clientsWithConvertedKeyFigures).hasSize(1);
        assertThat(clientsWithConvertedKeyFigures.getFirst().getAssetsUnderManagement()).isEqualTo(new Amount(BigDecimal.valueOf(5_000.0), new Currency("SGD")));
        assertThat(clientsWithConvertedKeyFigures.getFirst().getNetRevenue()).isEqualTo(new Amount(BigDecimal.valueOf(10_000.0), new Currency("SGD")));
    }
}