package study.multi.jen.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.multi.jen.Post
import study.multi.jen.PostRepository
import study.multi.jen.dto.request.CreatePostRequest
import study.multi.jen.dto.response.QueryPostResponse

@Service
class PostService(
    private val postRepository: PostRepository
) {
    fun createPost(request: CreatePostRequest) {
        postRepository.save(Post(
            title = request.title,
            content = request.content,
            memberId = request.memberId
        ))
    }

    @Transactional(readOnly = true)
    fun findPost(memberId: Long, postId: Long): QueryPostResponse {
        val post = postRepository.findByIdOrNull(postId) ?: throw IllegalArgumentException("Not Found Post")
        if (!post.memberId.equals(memberId)) {
            throw IllegalArgumentException("Forbidden Error")
        }
        return QueryPostResponse(
            title = post.title,
            content = post.content
        )
    }
}