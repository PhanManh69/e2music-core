package com.mobile.e2m.core.datasource.local.fakedata.model

import androidx.compose.runtime.Immutable

@Immutable
data class PlaylistsModel(
    val id: Int,
    val avatar: String,
    val username: String,
    val name: String,
    val songs: List<SongsModel>,
)
