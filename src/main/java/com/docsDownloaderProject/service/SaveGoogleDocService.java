package com.docsDownloaderProject.service;

import com.docsDownloaderProject.exception.GoogleDocsDownloaderException;
import com.docsDownloaderProject.model.GoogleDoc;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;

@Service
public class SaveGoogleDocService {
    public void writeGoogleDocObject(GoogleDoc googleDoc) {
        String title = googleDoc.getTitle();
        String content = googleDoc.getContent();
        try {
            File file = new File("/home/java/Downloads/docs-downloader-project/src/main/resources/" + title + ".docx");
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write((title + "\n" + content).getBytes(Charset.defaultCharset()));
            outputStream.close();
        } catch (IOException e) {
            throw new GoogleDocsDownloaderException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (RuntimeException e) {
            throw new GoogleDocsDownloaderException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }
}
