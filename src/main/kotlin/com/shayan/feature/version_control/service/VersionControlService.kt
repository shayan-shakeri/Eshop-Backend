package com.shayan.feature.version_control.service

import com.shayan.core.exception.FailedToAdd
import com.shayan.core.response.IdIpDTO
import com.shayan.feature.employee_audit_log.service.EmployeeAuditLogService
import com.shayan.feature.version_control.constants.VersionControlConst
import com.shayan.feature.version_control.dto.AddVersionRequest
import com.shayan.feature.version_control.dto.UpdateVersionControlRequest
import com.shayan.feature.version_control.dto.VerifyVersionRequest
import com.shayan.feature.version_control.dto.VerifyVersionResponse
import com.shayan.feature.version_control.mapper.toVersionControlResponse
import com.shayan.feature.version_control.model.VersionControl
import com.shayan.feature.version_control.repository.VersionControlRepository
import core.database.dbQuery
import core.util.IdGenerator
import io.ktor.server.plugins.*
import java.time.Instant

class VersionControlService(
    private val repository: VersionControlRepository,
    private val employeeAuditLogService: EmployeeAuditLogService
) {

    suspend fun add(
        employeeId: String,
        roleId: String,
        request: AddVersionRequest
    ) =
        dbQuery {

            runCatching {
                employeeAuditLogService.addAuditLog(
                    employeeId = employeeId,
                    roleId = roleId,
                    action = VersionControlConst.ADD_ACTION,
                    ip = request.ip
                )
            }

            if (request.active) {
                repository.readAll()
                    .filter { it.active }
                    .forEach {
                        repository.update(
                            it.copy(
                                active = false
                            )
                        )
                    }
            }

            val versionControl =
                VersionControl(
                    id = IdGenerator.generate(),
                    version = request.version,
                    active = request.active,
                    description = request.description,
                    forced = request.forced,
                    createdAt = Instant.now()
                )

            repository.add(
                versionControl
            )?.toVersionControlResponse()
                ?: throw FailedToAdd()
        }

    suspend fun read() =
        dbQuery {

            repository.readAll()
                .map {
                    it.toVersionControlResponse()
                }
        }

    suspend fun verify(
        request: VerifyVersionRequest
    ): VerifyVersionResponse =
        dbQuery {

            val activeVersion =
                repository.findActive()
                    ?: throw NotFoundException()

            VerifyVersionResponse(
                supported =
                    request.version ==
                            activeVersion.version,

                forced =
                    request.version !=
                            activeVersion.version &&
                            activeVersion.forced,

                latestVersion =
                    activeVersion.version,

                description =
                    activeVersion.description
            )
        }

    suspend fun update(
        employeeId: String,
        roleId: String,
        request: UpdateVersionControlRequest
    ) =
        dbQuery {

            runCatching {
                employeeAuditLogService.addAuditLog(
                    employeeId = employeeId,
                    roleId = roleId,
                    action = VersionControlConst.UPDATE_ACTION,
                    ip = request.ip
                )
            }

            val existing =
                repository.findById(
                    request.id
                ) ?: throw NotFoundException()

            if (request.active) {
                repository.readAll()
                    .filter { it.active }
                    .forEach {
                        repository.update(
                            it.copy(
                                active = false
                            )
                        )
                    }
            }

            repository.update(
                existing.copy(
                    version = request.version,
                    active = request.active,
                    description = request.description,
                    forced = request.forced
                )
            )?.toVersionControlResponse()
                ?: throw NotFoundException()
        }

    suspend fun delete(
        employeeId: String,
        roleId: String,
        request: IdIpDTO
    ) {

        runCatching {
            employeeAuditLogService.addAuditLog(
                employeeId = employeeId,
                roleId = roleId,
                action = VersionControlConst.DELETE_ACTION,
                ip = request.ip
            )
        }

        dbQuery {

            repository.findById(
                request.id
            ) ?: throw NotFoundException()

            repository.delete(
                request.id
            )
        }
    }
}