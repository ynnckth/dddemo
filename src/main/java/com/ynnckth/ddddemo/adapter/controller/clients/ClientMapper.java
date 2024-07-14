package com.ynnckth.ddddemo.adapter.controller.clients;

import com.ynnckth.ddddemo.core.domain.Client;

public class ClientMapper {

    static ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getDisplayName(),
                new ClientDto.AmountDto(client.getAssetsUnderManagement().value().toPlainString(), client.getAssetsUnderManagement().currency().isoCode()),
                new ClientDto.AmountDto(client.getNetRevenue().value().toPlainString(), client.getNetRevenue().currency().isoCode())
        );
    }
}
