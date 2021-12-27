package com.rabbitmq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicInteger;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("/test")
public class Controller {

    private final RabbitTemplate template;
    private final ObjectMapper mapper;
    private AtomicInteger atomic = new AtomicInteger(0);

    @GetMapping
    public void test() throws JsonProcessingException {
        template
                .convertAndSend(Config.EXCHANGE_MESSAGES,
                        Config.QUEUE_MESSAGES, mapper.writeValueAsBytes(
                        new Msg.MsgBuilder()
                                .id(atomic.incrementAndGet())
                                .desc("Description")
                                .build()));
    }


}
