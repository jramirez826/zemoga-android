package com.jramirez.pruebazemoga.domain.interactor

import android.content.Context
import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.data.web.ResponseHandler

interface BaseInteractor<P, R> {

    var context: Context

    val responseHandler: ResponseHandler
        get() = ResponseHandler()

    suspend fun execute(params: P? = null): Resource<R>

}