package com.docsDownloaderProject.controller;

import com.docsDownloaderProject.model.GoogleDoc;
import com.docsDownloaderProject.service.GoogleDocsApiService;
import com.docsDownloaderProject.service.SaveGoogleDocService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
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
//            GoogleDoc fetchedDoc = service.getGoogleDoc("1Kr__ADJPhpMe--8JYFe27Wj7LT2Lz_nY8Bbs6-OeWhg");
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        } catch (RuntimeException e) {
            System.out.println("SOME ERROR TAKES PLACE");
        }
        googleDocResponses.forEach(googleDoc -> docWriterService.writeGoogleDocObject(googleDoc));
        return ResponseEntity.ok("SUCCESSFULLY SAVED ALL THE GOOGLE DOCS");
    }

}
