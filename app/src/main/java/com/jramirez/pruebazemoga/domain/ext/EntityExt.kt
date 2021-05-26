package com.jramirez.pruebazemoga.domain.ext

import com.jramirez.pruebazemoga.domain.entity.*

fun WebPost.toUIPost(): UIPost = UIPost(userId, id, title, body)

fun WebUser.toUIUser(): UIUser = UIUser(name, username, email, phone, website)

fun WebComment.toUIComment(): UIComment = UIComment(body)