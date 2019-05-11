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
	public void firstConsumer(@Header(AmqpHeaders.CONSUMER_QUEUE) String rk, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
			Channel channel, @Payload String string) throws InterruptedException, IOException  
	{
		System.out.println("/********************************************************************************************");
		System.out.println("message recieved by first listener");
		System.out.println("message recieved from queue = "+string);
		Thread.sleep(10000);
		channel.basicAck(deliveryTag, false);
		System.out.println("********************************************************************************************/");
	}
	
	@RabbitListener(queues="${queue}",containerFactory="prefetchTenRabbitListenerContainerFactory")
	public void secondConsumer(@Header(AmqpHeaders.CONSUMER_QUEUE) String rk, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag,
			Channel channel, @Payload String string) throws InterruptedException, IOException  
	{
		System.out.println("/********************************************************************************************");
		System.out.println("message recieved by second listener");
		System.out.println("message recieved from queue = "+string);
		Thread.sleep(2000);
		channel.basicAck(deliveryTag, false);
		System.out.println("********************************************************************************************/");
	}
	
}
