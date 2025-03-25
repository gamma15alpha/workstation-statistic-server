package kometa.statistic.workstationstatisticserver.entity

import jakarta.persistence.*

@Entity
data class RunningProcess(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val processName: String = "unknown",
    val pid: Int = 0,
    val cpuUsage: Double = 0.0,
    val memoryUsage: Double = 0.0,

    @ManyToOne
    @JoinColumn(name = "workstation_id")
    val workstation: Workstation? = null,
)