package com.ynnckth.ddddemo.adapter.clients;

import com.ynnckth.ddddemo.core.domain.Client;
import com.ynnckth.ddddemo.core.application.outbound_ports.ClientsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DemoClientsRepository implements ClientsRepository {
    @Override
    public List<Client> getClients() {
        // TODO: implement
        return List.of();
    }
}
