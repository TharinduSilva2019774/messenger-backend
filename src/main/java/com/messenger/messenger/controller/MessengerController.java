package com.messenger.messenger.controller;

import com.messenger.messenger.model.Message;
import com.messenger.messenger.payload.SendMessageDto;
import com.messenger.messenger.service.MessagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class MessengerController {

    @Autowired
    private MessagerService messagerService;

    @GetMapping
    public String welcome() {
        return messagerService.welcome();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/getAll")
    public List<Message> getAll() {
        return messagerService.getAll();
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/send")
    public String sendMassage(@RequestBody SendMessageDto sendMessageDto) {
        return messagerService.sendMassage(sendMessageDto);
    }

}
