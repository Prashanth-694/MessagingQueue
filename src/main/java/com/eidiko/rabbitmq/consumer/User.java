package com.eidiko.rabbitmq.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.eidiko.rabbitmq.config.MessageConfig;
import com.eidiko.rabbitmq.dto.OrderStatus;

@Component
public class User {
	
	
    @RabbitListener(queues = MessageConfig.QUEUE)
	public void consumeMessageFromQueue(OrderStatus orderStatus) {
	System.out.println("Message From Queue :"+orderStatus);
	}
}
