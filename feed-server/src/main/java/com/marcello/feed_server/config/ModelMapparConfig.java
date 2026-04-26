package com.marcello.feed_server.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapparConfig {

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
