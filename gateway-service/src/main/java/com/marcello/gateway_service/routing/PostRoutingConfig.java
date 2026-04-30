package com.marcello.gateway_service.routing;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class PostRoutingConfig {

    private final String SERVER_URI = "http://localhost:8083";

    @Bean
    public RouteLocator postRoutesLocator(RouteLocatorBuilder routeLocatorBuilder){
        return routeLocatorBuilder
                .routes()
                .route("Criar-post", p ->
                        p.path("/posts")
                                .and()
                                .method(HttpMethod.POST)
                                .uri(SERVER_URI))
                .build();
    }
}
