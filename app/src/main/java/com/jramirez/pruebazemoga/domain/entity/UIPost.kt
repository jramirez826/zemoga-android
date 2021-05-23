package com.jramirez.pruebazemoga.domain.entity

data class UIPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var isFavorite: Boolean = false,
    var isRead: Boolean = false
)
