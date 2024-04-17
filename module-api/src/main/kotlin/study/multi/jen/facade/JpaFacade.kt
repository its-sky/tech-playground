package study.multi.jen.facade

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.multi.jen.Post
import study.multi.jen.dto.request.CreateMemberRequest
import study.multi.jen.dto.request.CreatePostRequest
import study.multi.jen.dto.response.QueryPostResponse
import study.multi.jen.service.MemberService
import study.multi.jen.service.PostService

@Service
@Transactional
class JpaFacade(
    private val memberService: MemberService,
    private val postService: PostService
) {
    fun createMember(request: CreateMemberRequest) {
        memberService.createMember(request)
    }

    fun createPost(request: CreatePostRequest) {
        if (memberService.isExistMember(request.memberId)) {
            return postService.createPost(request)
        }
    }

    @Transactional(readOnly = true)
    fun queryPost(memberId: Long, postId: Long): QueryPostResponse {
        val member = memberService.findMember(memberId)
        return postService.findPost(member.id, postId)
    }
}