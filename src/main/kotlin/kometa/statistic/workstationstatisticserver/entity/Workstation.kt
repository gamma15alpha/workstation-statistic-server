package kometa.statistic.workstationstatisticserver.entity

import jakarta.persistence.*

@Entity
data class Workstation(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(unique = true)
    val uuid: String = "unknown",  // Уникальный идентификатор

    val name: String = "unknown",
    val ipAddress: String = "unknown",
    val location: String = "unknown",

    @Lob
    val qrCode: ByteArray? = null,

    @OneToMany(mappedBy = "workstation", cascade = [CascadeType.ALL], orphanRemoval = true)
    val components: List<Component> = listOf(),

    @OneToMany(mappedBy = "workstation", cascade = [CascadeType.ALL], orphanRemoval = true)
    val softwares: List<Software> = listOf()
)