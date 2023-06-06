package com.docsDownloaderProject.exception;

import com.docsDownloaderProject.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GoogleDocsExceptionHandler {
    @ExceptionHandler(GoogleDocsDownloaderException.class)
    public ResponseEntity<ErrorResponse> handleGoogleDocsNotFoundException(GoogleDocsDownloaderException e) {
        return ResponseEntity.status(e.getStatusCode()).body(new ErrorResponse(e.getStatusCode(), e.getMessage()));
    }

}
