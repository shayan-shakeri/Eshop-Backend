
package com.shayan

import com.shayan.config.configureWebSocket
import config.*
import io.ktor.server.application.*
import routes.registerRoutes


fun Application.application() {
    configureSerialization()
    configureWebSocket()
    configureSecurity()
    configureDatabase()
    configureKoin()
    registerRoutes()
}

