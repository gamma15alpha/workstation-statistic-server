package kometa.statistic.workstationstatisticserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class WorkstationStatisticServerApplication

fun main(args: Array<String>) {
    runApplication<WorkstationStatisticServerApplication>(*args)
}
