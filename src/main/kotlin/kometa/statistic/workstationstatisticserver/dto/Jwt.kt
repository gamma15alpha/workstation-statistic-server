package kometa.statistic.workstationstatisticserver.dto

// dto/AuthRequest.kt
data class AuthRequest(val username: String, val password: String)

// dto/RegisterRequest.kt
data class RegisterRequest(val username: String, val password: String, val role: String = "CLIENT")

// dto/AuthResponse.kt
data class AuthResponse(val token: String)