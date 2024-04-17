package study.multi.jen.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study.multi.jen.dto.request.CreateMemberRequest
import study.multi.jen.facade.JpaFacade

@RestController
@RequestMapping("/member")
class MemberController(
    private val jpaFacade: JpaFacade
) {
    @PostMapping
    fun createMember(@RequestBody request: CreateMemberRequest) {
        jpaFacade.createMember(request)
    }
}