package study.multi.jen.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DeadLetterRabbitMQConfig {

    @Value("\${rabbitmq.dead.queue.name}")
    private lateinit var deadLetterQueueName: String
    @Value("\${rabbitmq.dead.exchange.name}")
    private lateinit var deadLetterExchangeName: String
    @Value("\${rabbitmq.dead.routing.key}")
    private lateinit var deadLetterRoutingKey: String

    @Bean
    fun deadLetterQueue(): Queue = Queue(deadLetterQueueName)

    @Bean
    fun deadLetterDirectExchange(): DirectExchange = DirectExchange(deadLetterExchangeName)

    @Bean
    fun deadLetterBinding(queue: Queue, exchange: DirectExchange): Binding
            = BindingBuilder.bind(queue).to(exchange).with(deadLetterRoutingKey)

}