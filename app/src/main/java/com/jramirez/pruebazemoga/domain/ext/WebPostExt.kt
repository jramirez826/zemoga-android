package com.jramirez.pruebazemoga.domain.ext

import com.jramirez.pruebazemoga.domain.entity.UIPost
import com.jramirez.pruebazemoga.domain.entity.WebPost

fun WebPost.toUIPost(): UIPost = UIPost(userId, id, title, body)