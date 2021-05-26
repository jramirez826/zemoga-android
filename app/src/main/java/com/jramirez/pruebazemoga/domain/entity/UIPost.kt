package com.jramirez.pruebazemoga.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UIPost(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String,
    var isFavorite: Boolean = false,
    var isRead: Boolean = false
) : Parcelable
