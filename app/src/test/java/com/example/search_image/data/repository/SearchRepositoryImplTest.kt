package com.example.search_image.data.repository

import com.example.search_image.common.Resource
import com.example.search_image.data.local.SearchDatabase
import com.example.search_image.data.local.SearchListingEntity
import com.example.search_image.data.remote.SearchApi
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock

class SearchRepositoryImplTest {
    private lateinit var mockSearchApi: SearchApi
    private lateinit var repository: SearchRepositoryImpl
    private lateinit var mockLocalDb: SearchDatabase
    private lateinit var sampleSearchEntity: SearchListingEntity

    @Before
    fun setUp() {
        mockSearchApi = mock()
        mockLocalDb = mock()
        repository = SearchRepositoryImpl(mockSearchApi, mockLocalDb)
        sampleSearchEntity = SearchListingEntity(
            comments = 0,
            downloads = 0,
            fullHDURL = "String",
            id = null,
            imageWidth = 5f,
            imageHeight = 5f,
            imageURL = "imageURL",
            largeImageURL = "largeImageURL",
            likes = 4,
            previewURL = "previewURL",
            tags = "tags",
            user = "user",
            userImageURL = "userImageURL",
            userId = 2345,
            views = 10,
        )
    }


    @Test
    fun `is loading is true on getSearchList`() = runBlocking {
        Mockito.`when`(mockSearchApi.getSearchList("cat")).thenReturn(null)
        val firstItem = repository.getSearchResults("cat").first()
        assertThat((firstItem as Resource.Loading).isLoading).isEqualTo(true)
    }
}