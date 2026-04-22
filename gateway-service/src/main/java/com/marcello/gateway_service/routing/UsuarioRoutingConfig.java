package com.marcello.gateway_service.routing;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;

@Configuration
public class UsuarioRoutingConfig {

    private final String SERVER_URI = "http://localhost:8084";

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route("buscar-usuarios", p ->
                        p.path("/usuarios")
                                .uri(SERVER_URI))
                .route("buscar-usuario-id", p ->
                        p.path("/usuarios/{id}")
                                .uri(SERVER_URI))
                .route("buscar-usuario-email", p ->
                        p.path("/usuarios/email")
                                .and()
                                .method(HttpMethod.GET)
                                .and()
                                .query("email", ".*")
                                .uri(SERVER_URI))
                .route("criar-usuario", p ->
                        p.path("/usuarios")
                                .and()
                                .method(HttpMethod.POST)
                                .uri(SERVER_URI))
                .route("atualizar-usuario", p ->
                        p.path("/usuarios/{id}")
                                .and()
                                .method(HttpMethod.PUT)
                                .uri(SERVER_URI))
                .build();
    }
}
