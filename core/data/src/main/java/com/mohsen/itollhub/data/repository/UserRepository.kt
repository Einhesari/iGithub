package com.mohsen.itollhub.data.repository

import com.mohsen.itollhub.model.User
import com.mohsen.itollhub.model.UserDetail


interface UserRepository {
    suspend fun searchUser(searchQuery: String): Result<List<User>>
    suspend fun getUserDetail(userName: String): Result<UserDetail>
}