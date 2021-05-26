package com.jramirez.pruebazemoga.data.web.repository

import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.domain.entity.WebUser

class UserRepositoryImpl : UserRepository {
    override suspend fun getUser(userId: Int): Resource<WebUser> =
        handleAPICall { client.getUser(userId) }
}