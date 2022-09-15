package com.visa.vttp.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class CORSConfig implements WebMvcConfigurer {

    private String path;
    private String origin;

    CORSConfig(String path, String origin) {
        this.path = path;
        this.origin = origin;
    }

    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping(path).allowedOrigins(origin);
    }

}
