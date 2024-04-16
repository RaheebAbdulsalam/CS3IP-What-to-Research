package com.cs3ip.whattoresearch.openAI;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Entity class representing a prompts messages .
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Message {
    private String role;
    private String content;

}
