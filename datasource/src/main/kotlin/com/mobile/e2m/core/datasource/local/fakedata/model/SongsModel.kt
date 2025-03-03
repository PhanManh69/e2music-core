package com.mobile.e2m.core.datasource.local.fakedata.model

import androidx.compose.runtime.Immutable

@Immutable
data class SongsModel(
    val id: Int? = null,
    val linkSong: Int? = null,
    val avatar: String? = null,
    val name: String? = null,
    val singer: String? = null,
    val numberLikes: Int? = null,
    val views: Int? = null,
    val genres: List<GenresModel>? = null,
)
