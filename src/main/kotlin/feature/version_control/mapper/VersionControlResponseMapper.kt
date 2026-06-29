package com.shayan.feature.version_control.mapper

import com.shayan.feature.version_control.dto.VersionControlResponse
import com.shayan.feature.version_control.model.VersionControl

fun VersionControl.toVersionControlResponse() =
    VersionControlResponse(
        id = id,
        version = version,
        active = active,
        description = description,
        forced = forced,
        createdAt = createdAt
    )