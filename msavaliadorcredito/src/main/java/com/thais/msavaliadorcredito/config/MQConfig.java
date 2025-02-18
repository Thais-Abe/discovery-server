package com.thais.msavaliadorcredito.config;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MQConfig {
    @Value("${mq.queue.emissao-cartoes}")
    private String emissionCardsQueue;
    public Queue queueEmissionCards(){
        return new Queue(emissionCardsQueue, true);
    }
}
