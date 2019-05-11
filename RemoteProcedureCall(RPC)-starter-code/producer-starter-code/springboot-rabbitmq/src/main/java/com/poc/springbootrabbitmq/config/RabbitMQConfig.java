package com.poc.springbootrabbitmq.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
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

	@Value("${queueName}")
	String queueName;

	@Value("${exchangeName}")
	String exchange;

	@Value("${routingkey}")
	private String routingkey;

	@Bean
	Queue queue() {
		return new Queue(queueName, true);
	}

	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}

	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
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
		idClassMapping.put("Integer", Integer.class);
		idClassMapping.put("Employee", Employee.class);
		classMapper.setIdClassMapping(idClassMapping);
		return classMapper;
	}
	
}
