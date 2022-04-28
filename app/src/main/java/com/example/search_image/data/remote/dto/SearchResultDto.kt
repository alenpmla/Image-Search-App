package com.example.search_image.data.remote.dto

data class SearchResultDto(
    val hits: List<SearchDto>,
    val total: Int,
    val totalHits: Int
)
