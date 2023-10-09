package com.example.myapplicationn

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SearchViewModel : ViewModel() {
    private val _searchResults = MutableStateFlow<List<String>>(emptyList())
    val searchResults: StateFlow<List<String>> = _searchResults

    // Add a posts property
    private val _posts = MutableStateFlow<List<Post>>(emptyList())
    val posts: StateFlow<List<Post>> = _posts

    // Function to update search results in the ViewModel.
    fun updateSearchResults(results: List<String>) {
        _searchResults.value = results
    }

    // Function to update the list of posts
    fun updatePosts(newPosts: List<Post>) {
        _posts.value = newPosts
    }

    suspend fun fetchPostsByQuery(query: String): List<Post> {
        // Perform an asynchronous operation to fetch posts based on the query
        // Replace this with your actual data source or network request
        return fetchDataFromDataSource(query)
    }


    // Simulated data source representing a list of posts
    private val mockPosts = listOf(
        Post(id = 1, content = "This is post 1 content."),
        Post(id = 2, content = "This is post 2 content."),
        Post(id = 3, content = "Another post with a different content."),
        // Add more posts as needed
    )

    private suspend fun fetchDataFromDataSource(query: String): List<Post> {
        // Simulate an asynchronous operation, like a network request or database query
        delay(1000) // Simulate a delay to mimic a real operation

        // Filter posts based on the search query (simplified search logic)
        val filteredPosts = mockPosts.filter { post ->
            post.content.contains(query, ignoreCase = true)
        }

        return filteredPosts
    }

}
