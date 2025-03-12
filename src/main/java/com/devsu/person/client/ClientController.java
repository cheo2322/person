package com.devsu.person.client;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/test/v1/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping
    public ClientDto createClient(@RequestBody Client client) {
        return clientService.createClient(client);
    }

    @GetMapping
    public List<ClientDto> getClients() {
        return clientService.getClients();
    }

    @GetMapping("/{clientId}")
    public ClientDto getClient(@PathVariable String clientId) {
        return clientService.getClient(clientId);
    }

    @PutMapping("/{clientId}")
    public ClientDto updateClient(@PathVariable String clientId, @RequestBody ClientDto clientDto) {
        clientDto.setClientId(clientId);
        return clientService.updateClient(clientDto);
    }

    @DeleteMapping("/{clientId}")
    public ClientDto deleteClient(@PathVariable String clientId) {
        return clientService.deleteClient(clientId);
    }
}