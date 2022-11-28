package com.capstone.notechigima.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@SecurityScheme(
        name = "Bearer Authentication",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class Swagger2Config {

    @Bean
    public GroupedOpenApi publicApi() {
        return GroupedOpenApi.builder()
                .group("v1-definition")
                .pathsToMatch("/api/**")
                .build();
    }

    @Bean
    public OpenAPI springOpenAPI() {

        return new OpenAPI()
                .info(new Info().title("Notechigima API")
                        .description("Notechigima API 명세서입니다.")
                        .version("v1.1.2"));
    }
}
