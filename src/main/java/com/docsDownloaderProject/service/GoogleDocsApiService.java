package com.docsDownloaderProject.service;

import com.docsDownloaderProject.cilent.DocsApiClient;
import com.docsDownloaderProject.model.GoogleDoc;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleDocsApiService {
    @Autowired
    private DocsApiClient apiClient;

    public GoogleDoc getGoogleDoc(String docId) throws JsonProcessingException {
        var googleDocsResponse = apiClient.getGoogleDocs(docId);
        String title = googleDocsResponse.getBody().get("title").toString();
        JSONObject jsonObject = new JSONObject(new ObjectMapper().writeValueAsString(googleDocsResponse.getBody()));
        JSONObject secondElement = ((JSONArray) jsonObject.getJSONObject("body").get("content")).getJSONObject(1);
        JSONObject finalObject = (JSONObject) secondElement.getJSONObject("paragraph").getJSONArray("elements").get(0);
        String documentContent = finalObject.getJSONObject("textRun").get("content").toString();
        return new GoogleDoc(title,documentContent);
    }
}
