package study.multi.jen.dto.request

data class CreatePostRequest(
    val memberId: Long,
    val title: String,
    val content: String
)
