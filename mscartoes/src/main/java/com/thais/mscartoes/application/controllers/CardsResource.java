package com.thais.mscartoes.application.controllers;

import com.thais.mscartoes.application.services.CardService;
import com.thais.mscartoes.application.services.ClientCardService;
import com.thais.mscartoes.domain.models.Card;
import com.thais.mscartoes.domain.models.ClientCard;
import com.thais.mscartoes.domain.models.dtos.CardSaveRequestDTO;
import com.thais.mscartoes.domain.models.dtos.CardsByClientResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cartoes")
@RequiredArgsConstructor
public class CardsResource {

    private final CardService cardService;
    private final ClientCardService clientCardService;
    @GetMapping
    public String status(){
        return "Ok";
    }
    @PostMapping
    public ResponseEntity register(@RequestBody CardSaveRequestDTO request){
        Card card = request.cardDTOToCard();
        cardService.save(card);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping(params = "income")
    public ResponseEntity<List<Card>> getCardsIncomeUntil(@RequestParam("income") Long income){
      return ResponseEntity.ok(cardService.getCardsThatIncomeLessOrEqual(income));
    }


    @GetMapping(params = "cpf")
    public ResponseEntity<List<CardsByClientResponseDTO>> getCardsByClient(@RequestParam("cpf")String cpf){
      List<ClientCard>listCard =   clientCardService.listCardsByCpf(cpf);
      List<CardsByClientResponseDTO> resultList = listCard.stream().map(CardsByClientResponseDTO::clientCardToCardsByClientResponseDTO)
              .collect(Collectors.toList());
      return ResponseEntity.ok(resultList);
    }
}
