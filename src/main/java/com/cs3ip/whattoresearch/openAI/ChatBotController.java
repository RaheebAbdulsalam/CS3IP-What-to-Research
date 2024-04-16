package com.cs3ip.whattoresearch.openAI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller class handling requests related to ChatGPT.
 */
@RestController
@RequestMapping("/api/v1")
public class ChatBotController {
    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Value("${openai.api.key}")
    private String apiKey;

    private static RestTemplate restTemplate=new RestTemplate();

    /**
     * Handles GET requests to the endpoint.
     * @return ModelAndView object representing the chatBot view.
     */
    @GetMapping("/chat-bot")
    public ModelAndView getGpt() {
        ModelAndView mav = new ModelAndView("/chatBot");
        return mav;
    }

    /**
     * Handles GET requests to '/api/v1/ask' endpoint.
     * @param query The query string to ask the ChatGPT.
     * @return String representing the response from the ChatGPT.
     */
    @RequestMapping(value="/ask",method = RequestMethod.GET,produces= MediaType.APPLICATION_JSON_VALUE)
    public String ask(@RequestParam String query) {
        ChatRequest request = new ChatRequest(model, query);
        HttpHeaders headers=new HttpHeaders();
        headers.add("Authorization", "Bearer " + apiKey);
        // Call the API
        ChatResponse chatResponse = restTemplate.postForObject(apiUrl, new HttpEntity<>(request,headers), ChatResponse.class);
        // Return the first response
        return chatResponse.getChoices().get(0).getMessage().getContent();
    }







}
