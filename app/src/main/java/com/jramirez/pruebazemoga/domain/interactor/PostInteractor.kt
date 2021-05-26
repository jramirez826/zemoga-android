package com.jramirez.pruebazemoga.domain.interactor

import android.content.Context
import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.data.web.Status
import com.jramirez.pruebazemoga.data.web.repository.PostRepository
import com.jramirez.pruebazemoga.data.web.repository.PostRepositoryImpl
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.domain.ext.toUIPost
import com.jramirez.pruebazemoga.domain.utils.InternetManager

class PostInteractor(
    override val context: Context,
    private val postRepository: PostRepository = PostRepositoryImpl()
) : BaseInteractor<Nothing, List<UIPost>> {

    override suspend fun execute(params: Nothing?): Resource<List<UIPost>> {
        val call =
            if (InternetManager.isInternetAvailable(context))
                postRepository.getPosts()
            else
                responseHandler.handleSuccess(emptyList())

        val result = when (call.status) {
            Status.SUCCESS -> {
                val items = call.data?.map { it.toUIPost() }
                responseHandler.handleSuccess(items!!)
            }
            Status.ERROR -> responseHandler.handleException(call.message ?: "")
        }
        return result
    }
}