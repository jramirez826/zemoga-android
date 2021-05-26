package com.jramirez.pruebazemoga.data.web.repository

import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.domain.entity.WebUser

interface UserRepository : BaseRepository {

    suspend fun getUser(userId: Int): Resource<WebUser>
}