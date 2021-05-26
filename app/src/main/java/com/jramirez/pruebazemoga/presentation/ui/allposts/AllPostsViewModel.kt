package com.jramirez.pruebazemoga.presentation.ui.allposts

import android.app.Application
import android.content.Context
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.domain.interactor.PostInteractor
import com.jramirez.pruebazemoga.presentation.application.ZemogaApplication
import com.jramirez.pruebazemoga.presentation.ui.detailpost.PostDetailActivity
import com.jramirez.pruebazemoga.presentation.util.CellClickListener
import com.jramirez.pruebazemoga.presentation.util.IntentConstants
import kotlinx.coroutines.launch

class AllPostsViewModel(
    application: Application = ZemogaApplication.instance,
    private val postInteractor: PostInteractor = PostInteractor(application.applicationContext)
) : AndroidViewModel(application), CellClickListener<UIPost> {

    private var posts: List<UIPost> = emptyList()
    private val _livePosts = MutableLiveData<List<UIPost>>()
    val livePosts: LiveData<List<UIPost>> get() = _livePosts
    private val _liveIntent = MutableLiveData<Intent>()
    val liveIntent: LiveData<Intent> get() = _liveIntent

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

    override fun onCellClickListener(item: UIPost) {
        item.isRead = true
        val intent = Intent(getApplication() as Context, PostDetailActivity::class.java).apply {
            putExtra(
                IntentConstants.UI_POST,
                item
            )
        }
        _liveIntent.postValue(intent)
    }

}