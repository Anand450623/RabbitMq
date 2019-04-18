package com.poc.springbootrabbitmq.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig 
{
	@Value("${exchangeName}")
	String exchange;
	
	@Value("${queueName}")
	String queueName;

	@Value("${queueName1}")
	String queueName1;

	@Value("${queueName2}")
	String queueName2;

	@Bean
	public List<Declarable> fanoutBindings() 
	{
	    Queue fanoutQueue = new Queue(queueName, false);
	    Queue fanoutQueue1 = new Queue(queueName1, false);
	    Queue fanoutQueue2 = new Queue(queueName2, false);
	    
	    FanoutExchange fanoutExchange = new FanoutExchange(exchange);
	 
	    return Arrays.asList(fanoutQueue,fanoutQueue1,fanoutQueue2,fanoutExchange,
	    		BindingBuilder.bind(fanoutQueue).to(fanoutExchange),
	    		BindingBuilder.bind(fanoutQueue1).to(fanoutExchange), 
	    		BindingBuilder.bind(fanoutQueue2).to(fanoutExchange));
	}
	
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
}