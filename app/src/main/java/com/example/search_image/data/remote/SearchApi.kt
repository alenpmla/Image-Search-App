package com.example.search_image.data.remote

import com.example.search_image.common.Constants.API_KEY
import com.example.search_image.data.remote.dto.SearchResultDto
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApi {
    @GET("/api/")
    suspend fun getSearchList(
        @Query("q") searchQuery: String,
        @Query("key") apiKey: String = API_KEY
    ): SearchResultDto
}