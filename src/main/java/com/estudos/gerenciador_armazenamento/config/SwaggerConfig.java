package com.estudos.gerenciador_armazenamento.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Gerenciador de Armazenamento API")
                        .description("API REST para gerenciamento de produtos e movimentações de armazenamento")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Suporte")
                                .url("https://exemplo.com")
                                .email("suporte@exemplo.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")));
    }
}
