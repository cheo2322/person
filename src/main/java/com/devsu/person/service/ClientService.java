package com.devsu.person.service;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto createClient(Client client);
    List<ClientDto> getClients();
    ClientDto getClient(String clientId);
    ClientDto updateClient(ClientDto clientDto);
    ClientDto deleteClient(String clientId);
}
