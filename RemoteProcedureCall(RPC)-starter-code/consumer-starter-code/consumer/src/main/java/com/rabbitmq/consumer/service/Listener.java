package com.rabbitmq.consumer.service;

import java.io.IOException;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;

@Component
public class Listener 
{
	@RabbitListener(queues="${queue}",containerFactory="prefetchTenRabbitListenerContainerFactory")
	public Integer firstConsumer(@Header(AmqpHeaders.CONSUMER_QUEUE) String rk, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
			Channel channel, @Payload Integer value) throws InterruptedException, IOException  
	{
		System.out.println("value recieved from queue = "+value);
		channel.basicAck(deliveryTag, false);
		return this.increment(value);
	}

	private Integer increment(Integer value) 
	{
		return (value+1);
	}
	
}
