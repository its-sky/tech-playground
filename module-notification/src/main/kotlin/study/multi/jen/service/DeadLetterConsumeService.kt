package study.multi.jen.service

import com.rabbitmq.client.Channel
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Service
import kotlin.math.log
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType.*

private val logger = KotlinLogging.logger {}

@Service
class DeadLetterConsumeService {

    @RabbitListener(
        queues = ["\${RABBIT_MQ_DEAD_QUEUE_NAME}"]
    )
    fun onDeadLetterMessage(message: Message, channel: Channel) {
        logger.error { "Dead Letter Fallback !" }
    }

}