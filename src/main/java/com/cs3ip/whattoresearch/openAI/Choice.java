package com.cs3ip.whattoresearch.openAI;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Entity class representing a single choice generated by the ChatGPT.
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Choice {

    private int index;
    private Message message;
}
