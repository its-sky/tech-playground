package study.multi.jen.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import study.multi.jen.Post
import study.multi.jen.dto.request.CreateMemberRequest
import study.multi.jen.dto.request.CreatePostRequest
import study.multi.jen.dto.response.QueryPostResponse
import study.multi.jen.facade.JpaFacade

@RestController
@RequestMapping("/post")
class PostController(
    private val jpaFacade: JpaFacade
) {
    @PostMapping
    fun createPost(@RequestBody request: CreatePostRequest) {
        jpaFacade.createPost(request)
    }

    @GetMapping
    fun queryPost(@RequestParam memberId: Long, @RequestParam postId: Long): ResponseEntity<QueryPostResponse> {
        return ResponseEntity.ok().body(jpaFacade.queryPost(memberId, postId))
    }
}