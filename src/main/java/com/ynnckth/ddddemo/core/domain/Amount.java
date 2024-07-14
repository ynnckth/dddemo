package com.ynnckth.ddddemo.core.domain;

import java.math.BigDecimal;
import java.util.List;

/**
 * This would be considered a Value Object in DDD terminology and is therefore immutable
 */
public record Amount(BigDecimal value, Currency currency) {

    Amount convert(ExchangeRate exchangeRate) {
        if (!exchangeRate.baseCurrency().equals(currency)) {
            throw new IllegalArgumentException("Base currency doesn't match");
        }
        if (exchangeRate.targetCurrency().equals(currency)) {
            return this;
        }

        BigDecimal convertedValue = value.multiply(exchangeRate.rate());
        return new Amount(convertedValue, exchangeRate.targetCurrency());
    }

    Amount findApplicableExchangeRateAndConvert(List<ExchangeRate> exchangeRates) {
        ExchangeRate applicableExchangeRate = findApplicableExchangeRate(exchangeRates);
        return convert(applicableExchangeRate);
    }

    private ExchangeRate findApplicableExchangeRate(List<ExchangeRate> exchangeRates) {
        return exchangeRates.stream()
                .filter(fxRate -> fxRate.baseCurrency().equals(currency))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(String.format("Exchange rate for currency %s not found", currency())));
    }
}
