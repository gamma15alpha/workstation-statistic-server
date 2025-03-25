package kometa.statistic.workstationstatisticserver.entity

import jakarta.persistence.*

@Entity
data class HardwareStats(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val cpuLoad: Double = 0.0,
    val ramUsage: Double = 0.0,
    val gpuLoad: Double? = null,
    val diskUsage: Double? = null,

    @ManyToOne
    @JoinColumn(name = "workstation_id")
    val workstation: Workstation? = null,
)