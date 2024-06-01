package com.luv2code.chlenix.chlenixProject.chat;

import lombok.*;

/**
 * class with properties
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChatMessage {
    private String content;
    private String sender;
    private MessageType type;
}