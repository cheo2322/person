package com.devsu.person.repository;

import com.devsu.person.entity.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

  Optional<Client> findByClientId(String clientId);

  Optional<Client> findByPersonIdentification(String personIdentification);
}
