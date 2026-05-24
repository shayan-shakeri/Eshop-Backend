package routes

import com.shayan.feature.audit_logs.route.auditLogRoutes
import com.shayan.feature.audit_logs.service.AuditLogService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.registerRoutes() {
    routing {
        val auditLogService by inject<AuditLogService>()
        auditLogRoutes(auditLogService)

    }
}
