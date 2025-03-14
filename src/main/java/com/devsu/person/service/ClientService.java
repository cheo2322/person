package com.devsu.person.service;

import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.entity.dto.ClientRecord;

import java.util.List;

public interface ClientService {

    void createClient(ClientRecord client);
    List<ClientDto> getClients();
    ClientDto getClient(String clientId);
    ClientDto updateClient(ClientRecord client);
    ClientDto deleteClient(String clientId);
}
