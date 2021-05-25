package com.jramirez.pruebazemoga.presentation.posts.allposts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.domain.interactor.PostInteractor
import kotlinx.coroutines.launch

class AllPostsViewModel(
    application: Application
) : AndroidViewModel(application) {

    private val postInteractor: PostInteractor = PostInteractor(application.applicationContext)
    private var posts: List<UIPost> = emptyList()
    private val _livePosts = MutableLiveData<List<UIPost>>()
    val livePosts: LiveData<List<UIPost>> get() = _livePosts

    fun getPosts() {
        viewModelScope.launch {
            val response = postInteractor.execute()
            response.data?.let {
                posts = it
                _livePosts.postValue(it)
            }
        }
    }

    fun allPostsTab() {
        _livePosts.postValue(posts)
    }

    fun favoritePostsTab() {
        val favoritePosts = posts.filter {
            it.isFavorite
        }
        _livePosts.postValue(favoritePosts)
    }

}