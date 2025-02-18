package com.thais.msavaliadorcredito.domain.mqueue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.thais.msavaliadorcredito.domain.dto.DataRequestEmissionCardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RequestEmissionCardSubscriber {

    private final RabbitTemplate rabbitTemplate;
    private final Queue queueEmissionCards;

    public void requestCard(DataRequestEmissionCardDTO data) throws JsonProcessingException {
        var json = convertIntoJson(data);
        rabbitTemplate.convertAndSend(queueEmissionCards.getName(), json);
    }
    private String convertIntoJson(DataRequestEmissionCardDTO data) throws JsonProcessingException {
        ObjectMapper mapper =  new ObjectMapper();
       var json = mapper.writeValueAsString(data);
       return  json;
    }
}
