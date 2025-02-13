package com.thais.msclients.application;

import com.thais.msclients.domain.Client;
import com.thais.msclients.infra.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository repository;


    @Transactional
    public Client save(Client client){
        return repository.save(client);
    }


    public Optional<Client> getByCPF(String cpf){
        return repository.findByCpf(cpf);
    }
}
