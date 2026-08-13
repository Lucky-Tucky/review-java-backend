package com.service.kafka.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;

@Configuration
public class KafkaConsumer {
    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @KafkaListener(
            topics = "email-topic",
            groupId = "email-service"
            )
    public void consume(String message, @Header(KafkaHeaders.RECEIVED_TOPIC) String topic){
        log.info(message+" For Topic->"+topic);
    }


}
