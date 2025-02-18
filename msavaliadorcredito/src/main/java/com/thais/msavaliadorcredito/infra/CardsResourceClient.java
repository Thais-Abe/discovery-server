package com.thais.msavaliadorcredito.infra;

import com.thais.msavaliadorcredito.domain.dto.CardDTO;
import com.thais.msavaliadorcredito.domain.models.ClientCard;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(value = "mscartoes", path = "/cartoes")
public interface CardsResourceClient {
    @GetMapping(params = "cpf")
    ResponseEntity<List<ClientCard>> getCardsByClient(
            @RequestParam("cpf")String cpf);

    @GetMapping(params= "income")
    ResponseEntity<List<CardDTO>> getCardsIncomeUntil(@RequestParam("income") Long income);
}
