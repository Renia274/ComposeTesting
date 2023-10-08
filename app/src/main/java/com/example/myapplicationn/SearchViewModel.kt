package com.example.myapplicationn

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SearchViewModel : ViewModel() {
    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults

    // Function to update search results in the ViewModel
    fun updateSearchResults(results: List<String>) {
        _searchResults.value = results
    }
}
