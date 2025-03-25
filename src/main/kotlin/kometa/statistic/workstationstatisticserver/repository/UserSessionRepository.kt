package kometa.statistic.workstationstatisticserver.repository

import kometa.statistic.workstationstatisticserver.entity.UserSession
import kometa.statistic.workstationstatisticserver.entity.Workstation
import org.springframework.data.jpa.repository.JpaRepository

interface UserSessionRepository : JpaRepository<UserSession, Long> {
    fun findByWorkstation(workstation: Workstation): List<UserSession>
}