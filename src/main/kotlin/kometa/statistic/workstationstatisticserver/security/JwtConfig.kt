package kometa.statistic.workstationstatisticserver.security

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

@Component
@ConfigurationProperties(prefix = "jwt")
class JwtConfig {
    lateinit var secret: String
    var expiration: Long = 3600000
}