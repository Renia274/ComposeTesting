package com.example.myapplicationn

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel : ViewModel() {
    // Property to hold the search results
    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults

    // Property to hold the list of posts
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    // Property to track whether a search is in progress
    private val _isSearching = MutableStateFlow(false)
    val isSearching: StateFlow<Boolean> = _isSearching

    init {
        // Initialize the list of 22 posts
        val initialPosts = generateInitialPosts(3)
        _posts.value = initialPosts
    }

    // Function to update search results in the ViewModel
    fun updateSearchResults(results: List<String>) {
        _searchResults.value = results
    }

    // Function to update the list of posts
    fun updatePosts(newPosts: List<Post>) {
        _posts.value = newPosts
    }


    // Function to perform a search
    fun performSearch(query: String) {
        viewModelScope.launch {
            _isSearching.value = true

            // Perform the search operation (replace with your actual logic)
            val results = fetchDataFromDataSource(query)

            // Update the search results in the ViewModel
            updateSearchResults(results)

            _isSearching.value = false
        }
    }

    // Simulated data source representing a list of posts
    private val mockPosts = generateInitialPosts(singlePostId = 3)

    private fun generateInitialPosts(singlePostId: Int): List<Post> {
        return listOf(
            Post(id = singlePostId, content = "This is post $singlePostId content.")
        )
    }


    private suspend fun fetchDataFromDataSource(query: String): List<String> {
        // Simulate an asynchronous operation, like a network request or database query
        delay(1000) // Simulate a delay to mimic a real operation

        // Replace this with your actual data source logic
        // Here, we're simulating search results based on a query
        val results = mockPosts.filter { post ->
            post.content.contains(query, ignoreCase = true)
        }.map { it.content }

        return results
    }
}
