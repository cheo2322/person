package service;

import entity.Client;
import entity.dto.ClientDto;

import java.util.List;

public class ClientServiceImpl implements ClientService {
    @Override
    public ClientDto createClient(Client client) {
        return null;
    }

    @Override
    public List<ClientDto> getClients() {
        return List.of();
    }

    @Override
    public ClientDto getClient(String clientId) {
        return null;
    }

    @Override
    public ClientDto updateClient(ClientDto clientDto) {
        return null;
    }

    @Override
    public ClientDto deleteClient(String clientId) {
        return null;
    }
}
