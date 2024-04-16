package com.cs3ip.whattoresearch.openAI;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Represents a response from the ChatGPT API.
 */

@Getter
@Setter
public class ChatResponse {
    private List<Choice> choices;

    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }
}
