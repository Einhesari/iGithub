package com.mohsen.itollhub.data.repository

import com.mohsen.itollhub.model.User


interface UserRepository {
    suspend fun searchUser(searchQuery: String): Result<List<User>>
}