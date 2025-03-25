package kometa.statistic.workstationstatisticserver.security

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Service
import java.util.*
import javax.crypto.SecretKey

@Service
class JwtService(
    private val jwtConfig: JwtConfig
) {
    private val secretKey: SecretKey by lazy {
        Keys.hmacShaKeyFor(jwtConfig.secret.toByteArray())
    }

    fun generateToken(username: String): String {
        val now = Date()
        val expiryDate = Date(now.time + jwtConfig.expiration)

        return Jwts.builder()
            .setSubject(username)
            .setIssuedAt(now)
            .setExpiration(expiryDate)
            .signWith(secretKey, SignatureAlgorithm.HS256)
            .compact()
    }

    fun validateToken(token: String): Boolean {
        return try {
            val claims = Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (ex: Exception) {
            false
        }
    }

    fun extractUsername(token: String): String? {
        return try {
            val claims = Jwts.parserBuilder().setSigningKey(secretKey).build()
                .parseClaimsJws(token)
            claims.body.subject
        } catch (ex: Exception) {
            null
        }
    }
}