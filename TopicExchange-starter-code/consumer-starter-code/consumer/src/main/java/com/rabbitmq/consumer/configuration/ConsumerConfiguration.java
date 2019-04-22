package com.rabbitmq.consumer.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.rabbitmq.consumer.model.Employee;

@EnableRabbit
@Configuration
public class ConsumerConfiguration
{
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
