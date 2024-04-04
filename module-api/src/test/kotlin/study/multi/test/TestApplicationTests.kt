package study.multi.test

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import study.multi.jen.JenApplication

@SpringBootTest
@ContextConfiguration(classes = [JenApplication::class])
class TestApplicationTests {

	@Test
	fun contextLoads() {
	}

}
