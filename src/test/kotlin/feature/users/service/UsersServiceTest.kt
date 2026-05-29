package com.shayan.feature.users.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.exception.InvalidCredentials
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.auth.dto.UserAuthResponse
import com.shayan.feature.user_auth.service.UserAuthService
import com.shayan.feature.users.dto.*
import com.shayan.feature.users.model.Users
import com.shayan.feature.users.repository.UserRepository
import com.shayan.util.Gender
import core.database.dbQuery
import core.security.password.PasswordHashResult
import core.security.password.PasswordHasher
import core.util.IdGenerator
import io.ktor.server.plugins.NotFoundException
import io.mockk.*
import kotlinx.coroutines.test.runTest
import kotlin.test.*
import java.time.LocalDate

class UsersServiceTest {

    private lateinit var usersRepository: UserRepository
    private lateinit var authService: UserAuthService
    private lateinit var auditLogService: AuditLogService
    private lateinit var usersService: UsersService

    private val user = Users(
        id = "user-id",
        fullName = "Shayan",
        email = "test@test.com",
        phoneNumber = "123456",
        gender = Gender.Male,
        birthday = LocalDate.now(),
        passwordHash = "hashed-password",
        iterations = 1000,
        algorithm = "SHA-256",
        salt = "salt"
    )

    @BeforeTest
    fun setup() {

        mockkStatic("core.database.DbQueryKt")

        coEvery {
            dbQuery<Any>(any())
        } coAnswers {
            firstArg<suspend () -> Any>().invoke()
        }

        mockkObject(PasswordHasher)
        mockkObject(IdGenerator)

        every {
            IdGenerator.generate()
        } returns "generated-id"

        every {
            PasswordHasher.hashPassword(any())
        } returns PasswordHashResult(
            hash = "hashed-password",
            iterations = 1000,
            algorithm = "SHA-256",
            salt = "salt"
        )

        usersRepository = mockk()
        authService = mockk()
        auditLogService = mockk(relaxed = true)

        usersService = UsersService(
            usersRepository,
            authService,
            auditLogService
        )
    }

    @AfterTest
    fun teardown() {
        unmockkAll()
    }

    @Test
    fun login_returns_user_response() = runTest {

        val request = UserLoginRequest(
            email = "test@test.com",
            passwordHash = "hashed-password",
            deviceId = "device-id",
            ip = "127.0.0.1"
        )

        coEvery {
            usersRepository.findByEmail(request.email)
        } returns user

        coEvery {
            authService.generateRefreshToken(
                user.id,
                request.deviceId
            )
        } returns UserAuthResponse(
            accessToken = "access-token",
            refreshToken = "refresh-token"
        )

        val result = usersService.login(request)

        assertEquals(user.id, result.id)
        assertEquals("access-token", result.accessToken)
        assertEquals("refresh-token", result.refreshToken)
    }

    @Test
    fun login_throws_when_user_not_found() = runTest {

        val request = UserLoginRequest(
            email = "test@test.com",
            passwordHash = "password",
            deviceId = "device-id",
            ip = "127.0.0.1"
        )

        coEvery {
            usersRepository.findByEmail(request.email)
        } returns null

        assertFailsWith<InvalidCredentials> {
            usersService.login(request)
        }
    }

    @Test
    fun loginToken_returns_user() = runTest {

        coEvery {
            usersRepository.findById("user-id")
        } returns user

        val result = usersService.loginToken(
            "user-id",
            "127.0.0.1"
        )

        assertEquals(user.id, result.id)
    }

    @Test
    fun signup_returns_created_user() = runTest {

        val request = UserSignupRequest(
            fullName = "Shayan",
            email = "test@test.com",
            phoneNumber = "123456",
            gender = Gender.Male,
            birthday = LocalDate.now(),
            password = "password",
            deviceId = "device-id",
            ip = "127.0.0.1"
        )

        coEvery {
            usersRepository.add(any())
        } returns user.copy(
            id = "generated-id"
        )

        coEvery {
            authService.generateRefreshToken(
                any(),
                any()
            )
        } returns UserAuthResponse(
            accessToken = "access-token",
            refreshToken = "refresh-token"
        )

        val result = usersService.signup(request)

        assertEquals("generated-id", result.id)
        assertEquals("access-token", result.accessToken)
    }

    @Test
    fun signup_throws_when_add_fails() = runTest {

        val request = UserSignupRequest(
            fullName = "Shayan",
            email = "test@test.com",
            phoneNumber = "123456",
            gender = Gender.Male,
            birthday = LocalDate.now(),
            password = "password",
            deviceId = "device-id",
            ip = "127.0.0.1"
        )

        coEvery {
            usersRepository.add(any())
        } returns null

        assertFailsWith<FailedToAdd> {
            usersService.signup(request)
        }
    }

    @Test
    fun updateInfo_returns_updated_user() = runTest {

        val request = UserUpdateInfoRequest(
            fullName = "Updated",
            email = "updated@test.com",
            phoneNumber = "999999",
            gender = Gender.Male,
            birthday = LocalDate.now(),
            ip = "127.0.0.1"
        )

        coEvery {
            usersRepository.updateInfo(any())
        } returns user

        val result = usersService.updateInfo(
            "user-id",
            request
        )

        assertEquals(user.id, result.id)
    }

    @Test
    fun updatePassword_returns_updated_user() = runTest {

        val request = UserUpdatePasswordRequest(
            oldPassword = "old-password",
            newPassword = "new-password",
            ip = "127.0.0.1"
        )

        coEvery {
            usersRepository.findByPassword(
                request.oldPassword
            )
        } returns user

        coEvery {
            usersRepository.updatePassword(any())
        } returns user

        val result = usersService.updatePassword(
            "user-id",
            request
        )

        assertEquals(user.id, result.id)
    }

    @Test
    fun updatePassword_throws_when_credentials_invalid() = runTest {

        val request = UserUpdatePasswordRequest(
            oldPassword = "wrong-password",
            newPassword = "new-password",
            ip = "127.0.0.1"
        )

        coEvery {
            usersRepository.findByPassword(
                request.oldPassword
            )
        } returns user.copy(
            id = "another-id"
        )

        assertFailsWith<InvalidCredentials> {
            usersService.updatePassword(
                "user-id",
                request
            )
        }
    }

    @Test
    fun logout_calls_revoke_refresh_token() = runTest {

        coJustRun {
            authService.revokeRefreshToken(
                "user-id"
            )
        }

        usersService.logout(
            "user-id",
            "127.0.0.1"
        )

        coVerify {
            authService.revokeRefreshToken(
                "user-id"
            )
        }
    }

    @Test
    fun deleteUser_calls_delete_and_revoke() = runTest {

        coJustRun {
            authService.revokeRefreshToken(
                "user-id"
            )
        }

        coJustRun {
            usersRepository.delete(
                "user-id"
            )
        }

        usersService.deleteUser(
            "user-id",
            "127.0.0.1"
        )

        coVerify {
            authService.revokeRefreshToken(
                "user-id"
            )
        }

        coVerify {
            usersRepository.delete(
                "user-id"
            )
        }
    }
}