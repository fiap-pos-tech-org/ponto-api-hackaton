package br.com.fiap.hackaton.clockregistryapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${hackaton.openapi.dev-url}")
    private String url;

    @Bean
    public OpenAPI openAPI() {
        var devServer = new Server()
                .url(url)
                .description("URL do ambiente de desenvolvimento");

        var info = new Info()
                .title("API de registro de ponto")
                .version("1.0")
                .description("Esta API expõe endpoints para registro de ponto do Hackaton");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
