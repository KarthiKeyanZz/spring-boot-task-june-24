package com.stackly.leave.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI leaveOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Employee Leave Management API")
                        .description("REST API for employees to apply for and manage leave requests")
                        .version("1.0.0")
                        .contact(new Contact().name("Stackly").email("support@stackly.com")));
    }
}
