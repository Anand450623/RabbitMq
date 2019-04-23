package com.rabbitmq.consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rabbitmq.consumer.model.Employee;

@Component
@RabbitListener(queues = "#{'${combinedQueue}'.split(';')}")
public class RabbitMqListener 
{
	@RabbitHandler
	public void recievedMessage(@Header(AmqpHeaders.CONSUMER_QUEUE) String rk,@Payload Employee employee) 
	{
		System.out.println("*********************************************************************************************");
		System.out.println("Header recieved  = "+rk);
		System.out.println("Recieved Object Message From Important Queue " + employee);
		System.out.println("*********************************************************************************************");
	}
	
	@RabbitHandler
	public void recievedMessage1(@Header(AmqpHeaders.CONSUMER_QUEUE) String rk,@Payload String string) 
	{
		System.out.println("*********************************************************************************************");
		System.out.println("Header recieved  = "+rk);
		System.out.println("Recieved String Message From Important Queue " + string);
		System.out.println("*********************************************************************************************");
	}

}
