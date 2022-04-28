package com.example.search_image.domain.repository

import com.example.search_image.common.Resource
import com.example.search_image.domain.entities.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    suspend fun getSearchResults(searchQuery: String): Flow<Resource<List<Search>>>
}