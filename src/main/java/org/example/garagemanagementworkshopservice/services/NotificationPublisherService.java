package org.example.garagemanagementworkshopservice.services;

import org.example.garagemanagementworkshopservice.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationPublisherService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendNotification(String notif) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.WORKSHOP_EXCHANGE, RabbitMQConfig.ROUTING_KEY, notif);
        System.out.println("Notification published to RabbitMQ: " + notif);
    }
}


