package com.example.search_image.di

import android.app.Application
import androidx.room.Room
import com.example.search_image.common.Constants
import com.example.search_image.data.local.SearchDatabase
import com.example.search_image.data.remote.SearchApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideStockApi(): SearchApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BASIC
                }).build()
            )
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun providesSearchDb(app: Application): SearchDatabase {
        return Room.databaseBuilder(
            app,
            SearchDatabase::class.java,
            "searchDb.db"
        ).build()
    }

}