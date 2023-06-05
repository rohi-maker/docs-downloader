package com.docsDownloaderProject.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class InterceptorConfiguration implements RequestInterceptor {
    private final String token;

    public InterceptorConfiguration(String token) {
        this.token = token;
    }

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("Authorization", "Bearer " + token);
        requestTemplate.header("Accept", "*/*");
        requestTemplate.header("Accept-Encoding", "gzip", "deflate", "br");
    }
}
