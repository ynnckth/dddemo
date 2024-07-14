package com.ynnckth.ddddemo.use_cases.ports;

import com.ynnckth.ddddemo.domain.Currency;
import com.ynnckth.ddddemo.domain.ExchangeRate;

import java.time.LocalDate;

public interface ExchangeRatesRepository {
    ExchangeRate getExchangeRate(Currency baseCurrency, Currency targetCurrency, LocalDate date);
}
