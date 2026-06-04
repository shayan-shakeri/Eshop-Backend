package core.database

import core.consts.AppConstants
import core.util.EnvLoader
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import core.consts.ENV
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {

    fun init() {

        val config = HikariConfig().apply {

            jdbcUrl = EnvLoader.get(ENV.DB_URL)

            username = EnvLoader.get(ENV.DB_USER)

            password = EnvLoader.get(ENV.DB_PASSWORD)

            maximumPoolSize =
                EnvLoader.getInt(ENV.DB_POOL_SIZE)

        }

        val datasource = HikariDataSource(config)

        Database.connect(datasource)
    }

}
