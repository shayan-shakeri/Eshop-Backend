package di

import com.shayan.feature.address.repository.AddressRepository
import com.shayan.feature.address.repository.AddressRepositoryImpl
import com.shayan.feature.answer.repository.AnswerRepository
import com.shayan.feature.answer.repository.AnswerRepositoryImpl
import com.shayan.feature.audit_logs.repository.AuditLogRepository
import com.shayan.feature.audit_logs.repository.AuditLogRepositoryImpl
import com.shayan.feature.category.repository.CategoryRepository
import com.shayan.feature.category.repository.CategoryRepositoryImpl
import com.shayan.feature.comment.repository.CommentRepository
import com.shayan.feature.comment.repository.CommentRepositoryImpl
import com.shayan.feature.discount.repository.DiscountRepository
import com.shayan.feature.discount.repository.DiscountRepositoryImpl
import com.shayan.feature.email_verifier.repository.EmailVerifierRepository
import com.shayan.feature.email_verifier.repository.EmailVerifierRepositoryImpl
import com.shayan.feature.employee.repository.EmployeeRepository
import com.shayan.feature.employee.repository.EmployeeRepositoryImpl
import com.shayan.feature.employee_audit_log.repository.EmployeeAuditLogRepository
import com.shayan.feature.employee_audit_log.repository.EmployeeAuditLogRepositoryImpl
import com.shayan.feature.favorites.repository.FavoritesRepository
import com.shayan.feature.favorites.repository.FavoritesRepositoryImpl
import com.shayan.feature.filter.repository.RepositoryFilter
import com.shayan.feature.filter.repository.RepositoryFilterImpl
import com.shayan.feature.notification.repository.NotificationRepository
import com.shayan.feature.notification.repository.NotificationRepositoryImpl
import com.shayan.feature.order.repository.OrderRepository
import com.shayan.feature.order.repository.OrderRepositoryImpl
import com.shayan.feature.order_product.repository.OrderProductRepository
import com.shayan.feature.order_product.repository.OrderProductRepositoryImpl
import com.shayan.feature.product.repository.ProductRepository
import com.shayan.feature.product.repository.ProductRepositoryImpl
import com.shayan.feature.product_image.repository.ProductImageRepository
import com.shayan.feature.product_image.repository.ProductImageRepositoryImpl
import com.shayan.feature.question.repository.QuestionRepository
import com.shayan.feature.question.repository.QuestionRepositoryImpl
import com.shayan.feature.role.repository.RoleRepository
import com.shayan.feature.role.repository.RoleRepositoryImpl
import com.shayan.feature.search_history.repository.SearchHistoryRepository
import com.shayan.feature.search_history.repository.SearchHistoryRepositoryImpl
import com.shayan.feature.setting.repository.SettingRepository
import com.shayan.feature.setting.repository.SettingRepositoryImpl
import com.shayan.feature.support_chat.repository.SupportChatRepository
import com.shayan.feature.support_chat.repository.SupportChatRepositoryImpl
import com.shayan.feature.support_message.repository.SupportMessageRepository
import com.shayan.feature.support_message.repository.SupportMessageRepositoryImpl
import com.shayan.feature.user_pic.repository.UserPicRepository
import com.shayan.feature.user_pic.repository.UserPicRepositoryImpl
import com.shayan.feature.users.repository.UserRepository
import com.shayan.feature.users.repository.UserRepositoryImpl
import com.shayan.feature.users_session.repository.UserSessionRepository
import com.shayan.feature.users_session.repository.UserSessionRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<AuditLogRepository> { AuditLogRepositoryImpl() }
    single<UserSessionRepository> { UserSessionRepositoryImpl() }
    single<UserRepository> { UserRepositoryImpl() }
    single<AddressRepository> { AddressRepositoryImpl() }
    single<SearchHistoryRepository> { SearchHistoryRepositoryImpl() }
    single<EmailVerifierRepository> { EmailVerifierRepositoryImpl() }
    single<UserPicRepository> { UserPicRepositoryImpl() }
    single<EmployeeAuditLogRepository> { EmployeeAuditLogRepositoryImpl() }
    single<RoleRepository> { RoleRepositoryImpl() }
    single<EmployeeRepository> { EmployeeRepositoryImpl() }
    single<CategoryRepository> { CategoryRepositoryImpl() }
    single<RepositoryFilter> { RepositoryFilterImpl() }
    single<ProductImageRepository> { ProductImageRepositoryImpl() }
    single<DiscountRepository> { DiscountRepositoryImpl() }
    single<SettingRepository> { SettingRepositoryImpl() }
    single<ProductRepository> { ProductRepositoryImpl() }
    single<QuestionRepository> { QuestionRepositoryImpl() }
    single<CommentRepository> { CommentRepositoryImpl() }
    single<AnswerRepository> { AnswerRepositoryImpl() }
    single<NotificationRepository> { NotificationRepositoryImpl() }
    single<OrderRepository> { OrderRepositoryImpl() }
    single<OrderProductRepository> { OrderProductRepositoryImpl() }
    single<SupportChatRepository> { SupportChatRepositoryImpl() }
    single<SupportMessageRepository> { SupportMessageRepositoryImpl() }
    single<FavoritesRepository> { FavoritesRepositoryImpl() }
    
}
