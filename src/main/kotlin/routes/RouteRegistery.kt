package routes

import com.shayan.feature.audit_logs.route.auditLogRoutes
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.auth.constants.UserAuthConst
import com.shayan.feature.user_auth.route.userAuthRoutes
import com.shayan.feature.user_auth.service.UserAuthService
import com.shayan.feature.users_session.service.UserSessionService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.registerRoutes() {
    routing {
        val auditLogService by inject<AuditLogService>()
        auditLogRoutes(auditLogService)

        val userAuthService by inject<UserAuthService>()
        userAuthRoutes(userAuthService)
    }
}
