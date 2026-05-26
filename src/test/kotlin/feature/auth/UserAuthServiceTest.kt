package com.shayan.feature.auth_user.service

import com.shayan.feature.auth.dto.UserAuthResponse
import com.shayan.feature.user_auth.service.UserAuthService
import com.shayan.feature.users_session.model.UserSession
import com.shayan.feature.users_session.service.UserSessionService
import core.security.token.AccessTokenGenerator
import io.mockk.*
import kotlinx.coroutines.test.runTest
import kotlin.test.*
import java.time.Instant

class UserAuthServiceTest {

    private lateinit var userSessionService: UserSessionService
    private lateinit var authService: UserAuthService

    private val session = UserSession(
        id = "session-id",
        userId = "user-123",
        refreshToken = "refresh-token",
        deviceId = "device-1",
        expiresAt = Instant.now(),
        revoked = false,
        createdAt = Instant.now(),
        lastUsedAt = Instant.now()
    )

    @BeforeTest
    fun setup() {
        userSessionService = mockk()

        mockkObject(AccessTokenGenerator)

        every {
            AccessTokenGenerator.userToken(any())
        } returns "access-token"

        authService = UserAuthService(
            userSessionService
        )
    }

    @AfterTest
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun generateAccessToken_returns_access_token() = runTest {

        coEvery {
            userSessionService.verify("token")
        } returns session

        coEvery {
            userSessionService.updateLastUsed("user-123")
        } returns session

        val result =
            authService.generateAccessToken("token")

        assertEquals(
            "access-token",
            result
        )

        coVerify {
            userSessionService.verify("token")
        }

        coVerify {
            userSessionService.updateLastUsed("user-123")
        }
    }

    @Test
    fun generateRefreshToken_returns_existing_session_tokens() = runTest {

        coEvery {
            userSessionService.getSessionByUserId("user-123")
        } returns session

        val result =
            authService.generateRefreshToken(
                "user-123",
                "device-1"
            )

        assertEquals(
            UserAuthResponse(
                "access-token",
                "refresh-token"
            ),
            result
        )

        coVerify {
            userSessionService.getSessionByUserId("user-123")
        }

        coVerify(exactly = 0) {
            userSessionService.addSession(
                any(),
                any()
            )
        }
    }

    @Test
    fun generateRefreshToken_creates_new_session_when_missing() = runTest {

        coEvery {
            userSessionService.getSessionByUserId("user-123")
        } returns null

        coEvery {
            userSessionService.addSession(
                "user-123",
                "device-1"
            )
        } returns session

        val result =
            authService.generateRefreshToken(
                "user-123",
                "device-1"
            )

        assertEquals(
            UserAuthResponse(
                "access-token",
                "refresh-token"
            ),
            result
        )

        coVerify {
            userSessionService.addSession(
                "user-123",
                "device-1"
            )
        }
    }

    @Test
    fun revokeRefreshToken_calls_service() = runTest {

        coJustRun {
            userSessionService.revokeSession("user-123")
        }

        authService.revokeRefreshToken(
            "user-123"
        )

        coVerify {
            userSessionService.revokeSession(
                "user-123"
            )
        }
    }
}