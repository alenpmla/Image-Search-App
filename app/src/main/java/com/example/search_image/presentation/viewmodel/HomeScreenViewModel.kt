package com.example.search_image.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.search_image.common.Resource
import com.example.search_image.domain.repository.SearchRepository
import com.example.search_image.presentation.SearchEvent
import com.example.search_image.presentation.SearchListingState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(private val repository: SearchRepository) :
    ViewModel() {
    var state by mutableStateOf(SearchListingState())
    private var searchJob: Job? = null

    init {
        Log.e("HomeScreenViewModel", "init run")
        viewModelScope.launch {
            getSearchListing(query = "fruits")
        }
    }

    private fun getSearchListing(
        query: String = state.searchQuery.lowercase(),
    ) {
        viewModelScope.launch {
            repository
                .getSearchResults(searchQuery = query)
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    searchLists = listings
                                )
                            }
                        }
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading = result.isLoading)
                        }
                    }
                }
        }
    }


    fun onEvent(event: SearchEvent) {
        when (event) {
            is SearchEvent.OnSearchQueryChange -> {
                state = state.copy(searchQuery = event.query)
                searchJob?.cancel()
                searchJob = viewModelScope.launch {
                    delay(500L)
                    getSearchListing()
                }
            }
        }
    }


}