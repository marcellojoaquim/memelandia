package com.marcello.gateway_service.routing;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RoutingConfig {

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder
                .routes()
                .route("get-route", p ->
                        p.path("/get")
                                .filters(f -> f.addRequestHeader("Test-gatway", "Funcionando"))
                                .uri("https://httpbin.org")).build();
    }
}
