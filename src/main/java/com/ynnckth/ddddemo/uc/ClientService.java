package com.ynnckth.ddddemo.uc;

import com.ynnckth.ddddemo.adapter.clients.ClientsRepository;
import com.ynnckth.ddddemo.adapter.exchange_rates.ExchangeRatesRepository;
import com.ynnckth.ddddemo.domain.Client;
import com.ynnckth.ddddemo.domain.Currency;
import com.ynnckth.ddddemo.domain.ExchangeRate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ClientService implements GetClientsUseCase {

    private final ClientsRepository clientsRepository;
    private final ExchangeRatesRepository exchangeRatesRepository;

    public ClientService(ClientsRepository clientsRepository, ExchangeRatesRepository exchangeRatesRepository) {
        this.clientsRepository = clientsRepository;
        this.exchangeRatesRepository = exchangeRatesRepository;
    }

    @Override
    public List<Client> getClients(Currency targetCurrency, LocalDate exchangeRateDate) {
        List<Client> clients = clientsRepository.getClients();
        List<ExchangeRate> exchangeRates = getRequiredExchangeRates(targetCurrency, exchangeRateDate, clients);

        for (Client client : clients) {
            client.convertKeyFigures(exchangeRates);
        }
        return clients;
    }

    private List<ExchangeRate> getRequiredExchangeRates(Currency targetCurrency, LocalDate exchangeRateDate, List<Client> clients) {
        List<Currency> usedCurrencies = clients.stream()
                .flatMap(client -> client.getAllDistinctUsedCurrencies().stream())
                .distinct()
                .toList();
        return usedCurrencies.parallelStream()
                .map(currency -> exchangeRatesRepository.getExchangeRate(currency, targetCurrency, exchangeRateDate))
                .toList();
    }
}
