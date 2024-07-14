package com.ynnckth.ddddemo.core.application.inbound_ports;

import com.ynnckth.ddddemo.core.domain.Client;
import com.ynnckth.ddddemo.core.domain.Currency;

import java.time.LocalDate;
import java.util.List;

public interface GetClientsUseCase {

    /**
     * Get list of all clients in specified target currency and exchange rate date.
     * For example, get all clients with all key figures in SGD using the exchange rate from 01.07.2024
     */
    List<Client> getClients(Currency targetCurrency, LocalDate exchangeRateDate);
}
