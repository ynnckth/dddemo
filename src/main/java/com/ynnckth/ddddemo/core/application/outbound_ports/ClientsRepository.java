package com.ynnckth.ddddemo.core.application.outbound_ports;

import com.ynnckth.ddddemo.core.domain.Client;

import java.util.List;

public interface ClientsRepository {
    List<Client> getClients();
}
