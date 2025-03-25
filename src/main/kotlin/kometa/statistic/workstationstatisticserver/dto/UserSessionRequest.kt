package kometa.statistic.workstationstatisticserver.dto

data class UserSessionRequest(
    val users: List<UserSessionDto>
)

data class UserSessionDto(
    val username: String,
    val sessionStart: String,
    val sessionEnd: String? = null
)