package com.jramirez.pruebazemoga.data.web.repository

import com.jramirez.pruebazemoga.data.web.JSONPlaceHolderAPI
import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.data.web.ResponseHandler
import com.jramirez.pruebazemoga.data.web.RetrofitClient

interface BaseRepository {

    val client: JSONPlaceHolderAPI
        get() = RetrofitClient.client

    private val responseHandler: ResponseHandler
        get() = ResponseHandler()

    suspend fun <T : Any> handleAPICall(
        clientCall: suspend () -> T
    ): Resource<T> {
        return try {
            responseHandler.handleSuccess(clientCall.invoke())
        } catch (e: Exception) {
            responseHandler.handleException(e)
        }
    }
}