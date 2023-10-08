package com.example.myapplicationn

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class SearchViewModel : ViewModel() {
    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults

    fun performSearch(query: String) {
        // Simulate a search operation with a delay (replace with your actual search logic)
        val results = listOf("Result 1 for '$query'", "Result 2 for '$query'", "Result 3 for '$query'")

        // Update the search results
        _searchResults.value = results
    }
}

