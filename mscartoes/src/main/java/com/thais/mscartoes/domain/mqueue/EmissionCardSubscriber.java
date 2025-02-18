package com.thais.mscartoes.domain.mqueue;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EmissionCardSubscriber {
    @RabbitListener(queues = "${mq.queue.emissao-cartoes}") // nome da fila
    public void receiveIssuanceRequest(@Payload String payload){
        System.out.println(payload);
    }
}
