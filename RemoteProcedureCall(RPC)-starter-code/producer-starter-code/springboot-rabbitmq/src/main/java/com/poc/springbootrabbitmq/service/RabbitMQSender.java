package com.poc.springbootrabbitmq.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQSender 
{
	@Autowired
	private AmqpTemplate amqpTemplate;
	
	@Value("${exchangeName}")
	private String exchange;
	
	@Value("${routingkey}")
	private String routingkey;
	
	private static int count = 0;
	
	@Scheduled(fixedDelay=1000)
	public void send() 
	{
		System.out.println("/**********************************************************************************************/");
		System.out.println("Value sent to queue  = "+count);
		Integer incrementedValue = (Integer) amqpTemplate.convertSendAndReceive(exchange, routingkey, count);
		System.out.println("recieved inceremnted value = " + incrementedValue);	
		count++;
		System.out.println("/**********************************************************************************************/");
	}
	
}