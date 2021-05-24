package com.jramirez.pruebazemoga.presentation.main.allposts

import android.app.Application
import androidx.lifecycle.*
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.domain.interactor.PostInteractor
import kotlinx.coroutines.launch

class AllPostsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val postInteractor: PostInteractor = PostInteractor(application.applicationContext)

    private val _livePosts = MutableLiveData<List<UIPost>>()
    val livePosts: LiveData<List<UIPost>> get() = _livePosts

    fun getPosts() {
        viewModelScope.launch {
            val response = postInteractor.execute()
            response.data?.let {
                _livePosts.postValue(it)
            }
        }
    }

}