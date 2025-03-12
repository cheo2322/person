package com.devsu.person.service.impl;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.dto.ClientDto;
import org.springframework.stereotype.Service;
import com.devsu.person.repository.ClientRepository;
import com.devsu.person.service.ClientService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientDto createClient(Client client) {
        Client savedClient = clientRepository.save(client);
        return toDto(savedClient);
    }

    @Override
    public List<ClientDto> getClients() {
        return clientRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto getClient(String clientId) {
        Client client = clientRepository.findByClientId(clientId);
        return client != null ? toDto(client) : null;
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        Client client = clientRepository.findByClientId(clientDto.getClientId());
        if (client != null) {
            client.setPassword(clientDto.getPassword());
            client.setStatus(clientDto.getStatus());
            Client updatedClient = clientRepository.save(client);
            return toDto(updatedClient);
        }
        return null;
    }

    @Override
    public ClientDto deleteClient(String clientId) {
        Client client = clientRepository.findByClientId(clientId);
        if (client != null) {
            clientRepository.delete(client);
            return toDto(client);
        }
        return null;
    }

    private ClientDto toDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setClientId(client.getClientId());
        clientDto.setPassword(client.getPassword());
        clientDto.setStatus(client.getStatus());
        return clientDto;
    }
}
