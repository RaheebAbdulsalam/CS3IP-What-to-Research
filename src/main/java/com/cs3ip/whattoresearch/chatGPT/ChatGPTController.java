package com.cs3ip.whattoresearch.chatGPT;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
@RequestMapping("/api/v1")
public class ChatGPTController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    private static RestTemplate restTemplate=new RestTemplate();

    @RequestMapping(value="/ask",method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    public String ask(@RequestParam String query) {
        ChatRequest request = new ChatRequest(model, query);
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        // call the API
        ChatResponse chatResponse = restTemplate.postForObject(apiUrl, new HttpEntity<>(request,headers), ChatResponse.class);


        // return the first response
        return chatResponse.getChoices().get(0).getMessage().getContent();
    }
}
