package com.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Listener {
    @RabbitListener(queues = Config.QUEUE_MESSAGES)
    public void receiveMessage(Message message, @Header(required = false, name = "x-death") List<String> xDeath) throws Exception {
//    try {
        throw new Exception();
//    }catch (Exception e){}
    }


}
