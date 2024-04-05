package study.multi.jen.service

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.rabbitmq.client.Channel
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import study.multi.jen.dto.MessageDto

private val logger = KotlinLogging.logger {}
private val objectMapper = jacksonObjectMapper()

@Service
class NotificationConsumeService {

    @RabbitListener(queues = ["\${rabbitmq.queue.name}"])
    fun receiveMessage(message: Message, channel: Channel) {
        val messageDto = objectMapper.readValue(message.body, MessageDto::class.java)
        logger.info { "Received Message Title : ${messageDto.title}" }
        logger.info { "Received Message Content : ${messageDto.content}" }
        logger.info { "Received Message Inner Test Name : ${messageDto.event.testEventName}" }
    }
}