package di

import com.shayan.feature.audit_logs.repository.AuditLogRepository
import com.shayan.feature.audit_logs.repository.AuditLogRepositoryImpl
import org.koin.core.context.GlobalContext.get
import org.koin.dsl.module

val repositoryModule = module {

    single<AuditLogRepository> { AuditLogRepositoryImpl() }
}
