package com.shayan.feature.audit_logs.service

import com.shayan.feature.audit_logs.model.AuditLog
import com.shayan.feature.audit_logs.repository.AuditLogRepository
import core.database.dbQuery
import io.mockk.*
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.time.Instant
import kotlin.test.assertEquals

class AuditLogServiceTest {

    private val repository = mockk<AuditLogRepository>()
    private val service = AuditLogService(repository)

    @Before
    fun setup() {
        mockkStatic("core.database.DbQueryKt")
        coEvery { dbQuery<Any>(any()) } answers {
            runBlocking { (it.invocation.args[0] as suspend () -> Any).invoke() }
        }
    }

    @After
    fun tearDown() {
        unmockkStatic("core.database.DbQueryKt")
    }

    @Test
    fun `get should return list of audit logs when called with valid userId`() = runBlocking {
        val userId = "user123"
        val expectedLogs = listOf(
            AuditLog("id1", userId, "127.0.0.1", "LOGIN", Instant.now())
        )

        coEvery { repository.findByUserId(userId) } returns expectedLogs

        val result = service.get(userId)

        assertEquals(expectedLogs, result)
    }
}
