package kometa.statistic.workstationstatisticserver.dto

data class StatReportRequest(
    val cpuLoad: Double,
    val ramUsage: Double,
    val gpuLoad: Double?,
    val diskUsage: Double?
)