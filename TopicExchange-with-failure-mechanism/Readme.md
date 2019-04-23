This folder contains sample project code to get started with RabbitMq+Spring_Boot producer and consumer functionality using Topic exchange
with Deadletter queue implementation for failure handling.

The following property must be configured during queue creation:

x-dead-letter-exchange:	<<Exchange_to_bind_with>>
x-dead-letter-routing-key:	<<Routing_key_to_bind_with>>

The following property should be configured in properties of consumer project:

spring.rabbitmq.listener.simple.default-requeue-rejected=false
spring.rabbitmq.listener.direct.retry.enabled=true
spring.rabbitmq.listener.direct.retry.initial-interval=2000
spring.rabbitmq.listener.direct.retry.max-attempts=3
spring.rabbitmq.listener.direct.retry.multiplier=1.5
spring.rabbitmq.listener.direct.retry.max-interval=5000

An another way to deserialize JSON:

@JsonCreator
    public PaymentOrder(@JsonProperty("from") String from,
                        @JsonProperty("to") String to,
                        @JsonProperty("amount") BigDecimal amount) {
        this.from = from;
        this.to = to;
        this.amount = amount;
    }