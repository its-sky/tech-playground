package study.multi.jen.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import study.multi.jen.Member
import study.multi.jen.MemberRepository
import study.multi.jen.dto.request.CreateMemberRequest

@Service
@Transactional
class MemberService(
    private val memberRepository: MemberRepository
) {
    fun createMember(request: CreateMemberRequest) {
        memberRepository.save(
            Member(
                name = request.name,
                age = request.age
            )
        )
    }

    @Transactional(readOnly = true)
    fun findMember(memberId: Long): Member {
        return memberRepository.findByIdOrNull(memberId) ?: throw IllegalArgumentException("Not Found User")
    }

    @Transactional(readOnly = true)
    fun isExistMember(memberId: Long): Boolean {
        return memberRepository.existsById(memberId)
    }
}