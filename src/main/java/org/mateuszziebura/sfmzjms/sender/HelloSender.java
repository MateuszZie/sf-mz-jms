package org.mateuszziebura.sfmzjms.sender;


import lombok.RequiredArgsConstructor;
import org.mateuszziebura.sfmzjms.config.JMSConfig;
import org.mateuszziebura.sfmzjms.model.HelloWorldMessage;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class HelloSender {

    private final JmsTemplate jmsTemplate;

    @Scheduled(fixedRate = 2000)
    public void sendMessage(){
        System.out.println("i'm sending a message");

        HelloWorldMessage helloWorldMessage = HelloWorldMessage
                .builder()
                .id(UUID.randomUUID())
                .message("Hello World")
                .build();
        jmsTemplate.convertAndSend(JMSConfig.MY_QUEUE, helloWorldMessage);

        System.out.println("Message send!");
    }
}
