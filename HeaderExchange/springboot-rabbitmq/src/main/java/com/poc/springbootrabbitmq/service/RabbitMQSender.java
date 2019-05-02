package com.poc.springbootrabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.utils.SerializationUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.poc.springbootrabbitmq.model.Employee;

@Service
public class RabbitMQSender 
{
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${exchangeName}")
	private String exchange;
	
	public void send(Employee employee) 
	{ 
		Message message = MessageBuilder.withBody(SerializationUtils.serialize(employee))
                .setHeader("key", "value")
                .setHeader("key1", "value1")
                .setHeader("type", "Employee")
                .build();
		
		amqpTemplate.convertAndSend(exchange,"",message);
	}
	
}