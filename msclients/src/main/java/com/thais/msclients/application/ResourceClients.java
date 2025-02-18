package com.thais.msclients.application;

import com.thais.msclients.application.representation.ClientSaveRequest;
import com.thais.msclients.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("clientes")
@RequiredArgsConstructor
@Slf4j
public class ResourceClients {

    private final ClientService clientService;



    @GetMapping
    public String status(){
        log.info("Obtendo o status do microsservice de clientes");
        return "ok";
    }
    @PostMapping
    public ResponseEntity<Void> save(@RequestBody ClientSaveRequest request) {
        Client client = request.toModel();
        clientService.save(client);

        URI headerLocation = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{cpf}") // Adiciona o CPF ao caminho da URL
                .buildAndExpand(client.getCpf())
                .toUri();

        return ResponseEntity.created(headerLocation).build();
    }

    @GetMapping(params = "cpf")
    public ResponseEntity clientData(@RequestParam("cpf") String cpf){
        Optional<Client> client = clientService.getByCPF(cpf);
        if(client.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return  ResponseEntity.ok(client);
    }
}
