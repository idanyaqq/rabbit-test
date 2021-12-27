package com.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeadListener {
    @RabbitListener(queues = Config.QUEUE_MESSAGES_DLQ)
    public void processFailedMessages(Msg message) {
        log.info("Received failed message: {}", message.toString());
    }
}
