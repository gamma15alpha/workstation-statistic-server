package kometa.statistic.workstationstatisticserver.controller.admin

import kometa.statistic.workstationstatisticserver.repository.*
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/admin/workstations")
class AdminController(
    private val workstationRepo: WorkstationRepository,
    private val statsRepo: HardwareStatsRepository,
    private val processRepo: RunningProcessRepository
) {

    @GetMapping
    fun list(model: Model): String {
        model.addAttribute("workstations", workstationRepo.findAll())
        return "admin/workstations"
    }

    @GetMapping("/{id}")
    fun detail(@PathVariable id: Long, model: Model): String {
        val ws = workstationRepo.findById(id).orElseThrow()
        model.addAttribute("workstation", ws)
        return "admin/workstation_detail"
    }

    // AJAX endpoint для автоподгрузки
    @GetMapping("/{id}/stats")
    @ResponseBody
    fun getStats(@PathVariable id: Long): Map<String, Any> {
        val stats = statsRepo.findByWorkstation(workstationRepo.findById(id).orElseThrow())
            .lastOrNull() ?: return emptyMap()

        val workstation = stats.workstation ?: return emptyMap()

        val processes = processRepo.findByWorkstation(workstation).map {
            mapOf<String, Any>(
                "name" to it.processName,
                "pid" to it.pid,
                "cpu" to it.cpuUsage,
                "memory" to it.memoryUsage
            )
        }

        val result = mutableMapOf<String, Any>(
            "cpuLoad" to stats.cpuLoad,
            "ramUsage" to stats.ramUsage,
            "processes" to processes
        )

        stats.gpuLoad?.let { result["gpuLoad"] = it }
        stats.diskUsage?.let { result["diskUsage"] = it }

        return result
    }

}
