package com.thais.msavaliadorcredito.infra;

import com.thais.msavaliadorcredito.domain.models.ClientData;
import com.thais.msavaliadorcredito.domain.models.ClientStatus;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "msclientes", path = "/clientes")
public interface ClientResourceClient {

    @GetMapping(params = "cpf")
    public ResponseEntity<ClientData> clientData(@RequestParam("cpf") String cpf);

}
