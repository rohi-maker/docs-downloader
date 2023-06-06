package com.docsDownloaderProject.exception;

import lombok.Getter;

@Getter
public class GoogleDocsDownloaderException extends RuntimeException {
    private int statusCode;
    private String message;

    public GoogleDocsDownloaderException(int statusCode, String message) {
        super(message);
        this.statusCode = statusCode;
        this.message = message;
    }
}
