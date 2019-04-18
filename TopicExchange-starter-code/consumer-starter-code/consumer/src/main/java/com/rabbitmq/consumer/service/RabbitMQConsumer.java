package com.rabbitmq.consumer.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.rabbitmq.consumer.model.Employee;

@Component
public class RabbitMQConsumer 
{
	@RabbitListener(queues = "${queueName}")
	public void recievedMessage(Employee employee) {
		System.out.println("Recieved Message From Normal Queue " + employee);
	}
	
	@RabbitListener(queues = "${queueName1}")
	public void recievedMessage1(String employee) {
		System.out.println("Recieved Message From Error Queue " + employee);
	}
	
}