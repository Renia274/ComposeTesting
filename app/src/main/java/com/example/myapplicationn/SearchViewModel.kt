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

    // Property to hold the singlePostId
    private var singlePostId: Int = 3

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

            // Perform the search operation 
            val results = fetchDataFromDataSource(query)

            // Update the search results in the ViewModel
            updateSearchResults(results)

            _isSearching.value = false
        }
    }

    // Simulated data source representing a list of posts
    private val mockPosts: List<Post>
    get() = generateInitialPosts(singlePostId)

    private fun generateInitialPosts(singlePostId: Int): List<Post> {
        return listOf(
            Post(id = singlePostId, content = "This is post $singlePostId content.")
        )
    }

    // Function to dynamically update singlePostId
    fun updateSinglePostId(newId: Int) {
        singlePostId = newId
    }

    private suspend fun fetchDataFromDataSource(query: String): List<String> {
        delay(1000) 

        // search results based on a query
        val results = mockPosts.filter { post ->
            post.content.contains(query, ignoreCase = true)
        }.map { it.content }

        return results
    }
}
