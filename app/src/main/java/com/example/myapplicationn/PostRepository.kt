package com.example.myapplicationn

interface PostRepository {
    suspend fun getPosts(): List<Post>
    suspend fun updateSinglePostId(newId: Int)
}
