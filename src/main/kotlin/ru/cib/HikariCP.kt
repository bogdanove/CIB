package ru.cib

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import java.sql.Connection
import java.sql.SQLException


object DataSource {
    private val config = HikariConfig()
    private var ds: HikariDataSource? = null


    init {
        config.jdbcUrl = "jdbc:postgresql://localhost:5432/cibtest"
        config.username = "postgres"
        config.password = "passtest"
        config.addDataSourceProperty("cachePrepStmts" , "true")
        config.addDataSourceProperty("prepStmtCacheSize" , "250")
        config.addDataSourceProperty("prepStmtCacheSqlLimit" , "2048")
        ds = HikariDataSource(config)
    }

    @Throws(SQLException::class)
    fun getConnection(): Connection {
        return ds!!.connection
    }
}