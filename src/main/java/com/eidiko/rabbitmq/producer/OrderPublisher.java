package com.eidiko.rabbitmq.producer;

import java.util.UUID;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eidiko.rabbitmq.config.MessageConfig;
import com.eidiko.rabbitmq.dto.Order;
import com.eidiko.rabbitmq.dto.OrderStatus;

@RestController
@RequestMapping("/order")
public class OrderPublisher {

	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@PostMapping("/{restaurantname}")
	public OrderStatus bookOrder(@RequestBody Order order,@PathVariable String restaurantname) {
		order.setOrderid(UUID.randomUUID().toString());
		
		OrderStatus orderStatus=new OrderStatus(order, "Process", "order placed succesfully from "+restaurantname);
		rabbitTemplate.convertAndSend(MessageConfig.TOPIC_EXCHANGE, MessageConfig.ROUTING_kEY, orderStatus);
		return orderStatus;
	}
	
	
}
