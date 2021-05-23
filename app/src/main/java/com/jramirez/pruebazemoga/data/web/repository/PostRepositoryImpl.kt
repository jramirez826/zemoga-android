package com.jramirez.pruebazemoga.data.web.repository

import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.domain.entity.WebPost

class PostRepositoryImpl : PostRepository {
    override suspend fun getPosts(): Resource<List<WebPost>> =
        handleAPICall { client.getPosts() }
}