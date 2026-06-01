package di

import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.user_auth.service.UserAuthService
import com.shayan.feature.users.service.UsersService
import com.shayan.feature.users_session.service.UserSessionService
import org.koin.dsl.module

val serviceModule = module {

    single {
        AuditLogService(
            auditLogRepository = get()
        )
    }
    single {
        UserSessionService(
            userSessionRepo = get()
        )
    }
    single {
        UserAuthService(
            userSessionService = get()
        )
    }

    single {
        UsersService(
            usersRepository = get(),
            authService = get(),
            auditLogService = get()
        )
    }
}
