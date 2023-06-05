package com.docsDownloaderProject.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GoogleDoc {
    private String title;
    private String content;
}
