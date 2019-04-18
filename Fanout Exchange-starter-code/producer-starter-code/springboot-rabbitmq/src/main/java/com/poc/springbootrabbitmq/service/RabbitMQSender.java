package com.poc.springbootrabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
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
	
	public void send(Employee company) 
	{
		amqpTemplate.convertAndSend(exchange, "", company);
		System.out.println("Send msg = " + company);	    
	}
	
}