package com.shayan.feature.user_session

import com.shayan.feature.users_session.constants.UserSessionConst
import com.shayan.feature.users_session.model.UserSession
import com.shayan.feature.users_session.repository.UserSessionRepository
import com.shayan.feature.users_session.service.UserSessionService
import core.database.dbQuery
import core.security.token.RefreshTokenGenerator
import io.ktor.server.plugins.NotFoundException
import io.mockk.*
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Before
import kotlin.test.*
import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

class UserSessionServiceTest {

    private lateinit var repository: UserSessionRepository
    private lateinit var service: UserSessionService

    private val fixedInstant =
        Instant.parse("2026-02-26T12:00:00Z")

    private val clock =
        Clock.fixed(
            fixedInstant,
            ZoneOffset.UTC
        )

    @BeforeTest
    fun setup() {

        // mock dbQuery
        mockkStatic("core.database.DbQueryKt")

        coEvery {
            dbQuery<Any>(any())
        } coAnswers {
            firstArg<suspend () -> Any>().invoke()
        }

        // IMPORTANT: mock object
        mockkObject(RefreshTokenGenerator)

        every {
            RefreshTokenGenerator.generate(
                any(),
                any()
            )
        } returns "fake-token"

        repository = mockk()

        service = UserSessionService(
            repository,
            clock
        )
    }

    @AfterTest
    fun tearDown() {
        unmockkAll()
    }
    private fun session() =
        UserSession(
            id = "session-id",
            userId = "user-1",
            refreshToken = "token",
            deviceId = "device-1",
            expiresAt = fixedInstant.plusSeconds(
                UserSessionConst.PLUS_SECOND
            ),
            revoked = false,
            createdAt = fixedInstant,
            lastUsedAt = fixedInstant
        )

    @Test
    fun addSession_returns_created_session() = runTest {

        val resultSession = session()

        coEvery {
            repository.addSession(any())
        } returns resultSession

        val result =
            service.addSession(
                userId = "user-1",
                deviceId = "device-1"
            )

        assertEquals(
            resultSession.userId,
            result.userId
        )

        assertEquals(
            resultSession.deviceId,
            result.deviceId
        )

        coVerify(exactly = 1) {
            repository.addSession(any())
        }
    }

    @Test
    fun addSession_throws_when_repository_returns_null() =
        runTest {

            coEvery {
                repository.addSession(any())
            } returns null

            assertFailsWith<NotFoundException> {
                service.addSession(
                    "user-1",
                    "device-1"
                )
            }

            coVerify {
                repository.addSession(any())
            }
        }

    @Test
    fun updateLastUsed_returns_updated_session() =
        runTest {

            val resultSession = session()

            coEvery {
                repository.updateLastUsed("user-1")
            } returns resultSession

            val result =
                service.updateLastUsed(
                    "user-1"
                )

            assertEquals(
                resultSession,
                result
            )

            coVerify {
                repository.updateLastUsed(
                    "user-1"
                )
            }
        }

    @Test
    fun updateLastUsed_throws_when_not_found() =
        runTest {

            coEvery {
                repository.updateLastUsed(any())
            } returns null

            assertFailsWith<NotFoundException> {
                service.updateLastUsed(
                    "user-1"
                )
            }
        }

    @Test
    fun revokeSession_calls_repository() =
        runTest {

            coJustRun {
                repository.revokeSession(
                    "user-1"
                )
            }

            service.revokeSession("user-1")

            coVerify(exactly = 1) {
                repository.revokeSession(
                    "user-1"
                )
            }
        }

    @Test
    fun getSessionByID_returns_session() =
        runTest {

            val session = session()

            coEvery {
                repository.getSessionByUserId(
                    "user-1"
                )
            } returns session

            val result =
                service.getSessionByID(
                    "user-1"
                )

            assertEquals(
                session,
                result
            )
        }

    @Test
    fun getSessionByID_throws_when_not_found() =
        runTest {

            coEvery {
                repository.getSessionByUserId(any())
            } returns null

            assertFailsWith<NotFoundException> {
                service.getSessionByID(
                    "user-1"
                )
            }
        }

    @Test
    fun verify_returns_session() =
        runTest {

            val session = session()

            coEvery {
                repository.getTokenByHash(
                    "hash123"
                )
            } returns session

            val result =
                service.verify(
                    "hash123"
                )

            assertEquals(
                session,
                result
            )

            coVerify {
                repository.getTokenByHash(
                    "hash123"
                )
            }
        }

    @Test
    fun verify_throws_when_token_not_found() =
        runTest {

            coEvery {
                repository.getTokenByHash(any())
            } returns null

            assertFailsWith<NotFoundException> {
                service.verify(
                    "hash123"
                )
            }
        }
}