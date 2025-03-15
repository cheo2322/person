package com.devsu.person.service;

import com.devsu.person.entity.dto.ClientDto;
import com.devsu.person.entity.dto.ClientRecord;
import com.devsu.person.handler.exception.DuplicateIdentificationException;

import java.util.List;

public interface ClientService {

    /**
     * Create a client. If the identification is already present for one client or person,
     * throw a {@link DuplicateIdentificationException}
     * @param client with data to be created.
     */
    void createClient(ClientRecord client);

    /**
     * Get a list of all clients.
     * @return all clients.
     */
    List<ClientDto> getClients();

    /**
     * Get client details based on the id.
     * @param clientId from client to get.
     * @return A {@ClientDto} with information from the client.
     */
    ClientDto getClient(String clientId);

    /**
     * Update client details.
     * @param client contains the details to be updated.
     * @return A {@ClientDto}.
     */
    ClientDto updateClient(ClientRecord client);

    /**
     * Delete a client from DB.
     * @param clientId from client to be removed.
     * @return A {@ClientDto}.
     */
    ClientDto deleteClient(String clientId);
}
