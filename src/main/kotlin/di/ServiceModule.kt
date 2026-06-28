package di

import com.shayan.feature.address.service.AddressService
import com.shayan.feature.answer.service.AnswerService
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.category.service.CategoryService
import com.shayan.feature.comment.service.CommentService
import com.shayan.feature.discount.service.DiscountService
import com.shayan.feature.email_verifier.service.EmailVerifierService
import com.shayan.feature.employee.service.EmployeeService
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.employee_audit_log.table.EmployeeAuditLogTable
import com.shayan.feature.filter.dto.FilterResponse
import com.shayan.feature.filter.service.FilterService
import com.shayan.feature.notification.service.NotificationService
import com.shayan.feature.order.service.OrderService
import com.shayan.feature.order_product.service.OrderProductService
import com.shayan.feature.product.service.ProductService
import com.shayan.feature.product_image.service.ProductImageService
import com.shayan.feature.question.service.QuestionService
import com.shayan.feature.role.service.RoleService
import com.shayan.feature.search_history.service.SearchHistoryService
import com.shayan.feature.setting.service.SettingService
import com.shayan.feature.support_chat.service.SupportChatService
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

    single {
        ProductService(
            repository = get(),
            employeeAuditLogService = get(),
            discountService = get(),
            productImageService = get()
        )
    }

    single {
        QuestionService(
            repository = get(),
            usersService = get()
        )
    }
    
    single {
        CommentService(
            repository = get()
        )
    }

    single {
        AnswerService(
            repository = get()
        )
    }

    single {
        NotificationService(
            repository = get()
        )
    }

    single {
        OrderService(
            repository = get()
        )
    }

    single {
        OrderProductService(
            repository = get()
        )
    }

    single {
        SupportChatService(
            repository = get()
        )
    }
}
