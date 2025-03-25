package kometa.statistic.workstationstatisticserver.dto

data class ProcessReportRequest(
    val processes: List<ProcessDto>,
    val softwares: List<SoftwareDto>
)

data class ProcessDto(
    val name: String,
    val pid: Int,
    val cpu: Double,
    val memory: Double
)

data class SoftwareDto(
    val name: String,
    val version: String,
    val vendor: String
)