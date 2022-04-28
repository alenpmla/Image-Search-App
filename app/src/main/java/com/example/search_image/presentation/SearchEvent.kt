package com.example.search_image.presentation


sealed class SearchEvent {
    data class OnSearchQueryChange(val query: String) : SearchEvent()
}
