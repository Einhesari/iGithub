package com.mohsen.itollhub.network.datasource

import com.mohsen.itollhub.network.model.SearchApiResult
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    suspend fun searchUser(@Query("q") query: String): Response<SearchApiResult>

}