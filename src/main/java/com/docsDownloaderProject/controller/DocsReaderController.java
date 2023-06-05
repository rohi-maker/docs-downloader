package com.docsDownloaderProject.controller;

import com.docsDownloaderProject.exception.GoogleDocsDownloaderException;
import com.docsDownloaderProject.model.GoogleDoc;
import com.docsDownloaderProject.service.GoogleDocsApiService;
import com.docsDownloaderProject.service.SaveGoogleDocService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
public class DocsReaderController {

    @Autowired
    private GoogleDocsApiService googleDocService;

    @Autowired
    private SaveGoogleDocService docWriterService;

    @GetMapping("/readdocs")
    public ResponseEntity<String> readDocs() {
        List<String> urlList = Arrays.asList("https://docs.google.com/document/d/1Kr__ADJPhpMe--8JYFe27Wj7LT2Lz_nY8Bbs6-OeWhg/edit", "https://docs.google.com/document/d/1U8dxGpXFI6_2-ao4gWtfRcmPnfl8xdGSnhdkmDmaUyY/edit");
        List<String> docIdList = urlList.stream().map(url -> url.substring(35, 79)).collect(Collectors.toList());
        List<GoogleDoc> googleDocResponses = new ArrayList<>();
        try {
            for (String docId : docIdList) {
                var googleDocResponse = googleDocService.getGoogleDoc(docId);
                googleDocResponses.add(googleDocResponse);
            }
        } catch (JsonProcessingException e) {
            throw new GoogleDocsDownloaderException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (RuntimeException e) {
            throw new GoogleDocsDownloaderException(HttpStatus.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        }
        googleDocResponses.forEach(googleDoc -> docWriterService.writeGoogleDocObject(googleDoc));
        return ResponseEntity.ok("SUCCESSFULLY SAVED ALL THE GOOGLE DOCS");
    }

}
