
package com.shayan

import com.shayan.config.configureSchedulers
import com.shayan.config.configureWebSocket
import com.shayan.feature.error_log.service.ErrorLogService
import config.*
import core.plugin.configureStatusPages
import io.ktor.server.application.*
import org.koin.ktor.ext.inject
import routes.registerRoutes


fun Application.application() {
    configureSerialization()
    configureWebSocket()
    configureSecurity()
    configureDatabase()
    configureKoin()
    configureSchedulers()

    val errorLoqService by inject<ErrorLogService>()
    configureStatusPages(errorLoqService)

    registerRoutes()
}

