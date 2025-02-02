package it.lucacosta.gym.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${openapi.dev-url}")
    private String devUrl;

    public OpenAPI myOpenAPI() {

        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Info info = new Info()
        .title("Tutorial Management API")
        .version("1.0")
        .description("This API exposes endpoints to manage tutorials.").termsOfService("https://www.bezkoder.com/terms");


        return new OpenAPI().info(info).servers(List.of(devServer));
    }

}
