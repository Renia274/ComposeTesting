package com.example.myapplicationn

import kotlinx.coroutines.delay

class DefaultPostRepository : PostRepository {
    // You can inject your data source here, e.g., a network or database service

    override suspend fun getPosts(): List<Post> {
        // Replace this with your actual data source query logic
        return fetchDataFromDataSource()
    }



    private suspend fun fetchDataFromDataSource(): List<Post> {
        delay(1000)
        // Return a list of posts from your data source
        return getPosts()
    }
}
