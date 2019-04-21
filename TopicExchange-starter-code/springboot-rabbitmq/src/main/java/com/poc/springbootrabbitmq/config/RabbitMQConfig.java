package com.poc.springbootrabbitmq.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarable;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
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

	@Bean
	public List<Declarable> topicBindings() 
	{
		Queue topicQueue = new Queue(queueName, false);
		Queue topicQueue1 = new Queue(queueName1, false);

		TopicExchange topicExchange = new TopicExchange(exchange);

		return Arrays.asList(topicQueue, topicQueue1, topicExchange,
				BindingBuilder.bind(topicQueue).to(topicExchange).with("*.important.*"),
				BindingBuilder.bind(topicQueue1).to(topicExchange).with("#.error"));
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}

}