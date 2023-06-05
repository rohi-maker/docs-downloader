package com.docsDownloaderProject.exception;

import com.docsDownloaderProject.model.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GoogleDocsExceptionHandler {
    @ExceptionHandler(GoogleDocsDownloaderException.class)
    public ErrorResponse handleGoogleDocsNotFoundException(GoogleDocsDownloaderException e) {
        return new ErrorResponse(401, e.getMessage());
    }

}
