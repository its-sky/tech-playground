package study.multi.jen.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.connection.CorrelationData
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.multi.jen.dto.MessageDto
import java.nio.charset.StandardCharsets
import java.util.UUID

private val logger = KotlinLogging.logger {}
private val objectMapper = jacksonObjectMapper()

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
        logger.info { "message send : $messageDto" }
        val json = objectMapper.writeValueAsString(messageDto)
        val message = Message(json.toByteArray(StandardCharsets.UTF_8))
        this.rabbitTemplate.convertAndSend(exchangeName,
                                            routingKey,
                                            message,
                                            CorrelationData(UUID.randomUUID().toString())
        )
    }

}