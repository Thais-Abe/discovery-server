package com.thais.msavaliadorcredito.domain.services;

import com.thais.msavaliadorcredito.domain.dto.*;
import com.thais.msavaliadorcredito.domain.models.ClientCard;
import com.thais.msavaliadorcredito.domain.models.ClientData;
import com.thais.msavaliadorcredito.domain.models.ClientStatus;
import com.thais.msavaliadorcredito.domain.mqueue.RequestEmissionCardSubscriber;
import com.thais.msavaliadorcredito.exceptions.DataClientNotFoundException;
import com.thais.msavaliadorcredito.exceptions.ErrorCominucationMicroservicesException;
import com.thais.msavaliadorcredito.infra.CardsResourceClient;
import com.thais.msavaliadorcredito.infra.ClientResourceClient;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CreditAnalystService {

    private final ClientResourceClient clientResourceClient;
    private final CardsResourceClient cardsResourceClient;
    private final RequestEmissionCardSubscriber requestEmissionCardSubscriber;

    public ClientStatus getStatusClient(String cpf) throws DataClientNotFoundException, ErrorCominucationMicroservicesException {
        //Objetivo: obter dados do cliente - MSCLIENTs
        //obter cartões do cliente - MSCARTOES
        try {
            ResponseEntity<ClientData> clientDataResponse = clientResourceClient.clientData(cpf);
            ResponseEntity<List<ClientCard>> cardsResponse = cardsResourceClient.getCardsByClient(cpf);
            return ClientStatus
                    .builder()
                    .client(clientDataResponse.getBody())
                    .cards(cardsResponse.getBody())
                    .build();
        }catch (FeignException.FeignClientException e){
            int status = e.status();
            if(HttpStatus.NOT_FOUND.value() == status){
                throw new DataClientNotFoundException();
            }
            throw  new ErrorCominucationMicroservicesException(e.getMessage(),status);
        }
    }

    public ReturnEvaluationClientDTO makeEvaluation(String cpf, Long income) throws DataClientNotFoundException, ErrorCominucationMicroservicesException{
       try {
           ResponseEntity<ClientData> clientDataResponse = clientResourceClient.clientData(cpf);
           ResponseEntity<List<CardDTO>> listCardsResponse = cardsResourceClient.getCardsIncomeUntil(income);

           List<CardDTO> listCards = listCardsResponse.getBody();

           var listApprovedCards = listCards.stream().map(card -> {
               ClientData clientData = clientDataResponse.getBody();

               BigDecimal basicLimit = card.getBasicLimit();
               BigDecimal ageBD = BigDecimal.valueOf(clientData.getAge());
               var fator = ageBD.divide(BigDecimal.valueOf(10));
               BigDecimal approvedLimit = fator.multiply(basicLimit);

               CardApprovedDTO approved = new CardApprovedDTO();
               approved.setCard(card.getName());
               approved.setFlag(card.getFlag());
               approved.setAvailableLimit(approvedLimit);

               return  approved;

           }).collect(Collectors.toList());

           return new ReturnEvaluationClientDTO(listApprovedCards);

       }catch (FeignException.FeignClientException e){
           int status = e.status();
           if(HttpStatus.NOT_FOUND.value() == status){
               throw new DataClientNotFoundException();
           }
           throw  new ErrorCominucationMicroservicesException(e.getMessage(),status);
       }
    }

    public ProtocolRequestCardDTO requestEmissionCard(DataRequestEmissionCardDTO data){
        try{
            requestEmissionCardSubscriber.requestCard(data);
            var protocol = UUID.randomUUID().toString();
            return new ProtocolRequestCardDTO(protocol);
        }catch (Exception e){

        }
    }
}
