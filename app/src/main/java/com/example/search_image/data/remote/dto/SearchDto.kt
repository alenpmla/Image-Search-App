package com.example.search_image.data.remote.dto

import com.example.search_image.data.local.SearchListingEntity
import com.example.search_image.domain.entities.Search
import com.google.gson.annotations.SerializedName

data class SearchDto(
    val fullHDURL: String,
    val user: String,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
    val id: Int,
    val imageWidth: Float,
    val imageHeight: Float,
    val tags: String,
    val imageURL: String,
    val views: Int,
    val previewURL: String,
    @SerializedName("user_id")
    val userId: Int,
    val largeImageURL: String,
    val userImageURL: String,
)

fun SearchDto.toSearch(): Search {
    return Search(
        fullHDURL = fullHDURL,
        user = user,
        likes = likes,
        downloads = downloads,
        comments = comments,
        imageHeight = imageHeight,
        imageWidth = imageWidth,
        id = id,
        tags = tags,
        imageURL = imageURL,
        views = views,
        previewURL = previewURL,
        userId = userId,
        largeImageURL = largeImageURL,
        userImageURL = userImageURL
    )
}

fun SearchDto.toSearchEntity(): SearchListingEntity {
    return SearchListingEntity(
        fullHDURL = fullHDURL,
        user = user,
        likes = likes,
        downloads = downloads,
        comments = comments,
        imageHeight = imageHeight,
        imageWidth = imageWidth,
        id = id,
        tags = tags,
        imageURL = imageURL,
        views = views,
        previewURL = previewURL,
        userId = userId,
        largeImageURL = largeImageURL,
        userImageURL = userImageURL
    )
}