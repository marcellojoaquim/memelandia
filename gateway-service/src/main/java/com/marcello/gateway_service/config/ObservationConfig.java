package com.marcello.gateway_service.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Hooks;

@Configuration
public class ObservationConfig {
    @PostConstruct
    void setup() {
        Hooks.enableAutomaticContextPropagation();
    }
}