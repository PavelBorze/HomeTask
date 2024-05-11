package com.pavelb.mishlohatest.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface IRepositoriesApi {

    @GET("/search/repositories")
    suspend fun searchRepositories(
        @Query("q") created: String,
        @Query("per_page") perPage: Int = 10,
        @Query("page") page: Int,
        @Query("sort") sort: String = "stars",
        @Query("order") order: String = "desc"

    ): RepositoryItemsResponse

}