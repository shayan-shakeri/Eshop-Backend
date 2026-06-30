package com.shayan.feature.monthly_log.scheduler

import com.shayan.feature.monthly_log.service.MonthlyLogService
import io.ktor.server.application.Application
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import kotlin.time.Duration.Companion.milliseconds

class MonthlyLogScheduler(
    private val service: MonthlyLogService
) {

    fun start(
        application: Application
    ) {

        application.launch {

            while (true) {

                val now =
                    Instant.now()
                        .atZone(
                            ZoneId.systemDefault()
                        )

                if (
                    now.dayOfMonth == 1 &&
                    now.hour == 0 &&
                    now.minute == 0
                ) {

                    val previousMonth =
                        now.minusMonths(1)

                    service.generate(
                        month = previousMonth.monthValue,
                        year = previousMonth.year
                    )
                }

                delay(
                    60_000.milliseconds
                )
            }
        }
    }
}