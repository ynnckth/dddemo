package com.ynnckth.ddddemo.adapter.exchange_rates;

import com.ynnckth.ddddemo.domain.Currency;
import com.ynnckth.ddddemo.domain.ExchangeRate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DemoExchangeRatesRepository implements ExchangeRatesRepository {
    @Override
    public ExchangeRate getExchangeRate(Currency baseCurrency, Currency targetCurrency, LocalDate date) {
        // TODO: implement
        return null;
    }
}
