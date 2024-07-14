package com.ynnckth.ddddemo.core.application.outbound_ports;

import com.ynnckth.ddddemo.core.domain.Currency;
import com.ynnckth.ddddemo.core.domain.ExchangeRate;

import java.time.LocalDate;

public interface ExchangeRatesRepository {
    ExchangeRate getExchangeRate(Currency baseCurrency, Currency targetCurrency, LocalDate date);
}
