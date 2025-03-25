package kometa.statistic.workstationstatisticserver.controller.api

import kometa.statistic.workstationstatisticserver.dto.*
import kometa.statistic.workstationstatisticserver.entity.*
import kometa.statistic.workstationstatisticserver.repository.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/api/workstations")
class WorkstationApiController(
    private val workstationRepo: WorkstationRepository,
    private val statsRepo: HardwareStatsRepository,
    private val processRepo: RunningProcessRepository,
    private val softwareRepo: SoftwareRepository,
    private val userSessionRepo: UserSessionRepository
) {

    @PostMapping("/register")
    fun register(@RequestBody req: RegisterWorkstationRequest): ResponseEntity<Any> {
        if (workstationRepo.findByUuid(req.uuid).isPresent) {
            return ResponseEntity.badRequest().body("Workstation already registered")
        }

        val workstation = Workstation(
            uuid = req.uuid,
            name = req.name,
            ipAddress = req.ipAddress,
            location = req.location
        )

        workstationRepo.save(workstation)
        return ResponseEntity.ok("Registered")
    }

    @GetMapping("/{uuid}")
    fun getInfo(@PathVariable uuid: String): ResponseEntity<Workstation> {
        val ws = workstationRepo.findByUuid(uuid).orElseThrow()
        return ResponseEntity.ok(ws)
    }

    @PostMapping("/{uuid}/stats")
    fun reportStats(@PathVariable uuid: String, @RequestBody req: StatReportRequest): ResponseEntity<String> {
        val ws = workstationRepo.findByUuid(uuid).orElseThrow()

        val stats = HardwareStats(
            cpuLoad = req.cpuLoad,
            ramUsage = req.ramUsage,
            gpuLoad = req.gpuLoad,
            diskUsage = req.diskUsage,
            workstation = ws
        )

        statsRepo.save(stats)
        return ResponseEntity.ok("Stats saved")
    }

    @PostMapping("/{uuid}/apps")
    fun reportApps(@PathVariable uuid: String, @RequestBody req: ProcessReportRequest): ResponseEntity<String> {
        val ws = workstationRepo.findByUuid(uuid).orElseThrow()

        // Remove old records (optional)
        processRepo.deleteAll(processRepo.findByWorkstation(ws))
        softwareRepo.deleteAll(softwareRepo.findByWorkstation(ws))

        val processes = req.processes.map {
            RunningProcess(
                processName = it.name,
                pid = it.pid,
                cpuUsage = it.cpu,
                memoryUsage = it.memory,
                workstation = ws
            )
        }

        val softwares = req.softwares.map {
            Software(name = it.name, version = it.version, vendor = it.vendor, workstation = ws)
        }

        processRepo.saveAll(processes)
        softwareRepo.saveAll(softwares)
        return ResponseEntity.ok("Processes and software saved")
    }

    @PostMapping("/{uuid}/users")
    fun reportUsers(@PathVariable uuid: String, @RequestBody req: UserSessionRequest): ResponseEntity<String> {
        val ws = workstationRepo.findByUuid(uuid).orElseThrow()
        val users = req.users.map {
            UserSession(
                username = it.username,
                sessionStart = it.sessionStart,
                sessionEnd = it.sessionEnd,
                workstation = ws)
        }
        userSessionRepo.saveAll(users)
        return ResponseEntity.ok("Users saved")
    }

    @GetMapping("/{uuid}/qr")
    fun getQr(@PathVariable uuid: String): ResponseEntity<String> {
        val ws = workstationRepo.findByUuid(uuid).orElseThrow()
        val base64 = Base64.getEncoder().encodeToString(ws.qrCode ?: byteArrayOf())
        return ResponseEntity.ok(base64)
    }
}