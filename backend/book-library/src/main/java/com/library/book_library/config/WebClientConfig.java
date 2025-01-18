package com.library.book_library.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("https://gutendex.com") // Configuración base
                .defaultHeader("Accept", "application/json") // Headers comunes
                .build();
    }
}

