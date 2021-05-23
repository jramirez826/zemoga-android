package com.jramirez.pruebazemoga.data.web

import com.jramirez.pruebazemoga.domain.entity.Comment
import com.jramirez.pruebazemoga.domain.entity.User
import com.jramirez.pruebazemoga.domain.entity.WebPost
import retrofit2.http.GET
import retrofit2.http.Path

interface JSONPlaceHolderAPI {

    @GET("posts")
    suspend fun getPosts(): List<WebPost>

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): User

    @GET("users/{userId}/comments")
    suspend fun getComments(@Path("userId") userId: Int): List<Comment>
}