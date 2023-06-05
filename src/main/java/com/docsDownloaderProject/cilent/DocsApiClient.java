package com.docsDownloaderProject.cilent;

import com.google.api.services.docs.v1.model.Body;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "docs-api", url = "https://docs.googleapis.com/v1")
public interface DocsApiClient {

    @RequestMapping(method = RequestMethod.GET, value = "/documents/{documentId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Body> getGoogleDocs(@PathVariable("documentId") String documentId);
}
