package com.mohsen.itollhub.data.repository

import com.mohsen.itollhub.data.model.mapToUser
import com.mohsen.itollhub.model.User
import com.mohsen.itollhub.network.datasource.UserOnlineDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dataSource: UserOnlineDataSource) :
    UserRepository {
    override suspend fun searchUser(searchQuery: String): Result<List<User>> {
        return dataSource.searchUser(searchQuery).map {
            it.users.map { networkUser ->
                networkUser.mapToUser()
            }
        }
    }
}