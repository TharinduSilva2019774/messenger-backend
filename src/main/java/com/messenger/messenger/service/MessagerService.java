package com.messenger.messenger.service;

import com.messenger.messenger.model.Message;
import com.messenger.messenger.payload.SendMessageDto;
import com.messenger.messenger.repository.MessageRepository;
import com.messenger.messenger.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagerService {
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    public String welcome(){
        return "Welcome";
    }

    public List<Message> getAll(){
        return messageRepository.findAll();
    }

    public String sendMassage(SendMessageDto sendMessageDto){
        Message message = new Message();
        message.setMessageBody(sendMessageDto.getMessage());
        message.setUser(userRepository.getReferenceById(sendMessageDto.getUserId()));
        messageRepository.save(message);
        return "Message successfully sent";
    }

}
