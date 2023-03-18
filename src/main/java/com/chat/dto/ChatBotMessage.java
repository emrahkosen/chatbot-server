package com.chat.dto;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChatBotMessage {
    private String sender;
    private String message;

}
