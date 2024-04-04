package study.multi.jen.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/health")
class TestController {

    @GetMapping
    fun checkHealth(): String {
        return "OK"
    }

}