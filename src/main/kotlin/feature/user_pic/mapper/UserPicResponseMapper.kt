package com.shayan.feature.user_pic.mapper

import com.shayan.feature.user_pic.dto.UserPicResponse
import com.shayan.feature.user_pic.model.UserPic

fun UserPic.toUserPicResponse(url: String): UserPicResponse =
    UserPicResponse(
        id = this.id,
        userId = this.userId,
        title = this.title,
        imageUrl = url
    )