package com.ynnckth.ddddemo.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void givenClientWithKeyFiguresInDifferentCurrencies_whenGetAllDistinctCurrencies_returnsDistinctCurrencies() {
        Client client = new Client("0001", "Alice",
                new Amount(BigDecimal.valueOf(1_000), new Currency("SGD")),
                new Amount(BigDecimal.valueOf(1_000), new Currency("HKD")));

        List<Currency> usedCurrencies = client.getAllDistinctUsedCurrencies();

        assertThat(usedCurrencies).hasSize(2);
        assertThat(usedCurrencies).contains(new Currency("SGD"), new Currency("HKD"));
    }

    @Test
    void givenClientWithAllKeyFiguresInSameCurrencies_whenGetAllDistinctCurrencies_returnsDistinctCurrencies() {
        Client client = new Client("0001", "Alice",
                new Amount(BigDecimal.valueOf(1_000), new Currency("SGD")),
                new Amount(BigDecimal.valueOf(2_000), new Currency("SGD")));

        List<Currency> usedCurrencies = client.getAllDistinctUsedCurrencies();

        assertThat(usedCurrencies).hasSize(1);
        assertThat(usedCurrencies).contains(new Currency("SGD"));
    }

    @Test
    void convertKeyFigures() {
        LocalDate today = LocalDate.parse("2024-07-01");
        Client client = new Client("0001", "Alice",
                new Amount(BigDecimal.valueOf(1_000), new Currency("SGD")),
                new Amount(BigDecimal.valueOf(2_000), new Currency("HKD")));

        List<ExchangeRate> exchangeRates = List.of(
                new ExchangeRate(new Currency("SGD"), new Currency("CHF"), BigDecimal.valueOf(0.5), today),
                new ExchangeRate(new Currency("HKD"), new Currency("CHF"), BigDecimal.valueOf(0.5), today));


        client.convertKeyFigures(exchangeRates);

        assertThat(client.getAssetsUnderManagement()).isEqualTo(new Amount(BigDecimal.valueOf(500.0), new Currency("CHF")));
        assertThat(client.getNetRevenue()).isEqualTo(new Amount(BigDecimal.valueOf(1_000.0), new Currency("CHF")));
    }
}