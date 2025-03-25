package kometa.statistic.workstationstatisticserver.repository

import kometa.statistic.workstationstatisticserver.entity.Software
import kometa.statistic.workstationstatisticserver.entity.Workstation
import org.springframework.data.jpa.repository.JpaRepository

interface SoftwareRepository : JpaRepository<Software, Long> {
    fun findByWorkstation(workstation: Workstation): List<Software>
}