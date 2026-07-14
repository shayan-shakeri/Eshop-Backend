package com.shayan.feature.employee.service

import com.shayan.core.exception.EmailExist
import com.shayan.core.exception.FailedToAdd
import com.shayan.core.exception.InvalidCredentials
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.employee.dto.*
import com.shayan.feature.employee.mapper.toEmployeeNoTokenResponse
import com.shayan.feature.employee.mapper.toEmployeeResponse
import com.shayan.feature.employee.model.Employee
import com.shayan.feature.employee.repository.EmployeeRepository
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.role.service.RoleService
import com.shayan.feature.users.service.UsersService
import com.shayan.util.enum.Gender
import core.database.dbQuery
import core.exception.Forbidden
import core.security.password.PasswordHasher
import core.security.token.AccessTokenGenerator
import core.util.IdGenerator
import feature.employee.constants.EmployeeConst
import feature.employee.dto.SignupEmployee
import io.ktor.server.plugins.*
import com.shayan.util.enums.EmployeeState
import java.time.LocalDate

class EmployeeService(
    private val employeeRepository: EmployeeRepository,
    private val employeeAuditLogService: EmployeeAuditLogService,
    private val roleService: RoleService,
    private val userService: UsersService
) {
    suspend fun getAll(ip: String, employeeId: String, roleId: String): List<EmployeeNoTokenResponse> {
        runCatching { employeeAuditLogService.addAuditLog(employeeId, roleId, EmployeeConst.GET_ALL_ACTION, ip) }
        return dbQuery { employeeRepository.getAll().map { it.toEmployeeNoTokenResponse() } }
    }

    suspend fun getAll():
            List<EmployeeNoTokenResponse> =
        dbQuery {

            employeeRepository.getAll()
                .map {
                    it.toEmployeeNoTokenResponse()
                }
        }

    suspend fun login(loginEmployee: LoginEmployee): EmployeeResponse {
        var roleId= ""
        val result = dbQuery {
            val result = employeeRepository.findByEmail(loginEmployee.email) ?: throw InvalidCredentials()
            roleId = result.roleId
            val hashPassword = PasswordHasher.hashPassword(
                loginEmployee.password,
                result.salt,
                result.iterations,
                result.algorithm
            )

            if (employeeRepository.checkIfTerminated(result.id)) throw Forbidden(true)
            if (hashPassword.hash != result.passwordHash) throw InvalidCredentials()

            val roleCode = roleService.read(result.roleId).code

            result.toEmployeeResponse(AccessTokenGenerator.employeeToken(result.id, roleCode), roleCode)
        }
        runCatching {
            employeeAuditLogService.addAuditLog(
                result.id,
                roleId,
                EmployeeConst.LOGIN_ACTION,
                loginEmployee.ip
            )
        }
        return result
    }

    suspend fun signup(signupEmployee: SignupEmployee, employeeId: String, roleId: String): EmployeeNoTokenResponse {
        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId,
                roleId,
                EmployeeConst.CREATE_ACTION,
                signupEmployee.ip
            )
        }
        return dbQuery {
            val password = PasswordHasher.hashPassword(signupEmployee.password)
            val id = IdGenerator.generate()
            if(userService.emailExist(signupEmployee.email)) throw EmailExist()
            val request = Employee(
                id = id,
                roleId = signupEmployee.roleId,
                name = signupEmployee.name,
                nationalId = signupEmployee.nationalId,
                phone = signupEmployee.phone,
                email = signupEmployee.email,
                salary = signupEmployee.salary,
                address = signupEmployee.address,
                gender = signupEmployee.gender,
                birthday = signupEmployee.birthday,
                emergencyContactName = signupEmployee.emergencyContactName,
                emergencyContactPhone = signupEmployee.emergencyContactPhone,
                state = signupEmployee.state,
                passwordHash = password.hash,
                iterations = password.iterations,
                algorithm = password.algorithm,
                salt = password.salt,
            )
            employeeRepository.add(request)?.toEmployeeNoTokenResponse() ?: throw FailedToAdd()
        }
    }

    suspend fun updateInfo(
        updateEmployeeInfo: UpdateEmployeeInfo,
        employeeId: String,
        roleId: String
    ): EmployeeNoTokenResponse {
        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId,
                roleId,
                EmployeeConst.UPDATE_INFO_ACTION,
                updateEmployeeInfo.ip
            )
        }
        return dbQuery {
            val request = Employee(
                id = updateEmployeeInfo.id,
                roleId = "",
                name = updateEmployeeInfo.name,
                nationalId = updateEmployeeInfo.nationalId,
                phone = updateEmployeeInfo.phone,
                email = updateEmployeeInfo.email,
                salary = updateEmployeeInfo.salary,
                address = updateEmployeeInfo.address,
                gender = updateEmployeeInfo.gender,
                birthday = updateEmployeeInfo.birthday,
                emergencyContactName = updateEmployeeInfo.emergencyContactName,
                emergencyContactPhone = updateEmployeeInfo.emergencyContactPhone,
                state = EmployeeState.Terminated,
                passwordHash = "",
                iterations = 0,
                algorithm = "",
                salt = ""
            )
            employeeRepository.updateInfo(request)?.toEmployeeNoTokenResponse() ?: throw NotFoundException()
        }
    }

    suspend fun updatePassword(
        updateEmployeePassword: UpdateEmployeePassword,
        employeeId: String,
        roleId: String
    ): EmployeeNoTokenResponse {
        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId,
                roleId,
                EmployeeConst.UPDATE_PASSWORD_ACTION,
                updateEmployeePassword.ip
            )
        }
        val password = PasswordHasher.hashPassword(updateEmployeePassword.newPassword)
        return dbQuery {
            val request = Employee(
                id = updateEmployeePassword.id,
                passwordHash = password.hash,
                iterations = password.iterations,
                algorithm = password.algorithm,
                salt = password.salt,
                roleId = "",
                name = "",
                nationalId = "",
                phone = "",
                email = "",
                salary = 0.00.toBigDecimal(),
                address = "",
                gender = Gender.NotSpecified,
                birthday = LocalDate.now(),
                emergencyContactName = "",
                emergencyContactPhone = "",
                state = EmployeeState.Terminated,
            )
            employeeRepository.updatePassword(request)?.toEmployeeNoTokenResponse() ?: throw NotFoundException()
        }
    }

    suspend fun deleteEmployee(employeeId: String, roleId: String, idIpDTO: IdIpDTO) {
        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId,
                roleId,
                EmployeeConst.DELETE_ACTION,
                idIpDTO.ip
            )
        }
        dbQuery {
            employeeRepository.delete(idIpDTO.id)
        }
    }
}