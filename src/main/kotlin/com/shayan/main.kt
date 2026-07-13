package com.shayan

import com.shayan.config.configureSchedulers
import com.shayan.config.configureWebSocket
import com.shayan.feature.error_log.service.ErrorLogService
import config.configureDatabase
import config.configureKoin
import config.configureSecurity
import config.configureSerialization
import core.plugin.configureStatusPages
import io.ktor.server.application.*
import io.ktor.server.netty.EngineMain
import org.koin.ktor.ext.inject
import routes.registerRoutes
import kotlin.getValue

fun main(args: Array<String>) {
    println("=== MAIN START ===")
    io.ktor.server.netty.EngineMain.main(args)
    println("=== MAIN END ===")
}

fun Application.application() {
    println("=== APPLICATION MODULE START ===")

    configureSerialization()
    println("serialization ok")

    configureWebSocket()
    println("websocket ok")

    configureSecurity()
    println("security ok")

    configureDatabase()
    println("database ok")

    configureKoin()
    println("koin ok")

    configureSchedulers()
    println("scheduler ok")

    val errorLoqService by inject<ErrorLogService>()
    configureStatusPages(errorLoqService)

    registerRoutes()

    println("=== APPLICATION MODULE END ===")
}