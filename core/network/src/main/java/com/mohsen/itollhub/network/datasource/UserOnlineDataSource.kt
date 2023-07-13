package com.mohsen.itollhub.network.datasource

import com.mohsen.itollhub.network.datasource.NetworkErrorHandler.safeApiCall
import com.mohsen.itollhub.network.model.NetworkUserDetail
import com.mohsen.itollhub.network.model.SearchApiResult
import javax.inject.Inject


class UserOnlineDataSource @Inject constructor(private val api: Api) {
    suspend fun searchUser(query: String): Result<SearchApiResult> = safeApiCall {
        api.searchUser(query)
    }

    suspend fun getUserDetail(userName: String): Result<NetworkUserDetail> = safeApiCall {
        api.getUserDetail(userName)
    }
}