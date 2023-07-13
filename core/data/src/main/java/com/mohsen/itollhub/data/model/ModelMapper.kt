package com.mohsen.itollhub.data.model

import com.mohsen.itollhub.model.User
import com.mohsen.itollhub.network.model.NetworkUser

fun NetworkUser.mapToUser() = User(
    userName = login,
    score = score,
    type = type
)