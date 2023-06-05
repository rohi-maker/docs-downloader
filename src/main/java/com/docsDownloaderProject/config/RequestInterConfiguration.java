package com.docsDownloaderProject.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RequestInterConfiguration {
    @Value("${google.api.request.access.token}")
    private String accessToken;

    @Bean
    public InterceptorConfiguration interceptorConfiguration() {
        return new InterceptorConfiguration(accessToken);
    }
}
