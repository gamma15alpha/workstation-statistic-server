package kometa.statistic.workstationstatisticserver.repository

import kometa.statistic.workstationstatisticserver.entity.RunningProcess
import kometa.statistic.workstationstatisticserver.entity.Workstation
import org.springframework.data.jpa.repository.JpaRepository

interface RunningProcessRepository : JpaRepository<RunningProcess, Long> {
    fun findByWorkstation(workstation: Workstation): List<RunningProcess>
}