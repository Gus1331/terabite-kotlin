package terabite

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import terabite.service.AWSService

@SpringBootApplication
class Application

fun main(args: Array<String>) {
	val aws = AWSService()
	aws.getRDSConnection()
	runApplication<Application>(*args)
}
