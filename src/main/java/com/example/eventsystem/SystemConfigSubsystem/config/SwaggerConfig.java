package com.example.eventsystem.SystemConfigSubsystem.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setDescription("Server URL in Development environment");



        Info info = new Info()
                .title("Event Management and Ticketing System API")
                .version("1.0")
                .description("This API exposes endpoints to Event Management and Ticketing System API.");

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}