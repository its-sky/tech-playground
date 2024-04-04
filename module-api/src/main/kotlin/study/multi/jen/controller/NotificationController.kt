package study.multi.jen.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study.multi.jen.dto.MessageDto
import study.multi.jen.service.NotificationPublishService

@RestController
@RequestMapping("/message")
class NotificationController(
    private val notificationPublishService: NotificationPublishService
) {

    @PostMapping
    fun sendMessage(@RequestBody messageDto: MessageDto): ResponseEntity<String> {
        notificationPublishService.sendMessage(messageDto)
        return ResponseEntity.ok().body("success")
    }

}