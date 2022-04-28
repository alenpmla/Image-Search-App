package com.example.search_image.domain.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Search(
    val comments: Int?,
    val downloads: Int?,
    val fullHDURL: String?,
    val id: Int?,
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
) : Parcelable
