package com.jramirez.pruebazemoga.presentation.ui.detailpost

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.jramirez.pruebazemoga.domain.entity.UIPost

class PostDetailViewModelFactory(
    private val post: UIPost
) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PostDetailViewModel(post) as T
    }
}