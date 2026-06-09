package di

import com.shayan.feature.address.service.AddressService
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.email_verifier.service.EmailVerifierService
import com.shayan.feature.search_history.service.SearchHistoryService
import com.shayan.feature.user_auth.service.UserAuthService
import com.shayan.feature.user_pic.service.UserPicService
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

    single {
        AddressService(
            addressRepository = get(),
            auditLogService = get()
        )
    }

    single {
        SearchHistoryService(
            searchHistoryRepository = get(),
            auditLogService = get()
        )
    }
    single {
        EmailVerifierService(
            repository = get(),
            sha256Hasher = get(),
            emailSender = get(),
            auditLogService = get()
        )
    }

    single {
        UserPicService(
            repository = get(),
            imageController = get(),
            auditLogService = get(),
        )
    }
}
