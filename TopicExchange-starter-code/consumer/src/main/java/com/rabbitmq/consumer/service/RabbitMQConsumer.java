package com.rabbitmq.consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.consumer.model.Employee;

@Component
@RabbitListener(queues = "${queueName1}")
public class RabbitMQConsumer 
{
	@RabbitHandler
	public void recievedMessage(Employee employee) {
		System.out.println("Recieved Message From Queue " + employee);
	}
	
	@RabbitHandler
	public void recievedMessage1(String string) {
		System.out.println("Recieved Message From Queue " + string);
	}
		
}