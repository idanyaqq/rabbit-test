package com.rabbitmq;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableRabbit
public class Config {

    public static final String QUEUE_MESSAGES = "baeldung-messages-queue";
    public static final String EXCHANGE_MESSAGES = "baeldung-messages-exchange";

    public static final String DLX_EXCHANGE_MESSAGES = QUEUE_MESSAGES + ".dlx";
    public static final String QUEUE_MESSAGES_DLQ = "dead-queue";

    @Bean
    public MessageConverter rabbitMessageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES)
                .withArgument("x-dead-letter-exchange", "")
                .withArgument("x-dead-letter-routing-key", QUEUE_MESSAGES_DLQ)
                .build();
    }

//    @Bean
//    FanoutExchange deadLetterExchange() {
//        return new FanoutExchange(DLX_EXCHANGE_MESSAGES);
//    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_MESSAGES_DLQ).build();
    }
//
//    @Bean
//    Binding deadLetterBinding() {
//        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange());
//    }
    

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange(EXCHANGE_MESSAGES);
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(QUEUE_MESSAGES);
    }
}
