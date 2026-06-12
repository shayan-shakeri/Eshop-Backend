package com.shayan.feature.role.mapper

import com.shayan.feature.role.dto.RoleResponse
import com.shayan.feature.role.model.Role

fun Role.toRoleResponse(): RoleResponse =
    RoleResponse(
        id = this.id,
        title = this.title,
        description = this.description,
        code = this.code,
    )