package kometa.statistic.workstationstatisticserver.repository

import kometa.statistic.workstationstatisticserver.entity.Component
import kometa.statistic.workstationstatisticserver.entity.Workstation
import org.springframework.data.jpa.repository.JpaRepository

interface ComponentRepository : JpaRepository<Component, Long> {
    fun findByWorkstation(workstation: Workstation): List<Component>
}