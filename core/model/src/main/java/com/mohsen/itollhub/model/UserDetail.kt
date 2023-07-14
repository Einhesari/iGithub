package com.mohsen.itollhub.model

data class UserDetail(
    val userName: String,
    val name: String,
    val avatarUrl: String,
    val bio: String,
    val location: String,
    val company: String,
    val followersCount: Int,
    val followingCount: Int,
)
