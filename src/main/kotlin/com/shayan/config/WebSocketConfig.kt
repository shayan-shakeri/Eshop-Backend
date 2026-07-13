package com.shayan.config

import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.websocket.WebSockets
import io.ktor.server.websocket.pingPeriod
import kotlin.time.Duration

fun Application.configureWebSocket() {
    install(WebSockets)
}