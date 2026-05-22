package core.database

import core.consts.AppConstants
import core.util.EnvLoader
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import org.jetbrains.exposed.sql.Database

object DatabaseFactory {

    fun init() {

        val config = HikariConfig().apply {

            jdbcUrl = EnvLoader.get(AppConstants.Env.DB_URL)

            username = EnvLoader.get(AppConstants.Env.DB_USER)

            password = EnvLoader.get(AppConstants.Env.DB_PASSWORD)

            maximumPoolSize =
                EnvLoader.getInt(AppConstants.Env.DB_POOL_SIZE)

        }

        val datasource = HikariDataSource(config)

        Database.connect(datasource)
    }

}
