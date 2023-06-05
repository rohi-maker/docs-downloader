package com.docsDownloaderProject.config;

import com.docsDownloaderProject.exception.GoogleDocsDownloaderException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

@Configuration
public class FeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {
        HttpStatus responseStatus = HttpStatus.valueOf(response.status());
        if (responseStatus.is4xxClientError()) {
            throw new GoogleDocsDownloaderException(responseStatus.value(), response.reason());
        } else if (responseStatus.is5xxServerError()) {
            throw new GoogleDocsDownloaderException(responseStatus.value(), response.reason());
        }
        return new Exception("Error occured");
    }
}
