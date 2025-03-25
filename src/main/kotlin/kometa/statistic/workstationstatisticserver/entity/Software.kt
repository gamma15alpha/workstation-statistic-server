package kometa.statistic.workstationstatisticserver.entity

import jakarta.persistence.*

@Entity
data class Software(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String = "unknown",
    val version: String = "unknown",
    val vendor: String = "unknown",

    @ManyToOne
    @JoinColumn(name = "workstation_id")
    val workstation: Workstation? = null,
)