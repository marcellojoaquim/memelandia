package com.marcello.feed_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGER_NAME = "exchange-memes";
    public static final String QUEUE_FEED = "fila-feed-sincronizacao";

    @Bean
    Queue queueFeed() {
        return new Queue(QUEUE_FEED, true);
    }
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(EXCHANGER_NAME);
    }

    @Bean
    public Binding bindingFeed(Queue queueFeed, TopicExchange topicExchange) {
        return BindingBuilder.bind(queueFeed).to(topicExchange).with("meme.rota.#");
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
