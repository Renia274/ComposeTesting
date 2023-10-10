package com.example.myapplicationn

interface PostRepository {
    suspend fun getPosts(): List<Post>

}
