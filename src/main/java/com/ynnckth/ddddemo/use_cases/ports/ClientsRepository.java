package com.ynnckth.ddddemo.use_cases.ports;

import com.ynnckth.ddddemo.domain.Client;

import java.util.List;

public interface ClientsRepository {
    List<Client> getClients();
}
