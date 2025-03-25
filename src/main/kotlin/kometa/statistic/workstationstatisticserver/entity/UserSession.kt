package kometa.statistic.workstationstatisticserver.entity

import jakarta.persistence.*

@Entity
data class UserSession(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val username: String = "unknown",
    val sessionStart: String = "unknown",
    val sessionEnd: String? = null,

    @ManyToOne
    @JoinColumn(name = "workstation_id")
    val workstation: Workstation? = null,
)