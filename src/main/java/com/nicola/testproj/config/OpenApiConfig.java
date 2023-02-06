package com.nicola.testproj.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder().setGroup("users").pathsToMatch("/api/users/**")
                .pathsToExclude("/api/users/error").build();
    }
}
