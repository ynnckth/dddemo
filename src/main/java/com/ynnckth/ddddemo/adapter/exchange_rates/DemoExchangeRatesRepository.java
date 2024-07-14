package com.ynnckth.ddddemo.adapter.exchange_rates;

import com.ynnckth.ddddemo.domain.Currency;
import com.ynnckth.ddddemo.domain.ExchangeRate;
import com.ynnckth.ddddemo.use_cases.ports.ExchangeRatesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DemoExchangeRatesRepository implements ExchangeRatesRepository {
    @Override
    public ExchangeRate getExchangeRate(Currency baseCurrency, Currency targetCurrency, LocalDate date) {
        // TODO: implement e.g. using this free API: https://exchangeratesapi.io/documentation/
        return null;
    }
}
