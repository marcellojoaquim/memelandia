package com.marcello.feed_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGER_NAME = "exchange-memes";
    public static final String QUEUE_FEED = "meme.rota.#";

    @Bean
    Queue queueFeed() {
        return new Queue(QUEUE_FEED, true);
    }
    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(EXCHANGER_NAME);
    }

    @Bean
    public Binding bindingFeed(Queue queueFeed, FanoutExchange fanoutExchange) {
        return BindingBuilder.bind(queueFeed).to(fanoutExchange);
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return new Jackson2JsonMessageConverter(objectMapper);
    }

}
