package com.ynnckth.ddddemo.adapter.exchange_rates;

import com.ynnckth.ddddemo.core.domain.Currency;
import com.ynnckth.ddddemo.core.domain.ExchangeRate;
import com.ynnckth.ddddemo.core.application.outbound_ports.ExchangeRatesRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DemoExchangeRatesRepository implements ExchangeRatesRepository {
    @Override
    public ExchangeRate getExchangeRate(Currency baseCurrency, Currency targetCurrency, LocalDate date) {
        // Mocked random exchange rate (imagine an external API call here...)
        return new ExchangeRate(baseCurrency, targetCurrency, BigDecimal.valueOf(0.1 + Math.random() * (5 - 0.1)), date);
    }
}
