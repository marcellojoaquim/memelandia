package com.marcello.gateway_service.routing;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeedRoutingConfig {

    private final String SERVER_URI = "http://localhost:8082";

    @Bean
    public RouteLocator feedRoutesLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route("buscar-feed", p ->
                        p.path("/feed")
                                .uri(SERVER_URI))
                .route("buscar-categorias", p ->
                        p.path("/feed/categorias")
                                .and()
                                .uri(SERVER_URI))
                .route("buscar-memes-por-categoria", p ->
                        p.path("/feed/categoria/{categoria}")
                                .and()
                                .uri(SERVER_URI))
                .route("buscar-memes-por-usuario", p ->
                        p.path("/feed/usuario/{email}")
                                .and()
                                .uri(SERVER_URI))
                .route("buscar-meme-do-dia", p ->
                        p.path("/feed/meme-do-dia")
                                .and()
                                .uri(SERVER_URI))
                .build();
    }
}
