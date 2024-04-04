package study.multi.jen.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.amqp.support.converter.MessageConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {

    @Value("\${spring.rabbitmq.host}")
    private lateinit var host: String;
    @Value("\${spring.rabbitmq.port}")
    private lateinit var port: Integer;
    @Value("\${spring.rabbitmq.username}")
    private lateinit var username: String;
    @Value("\${spring.rabbitmq.password}")
    private lateinit var password: String;

    @Value("\${rabbitmq.queue.name}")
    private lateinit var queueName: String;
    @Value("\${rabbitmq.exchange.name}")
    private lateinit var exchangeName: String;
    @Value("\${rabbitmq.routing.key}")
    private lateinit var routingKey: String;

    @Bean
    fun queue(): Queue = Queue(queueName)

    @Bean
    fun directExchange(): DirectExchange = DirectExchange(exchangeName)

    @Bean
    fun binding(queue: Queue, exchange: DirectExchange): Binding
    = BindingBuilder.bind(queue).to(exchange).with(routingKey)

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = jackson2JsonMessageConverter()
        return rabbitTemplate
    }

    @Bean
    fun jackson2JsonMessageConverter(): MessageConverter {
        return Jackson2JsonMessageConverter()
    }

}