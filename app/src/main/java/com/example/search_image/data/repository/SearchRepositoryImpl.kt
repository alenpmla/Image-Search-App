package com.example.search_image.data.repository

import com.example.search_image.common.Resource
import com.example.search_image.data.local.SearchDatabase
import com.example.search_image.data.local.toSearch
import com.example.search_image.data.remote.SearchApi
import com.example.search_image.data.remote.dto.toSearch
import com.example.search_image.data.remote.dto.toSearchEntity
import com.example.search_image.domain.entities.Search
import com.example.search_image.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepositoryImpl @Inject constructor(
    private val api: SearchApi,
    private val db: SearchDatabase,

    ) : SearchRepository {
    private val dao = db.dao

    override suspend fun getSearchResults(searchQuery: String): Flow<Resource<List<Search>>> {
        return flow {
            emit(value = Resource.Loading(true))

            val localListings = dao.querySearchListing(searchQuery)
            emit(Resource.Success(
                data = localListings.map { it.toSearch() }
            ))
            if (localListings.isNotEmpty()) {
                emit(Resource.Loading(false))
            }

            val remoteListings = try {
                val response = api.getSearchList(searchQuery = searchQuery)
                response
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let { listings ->
                dao.insertSearchListing(
                    listings.hits.map { it.toSearchEntity() }
                )
                emit(Resource.Success(
                    data = remoteListings
                        .hits.map {
                            it.toSearch()
                        }
                ))
                emit(
                    Resource.Loading(
                        isLoading = false
                    )
                )
                return@flow
            }

        }

    }

}