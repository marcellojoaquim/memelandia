package com.marcello.feed_server.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGER_NAME = "exchange-memes";
    public static final String QUEUE_FEED = "memes";

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
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        DefaultClassMapper classMapper = new DefaultClassMapper();
        classMapper.setTrustedPackages("*");

        classMapper.setIdClassMapping(Map.of(
                "com.marcello.post_server.domain.dto.MemeDTO",
                com.marcello.feed_server.domain.dto.MemeDTO.class
        ));

        converter.setClassMapper(classMapper);
        return converter;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(
            ConnectionFactory connectionFactory,
            Jackson2JsonMessageConverter messageConverter) {

        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(messageConverter);
        return factory;
    }

}
