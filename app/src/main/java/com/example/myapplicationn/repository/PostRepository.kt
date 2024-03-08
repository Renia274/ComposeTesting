package com.example.myapplicationn.repository

import com.example.myapplicationn.data.Post

interface PostRepository {
    suspend fun getPosts(): List<Post>

}
