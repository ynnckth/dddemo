package com.ynnckth.ddddemo.core.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public record ExchangeRate(Currency baseCurrency, Currency targetCurrency, BigDecimal rate, LocalDate date) {
}
