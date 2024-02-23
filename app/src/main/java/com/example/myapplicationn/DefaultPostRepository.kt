package com.example.myapplicationn

import kotlinx.coroutines.delay

class DefaultPostRepository : PostRepository {
   

    override suspend fun getPosts(): List<Post> {
    
        return fetchDataFromDataSource()
    }



    private suspend fun fetchDataFromDataSource(): List<Post> {
        delay(1000)
        // Return a list of posts from your data source
        return getPosts()
    }
}
