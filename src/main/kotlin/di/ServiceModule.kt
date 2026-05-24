package di

import com.shayan.feature.audit_logs.service.AuditLogService
import org.koin.dsl.module

val serviceModule = module {

    single {
        AuditLogService(
            auditLogRepository = get()
        )
    }
}
