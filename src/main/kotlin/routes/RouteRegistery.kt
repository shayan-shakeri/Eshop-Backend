package routes

import com.shayan.feature.address.route.addressRoute
import com.shayan.feature.address.service.AddressService
import com.shayan.feature.answer.route.answerRoute
import com.shayan.feature.answer.service.AnswerService
import com.shayan.feature.audit_logs.route.auditLogRoutes
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.banner.route.bannerRoute
import com.shayan.feature.banner.service.BannerService
import com.shayan.feature.category.route.categoryRoute
import com.shayan.feature.category.service.CategoryService
import com.shayan.feature.comment.route.commentRoute
import com.shayan.feature.comment.service.CommentService
import com.shayan.feature.discount.route.discountRoute
import com.shayan.feature.discount.service.DiscountService
import com.shayan.feature.email_verifier.route.emailVerifierRoute
import com.shayan.feature.email_verifier.service.EmailVerifierService
import com.shayan.feature.employee.route.employeeRoute
import com.shayan.feature.employee.service.EmployeeService
import com.shayan.feature.employee_audit_log.route.employeeAuditLog
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.error_log.route.errorLogRoute
import com.shayan.feature.error_log.service.ErrorLogService
import com.shayan.feature.favorites.route.favoriteRoute
import com.shayan.feature.favorites.service.FavoritesService
import com.shayan.feature.filter.route.filterRoute
import com.shayan.feature.filter.service.FilterService
import com.shayan.feature.health.healthHealthRoute
import com.shayan.feature.monthly_log.route.monthlyLogRoute
import com.shayan.feature.monthly_log.service.MonthlyLogService
import com.shayan.feature.notification.route.notificationRoute
import com.shayan.feature.notification.service.NotificationService
import com.shayan.feature.order.service.OrderService
import com.shayan.feature.order_product.route.orderProductRoute
import com.shayan.feature.order_product.service.OrderProductService
import com.shayan.feature.product.route.productRoutes
import com.shayan.feature.product.service.ProductService
import com.shayan.feature.product_image.route.productImageRoute
import com.shayan.feature.product_image.service.ProductImageService
import com.shayan.feature.question.route.questionRoute
import com.shayan.feature.question.service.QuestionService
import com.shayan.feature.role.route.roleRoute
import com.shayan.feature.role.service.RoleService
import com.shayan.feature.search_history.route.searchHistoryRoute
import com.shayan.feature.search_history.service.SearchHistoryService
import com.shayan.feature.setting.route.settingRoute
import com.shayan.feature.setting.service.SettingService
import com.shayan.feature.support_chat.route.supportChatRoute
import com.shayan.feature.support_chat.service.SupportChatService
import com.shayan.feature.support_message.route.supportMessageRoute
import com.shayan.feature.support_message.service.SupportMessageService
import com.shayan.feature.user_auth.route.userAuthRoutes
import com.shayan.feature.user_auth.service.UserAuthService
import com.shayan.feature.user_pic.route.userPicRoute
import com.shayan.feature.user_pic.service.UserPicService
import com.shayan.feature.users.route.userRoutes
import com.shayan.feature.users.service.UsersService
import com.shayan.feature.version_control.route.versionControlRoute
import com.shayan.feature.version_control.service.VersionControlService
import feature.orders.route.orderRoute
import io.ktor.http.HttpStatusCode
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
        filterRoute(filterService)

        val productImageService by inject<ProductImageService>()
        productImageRoute(productImageService)

        val discountService by inject<DiscountService>()
        discountRoute(discountService)

        val settingService by inject<SettingService>()
        settingRoute(settingService)

        val productService by inject<ProductService>()
        productRoutes(productService)

        val questionService by inject<QuestionService>()
        questionRoute(questionService)

        val commentService by inject<CommentService>()
        commentRoute(commentService)

        val answerService by inject<AnswerService>()
        answerRoute(answerService)

        val notificationService by inject<NotificationService>()
        notificationRoute(notificationService)

        val orderService by inject<OrderService>()
        orderRoute(orderService)

        val orderProductService by inject< OrderProductService>()
        orderProductRoute(orderProductService)

        val supportChatService by inject<SupportChatService>()
        supportChatRoute(supportChatService)

        val supportMessageService by inject<SupportMessageService>()
        supportMessageRoute(supportMessageService)

        val favoritesService by inject<FavoritesService>()
        favoriteRoute(favoritesService)

        val versionControlService by inject<VersionControlService>()
        versionControlRoute(versionControlService)

        val errorLogService by inject<ErrorLogService>()
        errorLogRoute(errorLogService)

        val bannerService by inject<BannerService>()
        bannerRoute(bannerService)

        val monthlyLogService by inject<MonthlyLogService>()
        monthlyLogRoute(monthlyLogService)

        healthHealthRoute()
    }
}
