package di

import com.shayan.feature.address.repository.AddressRepository
import com.shayan.feature.address.repository.AddressRepositoryImpl
import com.shayan.feature.audit_logs.repository.AuditLogRepository
import com.shayan.feature.audit_logs.repository.AuditLogRepositoryImpl
import com.shayan.feature.email_verifier.repository.EmailVerifierRepository
import com.shayan.feature.email_verifier.repository.EmailVerifierRepositoryImpl
import com.shayan.feature.employee_audit_log.repository.EmployeeAuditLogRepository
import com.shayan.feature.employee_audit_log.repository.EmployeeAuditLogRepositoryImpl
import com.shayan.feature.role.repository.RoleRepository
import com.shayan.feature.role.repository.RoleRepositoryImpl
import com.shayan.feature.search_history.repository.SearchHistoryRepository
import com.shayan.feature.search_history.repository.SearchHistoryRepositoryImpl
import com.shayan.feature.user_pic.dto.UserPicResponse
import com.shayan.feature.user_pic.repository.UserPicRepository
import com.shayan.feature.user_pic.repository.UserPicRepositoryImpl
import com.shayan.feature.users.repository.UserRepository
import com.shayan.feature.users.repository.UserRepositoryImpl
import com.shayan.feature.users_session.repository.UserSessionRepository
import com.shayan.feature.users_session.repository.UserSessionRepositoryImpl
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module

val repositoryModule = module {

    single<AuditLogRepository> { AuditLogRepositoryImpl() }
    single<UserSessionRepository> { UserSessionRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl() }
    single<AddressRepository> { AddressRepositoryImpl() }
    single<SearchHistoryRepository> { SearchHistoryRepositoryImpl() }
    single<EmailVerifierRepository> { EmailVerifierRepositoryImpl() }
    single<UserPicRepository>{ UserPicRepositoryImpl() }
    single<EmployeeAuditLogRepository> { EmployeeAuditLogRepositoryImpl() }
    single<RoleRepository> { RoleRepositoryImpl() }
}
