package study.multi.jen.service

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.multi.jen.dto.MessageDto

private val logger = KotlinLogging.logger {}

@Service
class NotificationPublishService(
    private val rabbitTemplate: RabbitTemplate
) {
    @Value("\${rabbitmq.queue.name}")
    private lateinit var queueName: String
    @Value("\${rabbitmq.exchange.name}")
    private lateinit var exchangeName: String
    @Value("\${rabbitmq.routing.key}")
    private lateinit var routingKey: String

    fun sendMessage(messageDto: MessageDto) {
        logger.info { "message send : ${messageDto.toString()}" }
        this.rabbitTemplate.convertAndSend(exchangeName, routingKey, messageDto)
    }

}