package com.jramirez.pruebazemoga.presentation.ui.detailpost

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jramirez.pruebazemoga.domain.entity.UIComment
import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.domain.entity.UIUser
import com.jramirez.pruebazemoga.domain.interactor.CommentInteractor
import com.jramirez.pruebazemoga.domain.interactor.UserInteractor
import com.jramirez.pruebazemoga.presentation.application.ZemogaApplication
import com.jramirez.pruebazemoga.presentation.util.IntentConstants
import kotlinx.coroutines.launch

class PostDetailViewModel(
    private val post: UIPost,
    application: Application = ZemogaApplication.instance,
    private val userInteractor: UserInteractor = UserInteractor(application),
    private val commentInteractor: CommentInteractor = CommentInteractor(application),

    ) : AndroidViewModel(application) {

    private val _liveUser = MutableLiveData<UIUser>()
    private val _liveComments = MutableLiveData<List<UIComment>>()
    private val _liveIntent = MutableLiveData<Intent>()
    val liveUser: LiveData<UIUser> get() = _liveUser
    val liveComments: LiveData<List<UIComment>> get() = _liveComments
    val liveIntent: LiveData<Intent> get() = _liveIntent

    fun getUser() {
        viewModelScope.launch {
            val response = userInteractor.execute(post.userId)
            response.data?.let {
                _liveUser.postValue(it)
            }
        }
    }

    fun getComments() {
        viewModelScope.launch {
            val response = commentInteractor.execute(post.id)
            response.data?.let {
                _liveComments.postValue(it)
            }
        }
    }

    fun onFavorite(isFavorite: Boolean) {
        post.isFavorite = isFavorite
    }

    fun onBackPressed() {
        val intent = Intent().apply {
            putExtra(IntentConstants.UI_POST, post)
        }
        _liveIntent.postValue(intent)
    }
}
