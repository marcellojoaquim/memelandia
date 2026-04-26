package com.marcello.feed_server.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI customOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Microsservico Feed")
                        .description("Microsservico destinado a gerenciamento de Feed na plataforma.")
                        .contact(new Contact()
                                .name("Marcello J Silva")
                                .email("marcellojoaquim1@hotmail.com")
                                .url("https://github.com/marcellojoaquim"))
                        .termsOfService("")
                        .license(new License()
                                .name("Apache 2.0")));
    }
}
