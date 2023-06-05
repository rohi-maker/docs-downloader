package com.docsDownloaderProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DocsDownloaderProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocsDownloaderProjectApplication.class, args);
    }

}
