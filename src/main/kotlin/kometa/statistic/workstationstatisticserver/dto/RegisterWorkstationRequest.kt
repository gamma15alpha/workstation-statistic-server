package kometa.statistic.workstationstatisticserver.dto

data class RegisterWorkstationRequest(
    val uuid: String,
    val name: String,
    val ipAddress: String,
    val location: String
)