package com.chat.controller;

import com.chat.dto.ChatBotMessage;
import com.chat.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    @MessageMapping("/chatbot")
    @SendTo("/chatroom/messages")
    public ChatBotMessage send(@Payload ChatBotMessage message){
        System.out.println(message.toString());
        ChatBotMessage responseMessage = new ChatBotMessage();
        responseMessage.setSender("chat-bot");
        responseMessage.setMessage(generateResponseMessage(message));
        return responseMessage;
    }

    @MessageMapping("/private-chat")
    public ChatBotMessage privateChatMesssage(@Payload ChatBotMessage message){
        ChatBotMessage responseMessage = new ChatBotMessage();
        responseMessage.setSender("chat-bot");
        responseMessage.setMessage(generateResponseMessage(message));


        simpMessagingTemplate.convertAndSendToUser(message.getSender(), "/private", responseMessage);

        return responseMessage;
    }


    private String generateResponseMessage(ChatBotMessage receivedMessage){
        return "Hi, " + receivedMessage.getSender() + ". Sorry, i do not understand what you mean by '" + receivedMessage.getMessage() +  "'.";
    }
}
