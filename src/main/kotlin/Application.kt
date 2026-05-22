
package com.shayan

import config.*
import io.ktor.server.application.*
import routes.registerRoutes


fun Application.application() {
    configureSerialization()
    configureSecurity()
    configureDatabase()
    configureKoin()
    registerRoutes()
}

