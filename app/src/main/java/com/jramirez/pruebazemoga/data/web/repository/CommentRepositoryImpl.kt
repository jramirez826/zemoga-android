package com.jramirez.pruebazemoga.data.web.repository

import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.domain.entity.WebComment

class CommentRepositoryImpl : CommentRepository {
    override suspend fun getComments(postId: Int): Resource<List<WebComment>> =
        handleAPICall {
            client.getComments(postId)
        }
}