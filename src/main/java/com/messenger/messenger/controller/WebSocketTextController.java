package com.messenger.messenger.controller;

import com.messenger.messenger.model.Message;
import com.messenger.messenger.payload.SendMessageResponseDto;
import com.messenger.messenger.service.MessagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/websocket")
@CrossOrigin
public class WebSocketTextController {

    @Autowired
    SimpMessagingTemplate template;

    @Autowired
    MessagerService messagerService;

    @PostMapping("/send")
    public ResponseEntity<Void> sendMessage(@RequestBody SendMessageResponseDto textMessageDTO) {
        List<Message> updatesMessages = messagerService.getAll();
        template.convertAndSend("/topic/message", updatesMessages);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/sendMessage")
    public void receiveMessage(@Payload SendMessageResponseDto textMessageDTO) {
        // receive message from client
    }

    @SendTo("/topic/message")
    public SendMessageResponseDto broadcastMessage(@Payload SendMessageResponseDto textMessageDTO) {
        return textMessageDTO;
    }
}