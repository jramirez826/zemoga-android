package com.jramirez.pruebazemoga.presentation.posts.allposts

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AllPostsViewModelFactory(private val app: Application) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AllPostsViewModel(app) as T
    }
}