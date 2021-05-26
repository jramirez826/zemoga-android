package com.jramirez.pruebazemoga.data.web.repository

import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.domain.entity.WebComment

interface CommentRepository : BaseRepository {
    suspend fun getComments(postId: Int) : Resource<List<WebComment>>
}