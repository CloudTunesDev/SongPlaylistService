package com.cloudtunes.songplaylistserv.rabbitmq;

import com.cloudtunes.songplaylistserv.user.UserDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class UserMessageListener {

    @RabbitListener(queues = "userQueue")
    public void receiveMessage(UserDTO userDTO) {
        // Process the received user data
        System.out.println("Received user data: " + userDTO);
    }

    @RabbitListener(queues = "userQueue")
    public void receiveMessage(String message) {
        // Process the received message
        System.out.println("Received message: " + message);
    }
}