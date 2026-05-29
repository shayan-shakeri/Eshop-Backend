package com.shayan.feature.users.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.exception.InvalidCredentials
import com.shayan.feature.audit_logs.model.AuditLog
import com.shayan.feature.audit_logs.repository.AuditLogRepository
import com.shayan.feature.audit_logs.service.AuditLogService
import com.shayan.feature.user_auth.service.UserAuthService
import com.shayan.feature.users.constants.UsersConst
import com.shayan.feature.users.dto.*
import com.shayan.feature.users.mapper.toUserNoTokenResponse
import com.shayan.feature.users.mapper.toUserResponse
import com.shayan.feature.users.model.Users
import com.shayan.feature.users.repository.UserRepository
import com.shayan.util.Gender
import core.database.dbQuery
import core.security.password.PasswordHashResult
import core.security.password.PasswordHasher
import core.util.IdGenerator
import io.ktor.server.plugins.*
import java.time.LocalDate


class UsersService(
    private val usersRepository: UserRepository,
    private val authService: UserAuthService,
    private val auditLogService: AuditLogService
) {
    suspend fun login(user: UserLoginRequest): UserResponse {

        val db =  dbQuery {
            val userResponse = usersRepository.findByEmail(user.email) ?: throw InvalidCredentials()
            val password = PasswordHashResult(
                hash = user.passwordHash,
                iterations = userResponse.iterations,
                algorithm = userResponse.algorithm,
                salt = userResponse.salt
            )

            runCatching { auditLogService.add(userResponse.id, UsersConst.LOGIN_ACTION, user.ip) }

            if (userResponse.passwordHash != password.hash) throw InvalidCredentials()

            val token = authService.generateRefreshToken(userResponse.id, user.deviceId)
            userResponse.toUserResponse(token.accessToken, token.refreshToken)

        }
        runCatching { auditLogService.add(db.id, UsersConst.LOGIN_TOKEN_ACTION, user.ip) }
        return db
    }

    suspend fun loginToken(id: String, ip: String): UserNoTokenResponse  {
        runCatching { auditLogService.add(id, UsersConst.LOGIN_TOKEN_ACTION, ip) }
        return dbQuery {
            usersRepository.findById(id)?.toUserNoTokenResponse() ?: throw NotFoundException()
        }
    }

    suspend fun signup(user: UserSignupRequest): UserResponse  {
        val db =  dbQuery{
            val hashResult = PasswordHasher.hashPassword(user.password)
            val id = IdGenerator.generate()


            val usersRequest = Users(
                id = id,
                fullName = user.fullName,
                email = user.email,
                phoneNumber = user.phoneNumber,
                gender = user.gender,
                birthday = user.birthday,
                passwordHash = hashResult.hash,
                iterations = hashResult.iterations,
                algorithm = hashResult.algorithm,
                salt = hashResult.salt
            )

            val users = usersRepository.add(usersRequest) ?: throw FailedToAdd()
            val token = authService.generateRefreshToken(users.id, user.deviceId)



            users.toUserResponse(token.accessToken, token.refreshToken)
        }

        runCatching { auditLogService.add(db.id, UsersConst.SIGNUP_ACTION,user.ip)}
        return db
    }

    suspend fun updateInfo(id: String, user: UserUpdateInfoRequest): UserNoTokenResponse   {
        runCatching { auditLogService.add(id, UsersConst.UPDATE_INFO_ROUTE,user.ip)}
        return dbQuery{
            val usersRequest = Users(
                id = id,
                fullName = user.fullName,
                email = user.email,
                phoneNumber = user.phoneNumber,
                gender = user.gender,
                birthday = user.birthday,
                passwordHash = "",
                iterations = 0,
                algorithm = "",
                salt = ""
            )
            usersRepository.updateInfo(usersRequest)?.toUserNoTokenResponse() ?: throw NotFoundException()
        }
    }

    suspend fun updatePassword(id: String, user: UserUpdatePasswordRequest): UserNoTokenResponse  {
        runCatching { auditLogService.add(id, UsersConst.UPDATE_PASSWORD_ACTION, user.ip)}
        return dbQuery {
            val checkValidity = usersRepository.findByPassword(user.oldPassword) ?: throw NotFoundException()
            if (checkValidity.id != id) throw InvalidCredentials()

            val password = PasswordHasher.hashPassword(user.newPassword)
            val usersRequest = Users(
                id = id,
                fullName = "",
                email = "",
                phoneNumber = "",
                gender = Gender.NotSpecified,
                birthday = LocalDate.now(),
                passwordHash = password.hash,
                iterations = password.iterations,
                algorithm = password.algorithm,
                salt = password.salt
            )
            usersRepository.updatePassword(usersRequest)?.toUserNoTokenResponse() ?: throw NotFoundException()


        }
    }

    suspend fun logout(id: String, ip: String) {
        runCatching { auditLogService.add(id, UsersConst.LOGOUT_ACTION, ip)}
        authService.revokeRefreshToken(id)
    }

    suspend fun deleteUser(id: String, ip: String) {
        runCatching { auditLogService.add(id, UsersConst.DELETE_ACTION, ip)}
        authService.revokeRefreshToken(id)
        dbQuery{ usersRepository.delete(id) }
    }

}