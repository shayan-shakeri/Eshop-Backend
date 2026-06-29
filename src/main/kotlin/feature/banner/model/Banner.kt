package com.shayan.feature.banner.model

import java.time.Instant

data class Banner(
    val id: String,
    val title: String,
    val active: Boolean,
    val createdAt: Instant
)