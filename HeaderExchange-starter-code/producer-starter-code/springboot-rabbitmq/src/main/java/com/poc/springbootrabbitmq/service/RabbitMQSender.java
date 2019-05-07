package com.poc.springbootrabbitmq.service;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
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
		
		amqpTemplate.convertAndSend(exchange, "", employee, new MessagePostProcessor() 
		{			
			@Override
			public Message postProcessMessage(Message message) throws AmqpException 
			{
				message.getMessageProperties().setHeader("key","value");
				message.getMessageProperties().setHeader("key1", "value1");
				return message;
			}
		});
	}
	
}