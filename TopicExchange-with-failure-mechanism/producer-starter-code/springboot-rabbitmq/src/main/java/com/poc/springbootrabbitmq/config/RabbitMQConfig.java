package com.poc.springbootrabbitmq.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.poc.springbootrabbitmq.model.Employee;

@Configuration
public class RabbitMQConfig 
{
	
	@Value("${exchangeName}")
	String exchange;

	@Value("${queueName}")
	String queueName;

	@Value("${queueName1}")
	String queueName1;
	
	@Value("${deadletterQueueName}")
	String deadLetterqueueName;

	@Value("${deadletterRoutingKey}")
	private String deadLetterroutingkey;

	@Bean
	public List<Declarable> topicBindings() 
	{
		
		Queue topicQueue = QueueBuilder.durable(queueName)
				.withArgument("x-dead-letter-exchange", exchange)
				.withArgument("x-dead-letter-routing-key", deadLetterroutingkey)
				.build(); 
		
		Queue topicQueue1 = QueueBuilder.durable(queueName1)
				.withArgument("x-dead-letter-exchange", exchange)
				.withArgument("x-dead-letter-routing-key", deadLetterroutingkey)
				.build();	

		TopicExchange topicExchange = new TopicExchange(exchange);

		return Arrays.asList(topicQueue, topicQueue1, topicExchange,
				BindingBuilder.bind(topicQueue).to(topicExchange).with("*.important.*"),
				BindingBuilder.bind(topicQueue1).to(topicExchange).with("#.error"));
	}

	@Bean
	public Jackson2JsonMessageConverter jsonMessageConverter() 
	{
		Jackson2JsonMessageConverter jsonConverter = new Jackson2JsonMessageConverter();
		jsonConverter.setClassMapper(classMapper());
		return jsonConverter;
	}

	@Bean
	public DefaultClassMapper classMapper() 
	{
		DefaultClassMapper classMapper = new DefaultClassMapper();
		Map<String, Class<?>> idClassMapping = new HashMap<>();
		idClassMapping.put("String", String.class);
		idClassMapping.put("Employee", Employee.class);
		classMapper.setIdClassMapping(idClassMapping);
		return classMapper;
	}

}