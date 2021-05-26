package com.jramirez.pruebazemoga.domain.interactor

import android.content.Context
import com.jramirez.pruebazemoga.data.web.Resource
import com.jramirez.pruebazemoga.data.web.Status
import com.jramirez.pruebazemoga.data.web.repository.UserRepository
import com.jramirez.pruebazemoga.data.web.repository.UserRepositoryImpl
import com.jramirez.pruebazemoga.domain.entity.UIUser
import com.jramirez.pruebazemoga.domain.ext.toUIUser
import com.jramirez.pruebazemoga.domain.utils.InternetManager

class UserInteractor(
    override val context: Context,
    private val userRepository: UserRepository = UserRepositoryImpl()
) : BaseInteractor<Int, UIUser> {

    override suspend fun execute(params: Int?): Resource<UIUser> {
        val call =
            params?.let {
                if (InternetManager.isInternetAvailable(context))
                    userRepository.getUser(it)
                else
                    responseHandler.handleException("No internet")
            } ?: run {
                responseHandler.handleException("No Params")
            }
        val result = when (call.status) {
            Status.SUCCESS -> {
                val item = call.data?.toUIUser()
                responseHandler.handleSuccess(item!!)
            }
            Status.ERROR
            -> responseHandler.handleException(call.message ?: "")
        }
        return result
    }

}