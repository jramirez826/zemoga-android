package com.jramirez.pruebazemoga.data.web.repository

import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.domain.entity.WebPost

interface PostRepository : BaseRepository {

    suspend fun getPosts(): Resource<List<WebPost>>
}