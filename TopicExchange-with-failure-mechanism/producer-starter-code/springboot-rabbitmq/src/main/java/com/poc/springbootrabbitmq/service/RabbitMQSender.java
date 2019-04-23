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
	
	public void send(Employee employee) 
	{
		amqpTemplate.convertAndSend(exchange,"some.important.error", employee);		// sends data to both error and important queue
		amqpTemplate.convertAndSend(exchange,"some.important.error", 1);		// sends error prone data
		amqpTemplate.convertAndSend(exchange,"nothing.but.error", employee.getEmpName());	// sends data to error queue only
	}
	
}