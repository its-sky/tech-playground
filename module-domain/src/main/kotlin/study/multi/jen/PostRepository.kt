package study.multi.jen

import org.springframework.data.jpa.repository.JpaRepository

interface PostRepository: JpaRepository<Post, Long>