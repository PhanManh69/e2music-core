package com.mobile.e2m.core.datasource.local.fakedata.model

import androidx.compose.runtime.Immutable

@Immutable
data class AlbumsModel(
    val id: Int,
    val avatar: String,
    val name: String,
    val singer: String,
    val songs: List<SongsModel>,
)
