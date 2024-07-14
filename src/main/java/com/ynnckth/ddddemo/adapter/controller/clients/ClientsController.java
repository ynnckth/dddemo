package com.ynnckth.ddddemo.adapter.controller.clients;

import com.ynnckth.ddddemo.core.domain.Client;
import com.ynnckth.ddddemo.core.domain.Currency;
import com.ynnckth.ddddemo.core.application.inbound_ports.GetClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientsController {

    private final GetClients getClients;

    public ClientsController(GetClients getClients) {
        this.getClients = getClients;
    }

    @GetMapping(produces = "application/json")
    public List<ClientDto> getClients(@RequestParam String targetCurrency, @RequestParam String exchangeRateDate) {
        List<Client> clients = getClients.invoke(new Currency(targetCurrency), LocalDate.parse(exchangeRateDate));
        return clients.stream()
                .map(ClientMapper::toDto)
                .toList();
    }

}
