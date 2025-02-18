package com.thais.mscartoes.application.services;

import com.thais.mscartoes.domain.models.ClientCard;
import com.thais.mscartoes.domain.repositories.ClientCardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientCardService {

    private final ClientCardRepository repository;

    public List<ClientCard> listCardsByCpf(String cpf){
        return repository.findByCpf(cpf);
    }
}
