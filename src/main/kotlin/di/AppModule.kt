package di

import com.shayan.core.image_controller.ImageController
import com.shayan.core.security.hasher.Sha256Hasher
import com.shayan.feature.email_verifier.service.EmailSender
import com.shayan.feature.monthly_log.scheduler.MonthlyLogScheduler
import org.koin.dsl.module

val appModule = module {
    single {
        EmailSender()
    }
    single {
        Sha256Hasher()
    }
    single {
        ImageController()
    }

    single {
        MonthlyLogScheduler(
            get()
        )
    }
}
