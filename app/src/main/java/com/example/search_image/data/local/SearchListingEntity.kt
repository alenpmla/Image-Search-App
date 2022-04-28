package com.example.search_image.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.search_image.domain.entities.Search

@Entity
data class SearchListingEntity(
    val comments: Int?,
    val downloads: Int?,
    val fullHDURL: String?,
    @PrimaryKey val id: Int? = null,
    val imageWidth: Float,
    val imageHeight: Float,
    val imageURL: String?,
    val largeImageURL: String?,
    val likes: Int?,
    val previewURL: String?,
    val tags: String?,
    val user: String?,
    val userImageURL: String?,
    val userId: Int?,
    val views: Int?,
)

fun SearchListingEntity.toSearch(): Search {
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

