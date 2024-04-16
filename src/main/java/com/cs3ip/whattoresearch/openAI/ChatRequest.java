package com.cs3ip.whattoresearch.openAI;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a request to the ChatGPT API.
 */
@Getter
@Setter
public class ChatRequest {
    private String model;
    private List<Message> messages=new ArrayList<>();

    /**
     * Constructs a new ChatRequest object with the model and query.
     * @param model The model used for the request.
     * @param query The query string to ask the ChatGPT.
     */
    public ChatRequest(String model, String query) {
        this.model = model;
        this.messages.add(new Message("user", query));
    }
}
