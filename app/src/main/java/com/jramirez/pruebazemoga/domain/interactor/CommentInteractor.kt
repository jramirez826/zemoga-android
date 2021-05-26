package com.jramirez.pruebazemoga.domain.interactor

import android.content.Context
import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.data.web.Status
import com.jramirez.pruebazemoga.data.web.repository.CommentRepository
import com.jramirez.pruebazemoga.data.web.repository.CommentRepositoryImpl
import com.jramirez.pruebazemoga.domain.entity.UIComment
import com.jramirez.pruebazemoga.domain.ext.toUIComment
import com.jramirez.pruebazemoga.domain.utils.InternetManager

class CommentInteractor(
    override val context: Context,
    private val commentRepository: CommentRepository = CommentRepositoryImpl()
) : BaseInteractor<Int, List<UIComment>> {

    override suspend fun execute(params: Int?): Resource<List<UIComment>> {
        val call =
            params?.let {
                if (InternetManager.isInternetAvailable(context))
                    commentRepository.getComments(it)
                else
                    responseHandler.handleException("No internet")
            } ?: run {
                responseHandler.handleException("No Params")
            }
        val result = when (call.status) {
            Status.SUCCESS -> {
                val item = call.data?.map { it.toUIComment() }
                responseHandler.handleSuccess(item!!)
            }
            Status.ERROR
            -> responseHandler.handleException(call.message ?: "")
        }
        return result
    }
}