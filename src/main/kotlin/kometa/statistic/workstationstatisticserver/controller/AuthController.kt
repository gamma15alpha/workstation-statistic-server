package kometa.statistic.workstationstatisticserver.controller

import kometa.statistic.workstationstatisticserver.dto.AuthRequest
import kometa.statistic.workstationstatisticserver.dto.AuthResponse
import kometa.statistic.workstationstatisticserver.dto.RegisterRequest
import kometa.statistic.workstationstatisticserver.repository.UserRepository
import kometa.statistic.workstationstatisticserver.security.JwtService
import kometa.statistic.workstationstatisticserver.entity.User
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authManager: AuthenticationManager,
    private val userRepo: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService
) {

    @PostMapping("/login")
    fun login(@RequestBody req: AuthRequest): AuthResponse {
        val auth = UsernamePasswordAuthenticationToken(req.username, req.password)
        authManager.authenticate(auth)
        val token = jwtService.generateToken(req.username)
        return AuthResponse(token)
    }

    @PostMapping("/register")
    fun register(@RequestBody req: RegisterRequest): AuthResponse {
        if (userRepo.existsByLogin(req.username)) {
            throw IllegalArgumentException("User already exists")
        }

        val user = User(
            login = req.username,
            hashedPassword = passwordEncoder.encode(req.password),
            role = req.role
        )
        userRepo.save(user)
        val token = jwtService.generateToken(user.login)
        return AuthResponse(token)
    }
}