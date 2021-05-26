package com.jramirez.pruebazemoga.domain.entity

data class WebComment(
    val postId: String,
    val id: String,
    val name: String,
    val email: String,
    val body: String
)
