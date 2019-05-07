package com.poc.springbootrabbitmq.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
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

	@Bean
	public List<Object> topicBindings() 
	{
		
		Queue headerQueue = new Queue(queueName);
		Queue headerQueue1 = new Queue(queueName1);

		Map<String,Object> mapForQueue = new HashMap<>();
		mapForQueue.put("key", "value");
		mapForQueue.put("key2", "value2");
		
		Map<String,Object> mapForQueue1 = new HashMap<>();
		mapForQueue.put("key", "value");
		mapForQueue1.put("key1", "value1");
		
		HeadersExchange headerExchange = new HeadersExchange(exchange);

		return Arrays.asList
				(
						headerQueue,headerQueue1,headerExchange,
						BindingBuilder.bind(headerQueue).to(headerExchange).whereAny(mapForQueue),
						BindingBuilder.bind(headerQueue1).to(headerExchange).whereAll(mapForQueue1)
	    		);
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