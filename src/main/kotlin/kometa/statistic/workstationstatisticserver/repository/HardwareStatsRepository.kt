package kometa.statistic.workstationstatisticserver.repository

import kometa.statistic.workstationstatisticserver.entity.HardwareStats
import kometa.statistic.workstationstatisticserver.entity.Workstation
import org.springframework.data.jpa.repository.JpaRepository

interface HardwareStatsRepository : JpaRepository<HardwareStats, Long> {
    fun findByWorkstation(workstation: Workstation): List<HardwareStats>
}