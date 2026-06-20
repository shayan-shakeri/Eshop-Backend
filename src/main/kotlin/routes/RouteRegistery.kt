package routes

import com.shayan.feature.address.route.addressRoute
import com.shayan.feature.address.service.AddressService
import com.shayan.feature.audit_logs.route.auditLogRoutes
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.category.route.categoryRoute
import com.shayan.feature.category.service.CategoryService
import com.shayan.feature.discount.route.discountRoute
import com.shayan.feature.discount.service.DiscountService
import com.shayan.feature.email_verifier.route.emailVerifierRoute
import com.shayan.feature.email_verifier.service.EmailVerifierService
import com.shayan.feature.employee.route.employeeRoute
import com.shayan.feature.employee.service.EmployeeService
import com.shayan.feature.employee_audit_log.route.employeeAuditLog
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.filter.route.filterRouting
import com.shayan.feature.filter.service.FilterService
import com.shayan.feature.product_image.route.productImageRoute
import com.shayan.feature.product_image.service.ProductImageService
import com.shayan.feature.role.route.roleRoute
import com.shayan.feature.role.service.RoleService
import com.shayan.feature.search_history.route.searchHistoryRoute
import com.shayan.feature.search_history.service.SearchHistoryService
import com.shayan.feature.user_auth.route.userAuthRoutes
import com.shayan.feature.user_auth.service.UserAuthService
import com.shayan.feature.user_pic.route.userPicRoute
import com.shayan.feature.user_pic.service.UserPicService
import com.shayan.feature.users.route.userRoutes
import com.shayan.feature.users.service.UsersService
import io.ktor.server.application.*
import io.ktor.server.response.respond
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

        val roleService by inject<RoleService>()
        roleRoute(roleService)

        val employeeService by inject<EmployeeService>()
        employeeRoute(employeeService)

        val categoryService by inject<CategoryService>()
        categoryRoute(categoryService)

        val filterService by inject<FilterService>()
        filterRouting(filterService)

        val productImageService by inject<ProductImageService>()
        productImageRoute(productImageService)

        val discountService by inject<DiscountService>()
        discountRoute(discountService)
    }
}
