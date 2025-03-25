package kometa.statistic.workstationstatisticserver.repository

import kometa.statistic.workstationstatisticserver.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<User, Long> {
    fun findByLogin(username: String): Optional<User>
    fun existsByLogin(username: String): Boolean
}