package org.example.garagemanagementworkshopservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String WORKSHOP_EXCHANGE = "workshop-exchange";
    public static final String NOTIFICATION_QUEUE = "notification-queue";
    public static final String ROUTING_KEY = "notification.routingKey";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(WORKSHOP_EXCHANGE);
    }

    @Bean
    public Queue queue() {
        return new Queue(NOTIFICATION_QUEUE);
    }

    @Bean
    public Binding binding(Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }
}

