package com.mohsen.itollhub.data.model

import com.mohsen.itollhub.model.User
import com.mohsen.itollhub.model.UserDetail
import com.mohsen.itollhub.network.model.NetworkUser
import com.mohsen.itollhub.network.model.NetworkUserDetail

fun NetworkUser.mapToUser() = User(
    userName = login,
    id = id,
    score = score,
    type = type,
    avatarUrl = avatarUrl
)

fun NetworkUserDetail.mapToUserDetail() = UserDetail(
    userName = login,
    name = name ?: "",
    avatarUrl = avatarUrl,
    bio = bio ?: "",
    location = location ?: "",
    company = company ?: "",
    followersCount = followers,
    followingCount = following
)