package terabite.service

import org.springframework.stereotype.Service
import java.net.ConnectException
import java.sql.Connection
import java.sql.DriverManager

@Service
class AWSService {

    fun getRDSConnection(): Connection {
        val con: Connection = DriverManager.getConnection("", "", "") ?: throw ConnectException()
        return con
    }
}