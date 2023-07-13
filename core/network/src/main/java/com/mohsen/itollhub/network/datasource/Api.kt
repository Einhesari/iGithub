package com.mohsen.itollhub.network.datasource

import com.mohsen.itollhub.network.model.NetworkUserDetail
import com.mohsen.itollhub.network.model.SearchApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String): Response<SearchApiResult>

    @GET("users/{username}")
    suspend fun getUserDetail(@Path("username") username: String): Response<NetworkUserDetail>

}