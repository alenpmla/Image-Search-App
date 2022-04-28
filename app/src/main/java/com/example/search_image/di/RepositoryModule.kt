package com.example.search_image.di

import com.example.search_image.data.repository.SearchRepositoryImpl
import com.example.search_image.domain.repository.SearchRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun buildSearchRepository(
        searchRepositoryImpl: SearchRepositoryImpl
    ): SearchRepository
}
