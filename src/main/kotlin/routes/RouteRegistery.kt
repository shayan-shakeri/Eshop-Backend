package routes

import com.shayan.feature.address.route.addressRoute
import com.shayan.feature.address.service.AddressService
import com.shayan.feature.audit_logs.route.auditLogRoutes
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.email_verifier.route.emailVerifierRoute
import com.shayan.feature.email_verifier.service.EmailVerifierService
import com.shayan.feature.employee_audit_log.route.employeeAuditLog
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.search_history.route.searchHistoryRoute
import com.shayan.feature.search_history.service.SearchHistoryService
import com.shayan.feature.user_auth.route.userAuthRoutes
import com.shayan.feature.user_auth.service.UserAuthService
import com.shayan.feature.user_pic.route.userPicRoute
import com.shayan.feature.user_pic.service.UserPicService
import com.shayan.feature.users.route.userRoutes
import com.shayan.feature.users.service.UsersService
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.registerRoutes() {
    routing {
        val auditLogService by inject<AuditLogService>()
        auditLogRoutes(auditLogService)

        val userAuthService by inject<UserAuthService>()
        userAuthRoutes(userAuthService)

        val userService by inject<UsersService>()
        userRoutes(userService)

        val addressService by inject<AddressService>()
        addressRoute(addressService)

        val searchHistoryService by inject<SearchHistoryService>()
        searchHistoryRoute(searchHistoryService)

        val emailVerifierService by inject<EmailVerifierService>()
        emailVerifierRoute(emailVerifierService)

        val userPicService by inject<UserPicService>()
        userPicRoute(userPicService)

        val employeeAuditLogService by inject<EmployeeAuditLogService>()
        employeeAuditLog(employeeAuditLogService)
    }
}
