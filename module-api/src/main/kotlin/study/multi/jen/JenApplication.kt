package study.multi.jen

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
	scanBasePackageClasses = [ CommonRoot::class, DomainRoot::class, ExternalRoot::class ]
)
class JenApplication

fun main(args: Array<String>) {
	runApplication<JenApplication>(*args)
}
