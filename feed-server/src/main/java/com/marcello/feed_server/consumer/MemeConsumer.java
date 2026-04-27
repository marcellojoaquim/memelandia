package com.marcello.feed_server.consumer;

import com.marcello.feed_server.domain.dto.MemeDTO;
import com.marcello.feed_server.integration.IMemeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;


@Component
public class MemeConsumer {

    private final IMemeService memeClient;
    private static final Logger log = LoggerFactory.getLogger(MemeConsumer.class);

    public MemeConsumer(IMemeService memeClient) {
        this.memeClient = memeClient;
    }

    @RabbitListener(queues = "${amqp.queue.memes}")
    public void receiveMessage(@Payload MemeDTO memeDTO) {
        log.info("Meme recebido da fila para sincronização: {}", memeDTO.getNome());

        try {
            memeClient.salvarMeme(memeDTO);
            log.info("Meme sincronizado com sucesso no MS-Meme (Postgres).");
        } catch (Exception e) {
            log.error("Falha ao sincronizar meme com MS-Meme: {}", e.getMessage());
        }
    }
}
