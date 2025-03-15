package com.devsu.person.client;

import com.devsu.person.entity.Client;
import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.entity.dto.ClientRecord;
import com.devsu.person.service.ClientService;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/v1/clients")
public class ClientController {

  private final ClientService clientService;

  public ClientController(ClientService clientService) {
    this.clientService = clientService;
  }

  @PostMapping
  public ResponseEntity<Void> createClient(@RequestBody ClientRecord client) {
    clientService.createClient(client);

    return ResponseEntity.ok().build();
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
