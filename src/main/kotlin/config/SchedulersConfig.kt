package com.shayan.config

import com.shayan.feature.monthly_log.scheduler.MonthlyLogScheduler
import io.ktor.server.application.Application
import org.koin.ktor.ext.inject

fun Application.configureSchedulers() {

    val scheduler by inject<MonthlyLogScheduler>()

    scheduler.start(
        this
    )
}