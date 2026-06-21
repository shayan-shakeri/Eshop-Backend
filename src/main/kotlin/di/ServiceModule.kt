package di

import com.shayan.feature.address.service.AddressService
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.category.service.CategoryService
import com.shayan.feature.discount.service.DiscountService
import com.shayan.feature.email_verifier.service.EmailVerifierService
import com.shayan.feature.employee.service.EmployeeService
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.employee_audit_log.table.EmployeeAuditLogTable
import com.shayan.feature.filter.dto.FilterResponse
import com.shayan.feature.filter.service.FilterService
import com.shayan.feature.product_image.service.ProductImageService
import com.shayan.feature.role.service.RoleService
import com.shayan.feature.search_history.service.SearchHistoryService
import com.shayan.feature.setting.service.SettingService
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

    single {
        EmployeeAuditLogService(
            repository = get()
        )
    }

    single {
        RoleService(
            repository = get(),
            employeeAuditLogService = get()
        )
    }

    single {
        EmployeeService(
            employeeRepository = get(),
            employeeAuditLogService = get(),
            roleService = get(),
            userService = get()
        )
    }

    single {
        CategoryService(
            categoryRepository = get(),
            employeeAuditLogService = get(),
            imageController = get()
        )
    }

    single {
        FilterService(
            filterRepository = get(),
            employeeAuditLogService = get(),
            imageController = get()
        )
    }

    single {
        ProductImageService(
            repository = get(),
            employeeAuditLogService = get(),
            imageController = get(),
        )
    }

    single {
        DiscountService(
            repository = get(),
            employeeAuditLogService = get()
        )
    }

    single {
        SettingService(
            repository = get(),
            employeeAuditLogService = get()
        )
    }
}
