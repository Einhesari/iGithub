package com.mohsen.itollhub.data.repository

import com.mohsen.itollhub.data.model.mapToUser
import com.mohsen.itollhub.data.model.mapToUserDetail
import com.mohsen.itollhub.model.User
import com.mohsen.itollhub.model.UserDetail
import com.mohsen.itollhub.network.datasource.UserOnlineDataSource
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(private val dataSource: UserOnlineDataSource) :
    UserRepository {
    override suspend fun searchUser(
        searchQuery: String,
        page: Int,
        perPage: Int
    ): Result<List<User>> {
        return dataSource.searchUser(searchQuery, page, perPage).map {
            it.users.map { networkUser ->
                networkUser.mapToUser()
            }
        }
    }

    override suspend fun getUserDetail(userName: String): Result<UserDetail> {
        return dataSource.getUserDetail(userName).mapCatching {
            it.mapToUserDetail()
        }
    }
}