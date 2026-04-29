package com.marcello.post_server.producer;

import com.marcello.post_server.config.RabbitMQConfig;
import com.marcello.post_server.domain.dto.MemeDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MemeProducer {

    private final RabbitTemplate rabbitTemplate;

    public MemeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMeme(MemeDTO memeDTO) {
        log.info("Enviando o Meme");
        String routingKey = "meme.rota.criado";

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOME_EXCHANGE,
                routingKey,
                memeDTO
        );
    }
}
