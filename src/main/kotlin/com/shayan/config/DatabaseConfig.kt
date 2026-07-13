package config

import core.database.DatabaseFactory
import io.ktor.server.application.*

fun configureDatabase() {

    DatabaseFactory.init()

}
