package com.api.bazar.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("API Baza")
                        .version("1.0")
                        .description("Documentación de los endoponits de mi API publica")
                        .contact(new Contact()
                                .name("Agustin Minzoni")
                                .email("agustinminzoni@gmail.com")));
    }
}
