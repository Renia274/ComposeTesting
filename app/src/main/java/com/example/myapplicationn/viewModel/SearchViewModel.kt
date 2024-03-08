package com.example.myapplicationn.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplicationn.data.Post
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class ProfilePageState(
    val searchText: String = "",
    val isSearching: Boolean = false,
    val searchResults: List<String> = emptyList(),
    val posts: List<Post> = emptyList(),
    val singlePostId: Int = 3,
    val showErrorSnackbar: Boolean = false,
    val snackbarMessage: String = ""
)

class SearchViewModel : ViewModel() {
    private val state = MutableStateFlow(ProfilePageState())
    val stateFlow: StateFlow<ProfilePageState> = state

    suspend fun generateInitialPosts(posts: List<Post>) {
        delay(1000) // Simulate network delay
        state.update { it.copy(posts = posts) }
    }

    fun updateSearchText(newText: String) {
        state.update { it.copy(searchText = newText) }
    }

    fun showSnackbar(message: String) {
        state.update { it.copy(showErrorSnackbar = true, snackbarMessage = message) }
    }

    fun dismissSnackbar() {
        state.update { it.copy(showErrorSnackbar = false, snackbarMessage = "") }
    }

    fun updateSinglePostId(newId: Int) {
        state.update { it.copy(singlePostId = newId) }
    }

    fun performSearch(query: String) {
        viewModelScope.launch {
            state.update { it.copy(isSearching = true) }

            // Simulate fetching data from a data source
            val results = fetchDataFromDataSource(query)

            state.update { it.copy(searchResults = results, isSearching = false) }
        }
    }

    private suspend fun fetchDataFromDataSource(query: String): List<String> {
        delay(1000) // Simulate network delay

        // Simulated search results based on the query
        val results = state.value.posts.filter { post ->
            post.content.contains(query, ignoreCase = true)
        }.map { it.content }

        return results
    }

    init {
        // Initialize posts when ViewModel is created
        viewModelScope.launch {
            val posts = List(22) { Post(id = it, content = "This is post $it content.") }
            generateInitialPosts(posts)
        }
    }

}
