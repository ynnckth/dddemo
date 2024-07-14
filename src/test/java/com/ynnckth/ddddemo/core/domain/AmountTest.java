package com.ynnckth.ddddemo.core.domain;

import com.ynnckth.ddddemo.core.application.exception.MissingExchangeRateException;
import com.ynnckth.ddddemo.core.domain.exception.WrongBaseCurrencyException;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static java.util.Collections.emptyList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountTest {

    @Test
    void givenAmount_whenConvertedUsingValidFxRate_returnsConvertedAmount() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(100_000), new Currency("CHF"));
        ExchangeRate fxRate = new ExchangeRate(new Currency("CHF"), new Currency("SGD"), BigDecimal.valueOf(0.5), LocalDate.now());

        Amount convertedAmount = originalAmount.convert(fxRate);

        assertThat(convertedAmount.currency()).isEqualTo(new Currency("SGD"));
        assertThat(convertedAmount.value()).isEqualTo(BigDecimal.valueOf(50_000.0));
    }

    @Test
    void givenAmount_whenConvertedUsingFxRateWithDifferentBaseCurrency_throwsException() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(100_000), new Currency("CHF"));
        ExchangeRate fxRate = new ExchangeRate(new Currency("SGD"), new Currency("CHF"), BigDecimal.valueOf(0.5), LocalDate.now());

        assertThrows(WrongBaseCurrencyException.class, () -> originalAmount.convert(fxRate));
    }

    @Test
    void givenAmount_whenConvertedUsingFxRateWithSameTargetCurrency_returnsSameAmount() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(100_000), new Currency("CHF"));
        ExchangeRate fxRate = new ExchangeRate(new Currency("CHF"), new Currency("CHF"), BigDecimal.valueOf(0.5), LocalDate.now());

        Amount convertedAmount = originalAmount.convert(fxRate);

        assertThat(convertedAmount.currency()).isEqualTo(new Currency("CHF"));
        assertThat(convertedAmount.value()).isEqualTo(BigDecimal.valueOf(100_000));
    }

    @Test
    void givenExchangeRatesContainingApplicableOne_whenConvert_returnsConvertedAmount() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(100_000), new Currency("CHF"));
        List<ExchangeRate> rates = List.of(
                new ExchangeRate(new Currency("CHF"), new Currency("SGD"), BigDecimal.valueOf(0.5), LocalDate.now()),
                new ExchangeRate(new Currency("CHF"), new Currency("HKD"), BigDecimal.valueOf(0.1), LocalDate.now())
        );

        Amount convertedAmount = originalAmount.findApplicableExchangeRateAndConvert(rates);

        assertThat(convertedAmount.currency()).isEqualTo(new Currency("SGD"));
        assertThat(convertedAmount.value()).isEqualTo(BigDecimal.valueOf(50_000.0));
    }

    @Test
    void givenExchangeRatesMissingApplicableOne_whenConvert_throwsException() {
        Amount originalAmount = new Amount(BigDecimal.valueOf(100_000), new Currency("CHF"));
        List<ExchangeRate> rates = emptyList();

        assertThrows(MissingExchangeRateException.class, () -> originalAmount.findApplicableExchangeRateAndConvert(rates));
    }

}