package com.stackly.recharge.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI rechargeOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Mobile Recharge API")
                        .description("REST API for recharging mobile numbers across operators")
                        .version("1.0.0")
                        .contact(new Contact().name("Stackly").email("support@stackly.com")));
    }
}
