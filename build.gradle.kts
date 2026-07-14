
plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(ktorLibs.plugins.ktor)
    alias(libs.plugins.kotlin.serialization)
}

group = "com.shayan"
version = "1.0.1"

application {
    mainClass.set("com.shayan.MainKt")
}

tasks.shadowJar {
    manifest {
        attributes(
            "Main-Class" to "com.shayan.MainKt"
        )
    }
    mergeServiceFiles()
}

kotlin {
    jvmToolchain(21)
}
dependencies {
    // Ktor Core and Netty
    implementation(ktorLibs.server.core)
    implementation(ktorLibs.server.netty)

    // Content Negotiation for JSON
    implementation(ktorLibs.server.contentNegotiation)
    implementation(ktorLibs.serialization.kotlinx.json)

    // Authentication (JWT)
    implementation(ktorLibs.server.auth)
    implementation(ktorLibs.server.auth.jwt)

    // Logging and Request Validation
    implementation(ktorLibs.server.callLogging)
    implementation(ktorLibs.server.requestValidation)

    // Status Pages for error handling
    implementation(ktorLibs.server.statusPages)

    // Exposed for database operations (ORM)
    implementation(libs.exposed.core)
    implementation(libs.exposed.dao)
    implementation(libs.exposed.jdbc)
    implementation(libs.mysql.connector) // MySQL driver
    implementation(libs.hikaricp) // Connection pooling

    // Koin for dependency injection
    implementation(libs.koin.ktor)
    implementation(libs.koin.loggerSlf4j)

    // Rate Limiting
    implementation(libs.flaxoos.ktor.server.rateLimiting)

    // Logging
    implementation(libs.logback.classic)

    // Ktor YAML Configuration
    implementation(libs.ktor.config.yaml)

    // Exposed Java Time support
    implementation(libs.java.datetime)

    // Testing
    testImplementation(kotlin("test"))
    testImplementation(ktorLibs.server.testHost)
    testImplementation(libs.mockk)

    // Resend
    implementation(libs.resend)

    //websocket
    implementation(libs.websocket)
}
