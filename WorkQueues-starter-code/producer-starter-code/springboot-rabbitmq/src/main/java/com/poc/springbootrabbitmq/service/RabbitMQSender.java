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
	
	@Scheduled(fixedDelay=2000)
	public void send() 
	{
		String sampleString = "Hello";
		if(count%2==0)
			sampleString+="";
		else
			sampleString+="...";
		amqpTemplate.convertAndSend(exchange, routingkey, sampleString);
		System.out.println("Send msg = " + sampleString);	
		count++;
	}
	
}