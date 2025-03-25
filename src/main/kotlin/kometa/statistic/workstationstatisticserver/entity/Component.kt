package kometa.statistic.workstationstatisticserver.entity

import jakarta.persistence.*

@Entity
data class Component(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String = "unknown",           // "CPU", "GPU", "RAM"
    val manufacturer: String = "unknown",
    val model: String = "unknown",
    val serialNumber: String = "unknown",

    @ManyToOne
    @JoinColumn(name = "workstation_id")
    val workstation: Workstation? = null,
)