package kometa.statistic.workstationstatisticserver.repository

import kometa.statistic.workstationstatisticserver.entity.Workstation
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface WorkstationRepository : JpaRepository<Workstation, Long> {
    fun findByUuid(uuid: String): Optional<Workstation>
}