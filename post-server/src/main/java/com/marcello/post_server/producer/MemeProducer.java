package com.marcello.post_server.producer;

import com.marcello.post_server.config.RabbitMQConfig;
import com.marcello.post_server.domain.dto.MemeDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
public class MemeProducer {

    private final RabbitTemplate rabbitTemplate;

    public MemeProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void enviarMeme(MemeDTO memeDTO) {
        String routingKey = "meme.rota.criado";

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOME_EXCHANGE,
                routingKey,
                memeDTO
        );
    }
}
